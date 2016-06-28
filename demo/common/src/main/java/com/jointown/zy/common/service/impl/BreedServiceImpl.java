package com.jointown.zy.common.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.jointown.zy.common.dao.BossUserDao;
import com.jointown.zy.common.dao.BreedAttrDao;
import com.jointown.zy.common.dao.BreedCategoryDao;
import com.jointown.zy.common.dao.BreedDao;
import com.jointown.zy.common.dao.DictInfoDao;
import com.jointown.zy.common.dto.WmsBreedDto;
import com.jointown.zy.common.dto.WmsMessageWrapper;
import com.jointown.zy.common.enums.ApiFlagEnums;
import com.jointown.zy.common.model.BossUser;
import com.jointown.zy.common.model.Breed;
import com.jointown.zy.common.model.BreedAttr;
import com.jointown.zy.common.model.BreedCategory;
import com.jointown.zy.common.model.Categorys;
import com.jointown.zy.common.model.DictInfo;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.rabbitmq.RabbitmqProducerManager;
import com.jointown.zy.common.service.BreedService;
import com.jointown.zy.common.service.CategorysService;
import com.jointown.zy.common.util.TimeUtil;

/**
 * 项目名称：common 
 * 类名称：BreedService 
 * 类描述：类目分类Service 
 * 创建人：chengchang 
 * 创建时间：2014年12月18日 上午10:07 
 * 修改人：chengchang 修改时间：2014年12月16日 上午10:07 修改备注：
 * 
 * @version
 *
 */
@Service(value="breedService")
public class BreedServiceImpl implements BreedService {
	private static final Logger logger = LoggerFactory.getLogger(BreedServiceImpl.class);
	@Autowired
	BreedCategoryDao breedCategoryDao;
	@Autowired
	BreedDao breedDao;
	@Autowired
	BreedAttrDao breedAttrDao;
	@Autowired
	private DictInfoDao dictInfoDao;
	@Autowired
	private BossUserDao bossUserDao;
	@Autowired
	private CategorysService categorysService;
	/**
	 * 只插入breed，不插入维护关系
	 */
	@Transactional
	@Override
	public int addBreed(Breed breed) {
		return breedDao.insert(breed);
	}
	
	
	/**
	 * @param breed
	 * @return 逻辑删除的breed个数
	 * @author chengchang
	 * @description 删除breed(品种表),实际上市修改breed的有效状态和BREED_CATEGORY的有效状态
	 * 在breed中逻辑删除breed，并且删除BREED_CATEGORY中的所有的关系
	 * 1.在breedDao中逻辑删除
	 * 2.在breedCategoryDao中进行逻辑删除
	 */
	@Transactional
	@Override
	public int deleteBreed(Breed breed) {
		int result=  breedDao.deleteBreedLogic(breed);
		breedCategoryDao.deleteLogicByBreedId(breed);
		return result;
	}

	
	/**@author chengchang
	 * 1.插入breed
	 * 2.插入成功后，进行插入breed和categorys的关系维护
	 * 3.插入维护关系成功后返回
	 * 注：需求上面不会出现同时添加breed和categorys
	 */
	@Transactional
	@Override
	public int addBreed(Breed breed, Categorys categorys) {
		int result =breedDao.insert(breed);
		if(result>0){
			BreedCategory breedCategory = new BreedCategory();
			breedCategory.setBreedId(breed.getBreedId());
			breedCategory.setCategorysId(categorys.getId());
			result = breedCategoryDao.insert(breedCategory);
		}
		return result;
	}

	/**
	 * @param map
	 * @author chengchang
	 * @description 根据用户输入的查询条件，查询出相应的类容
	 */
	@Override
	public List<Breed> selectBreedBy(Page<Map<String, Object>> page) {
		return breedDao.selectBreedBy(page);
	}

	@Override
	public Breed selectBreedById(Long id) {
		return breedDao.selectByPrimaryKey(id);
	}

	@Transactional
	@Override
	public int addBreed(Breed breed, List<String> categoryIds) {
		int result = breedDao.insert(breed);
		if(result>0){
			for(int i=0;i<categoryIds.size();i++){
				String categoryId = categoryIds.get(i);
				BreedCategory breedCategory = new BreedCategory();
				breedCategory.setCategorysId(Long.parseLong(categoryId));
				breedCategory.setBreedId(breed.getBreedId());
				breedCategory.setStatus(Short.parseShort("1"));
				breedCategoryDao.insert(breedCategory);
			}
		}
		return result;
	}
	@Transactional
	public int addBreeds(Breed breed,List<Categorys> listCategories){
		int result =0;
		List <String> listCategoryId=new ArrayList<String> ();
		for(Categorys categorys :listCategories){
			String categoryId=categorys.getId().toString();
			listCategoryId.add(categoryId);
		}
//		if(listCategoryId!=null&&listCategoryId.size()>0){
			result=this.addBreed(breed, listCategoryId);
//		}
		return result;
	}
	/**
	 * @description 传入breed，breed和Categorys还有breedAttrs数据添加到数据库中
	 * @param breed
	 * @param listCategories
	 * @param breedAttrs
	 * @return
	 */
	@Transactional
	public int addBreeds(Breed breed,List<Categorys> listCategories,List<BreedAttr> breedAttrs){
		int result=this.addBreeds(breed, listCategories);
		List<BreedAttr> listBreed = new ArrayList<BreedAttr>();
		//将breed的id放入到breedAttrs的每个中组成新的breedAttrs
		for(int i=0;i<breedAttrs.size();i++){
			BreedAttr breedAttr =breedAttrs.get(i);
			breedAttr.setBreedId(breed.getBreedId());
			listBreed.add(breedAttr);
		}
		if(listBreed!=null&&listBreed.size()>0){
			breedAttrDao.insert(listBreed);
		}
		return result;
	}

	
	/**
	 * @author seven
	 * @param BreedAttr breedAttr
	 * @description 查询出符合要求的breedAttr放在list里面
	 */
	@Override
	public List<BreedAttr> queryBreedAttr(BreedAttr breedAttr) {
		return breedAttrDao.queryBreedAttr(breedAttr);
	}

	@Override
	public List<BreedCategory> queryBreedCategoryByBreedId(Long breedId) {
		return breedCategoryDao.selectByBreedId(breedId);
	}

	@Transactional
	@Override
	public int updateBreeds(Breed breed, List<Categorys> listCategories,
			List<BreedAttr> breedAttrs) {
		int result =0;
		long breedId=breed.getBreedId();
		//查询出该breed原来的所有的breedCategorys(修改原来的有效标志改为不可用)
		//查询出breed下面的所有的breedAttr(删除原来的breedAttr)，添加新的
		//breedId不为空
		//breedAttrs
		BreedAttr breedAttr = new BreedAttr();
		breedAttr.setBreedId(breedId);
		//List <BreedAttr> oldBreedAttrs=breedAttrDao.queryBreedAttr(breedAttr);
		//删除老的breedAttr
		int oldBreedAttr=breedAttrDao.deleteBreedAttrByBreedId(breedId);
		//删除老的breedCategory关系表
		int oldBreedCategorys=breedCategoryDao.deleteBreedCategoryBybreedId(breedId);
		//添加新的breedAttrs
		for(int i=0;i<breedAttrs.size();i++){
			breedAttrs.get(i).setBreedId(breedId);
		}
		int newBreedAttr=breedAttrDao.insert(breedAttrs);
		//添加新的listCategories
		for(int i=0;i<listCategories.size();i++){
			BreedCategory breedCategory = new BreedCategory();
			Categorys categorys = listCategories.get(i);
			breedCategory.setBreedId(breedId);
			breedCategory.setCategorysId(categorys.getId());
			breedCategory.setStatus(Short.parseShort("1"));
			breedCategoryDao.insert(breedCategory);
		}
		result=breedDao.updateByPrimaryKeySelective(breed);
		return result;
	}


	@Override
	public void WmsBreedputMsg(Breed breed, String categoryIds,ApiFlagEnums apiFlag) {
		WmsBreedDto wmsBreed = new WmsBreedDto();
		wmsBreed.setBreedCode(breed.getBreedCode());
		wmsBreed.setBreedName(breed.getBreedName());
		wmsBreed.setBreedCName(breed.getBreedCname());
		//根据品种的计量单位code查对应的字典value
		List<DictInfo> dictList = dictInfoDao.selectDictByCode(breed.getBreedCountUnit());
		if(dictList != null && dictList.size()>0)
			wmsBreed.setBreedCountUnit(((DictInfo)dictList.get(0)).getDictValue());
		wmsBreed.setBreedStandardLevel(breed.getBreedStandardLevel());
		wmsBreed.setBreedPlace(breed.getBreedPlace());
		wmsBreed.setBreedExtend(breed.getBreedExtend());
		Long userId = null;
		if(apiFlag.equals(ApiFlagEnums.BREED_ADD)){
			userId = breed.getCreater();
		}
		else{
			userId= breed.getUpdater();
		}
		if(userId!= null){
			
		 BossUser bossUser = bossUserDao.findBossUserByUserId(userId.toString());
		 
		   wmsBreed.setCreater(bossUser!=null?bossUser.getUserCode():null);
		}
		wmsBreed.setCreateTime(TimeUtil.getTimeShowByTimePartten(breed.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
		List<String> categoryNameList = new ArrayList<String>();
		if(StringUtils.isNotEmpty(categoryIds)){
			String[] categoryIdArray = categoryIds.split(",");
			for(String categoryId:categoryIdArray){
				if(StringUtils.isNotEmpty(categoryId) && StringUtils.isNotBlank(categoryId)){
					Categorys category = categorysService.getCategorysById(Long.valueOf(categoryId));
					categoryNameList.add(category.getCategorysName());
				}
			}
			String[] strings = new String[categoryNameList.size()];
			wmsBreed.setCategoryNames(categoryNameList.toArray(strings));
		}
		wmsBreed.setStatus(breed.getStatus().intValue());
		Gson gson = new Gson();
		 WmsMessageWrapper<String> wrapper = new WmsMessageWrapper<String>();
		 wrapper.setData(gson.toJson(wmsBreed));
//		 wrapper.setDataId(breed.getBreedId().toString());
		 wrapper.setDataId(breed.getBreedCode());  //update by fanyuna data_id设置为品种编码
		 String breedGsonStr = gson.toJson(wrapper);
		 logger.info("调用WMS "+apiFlag+" 接口，请求参数的json串："+breedGsonStr);
		 //将品种参数组成的json串put到生产者的消息中
		 RabbitmqProducerManager.getInstance().putMsgForApi(apiFlag, breedGsonStr);
		 //String respStr = WmsApiCommon.wmsEncAndSign(WmsApiCommon.WMS_BREED_ADD_URL, breedGsonStr);
	}


	@Override
	public Breed selectBreedByCode(String breedCode) {
		Breed breedTmp = new Breed();
			breedTmp.setBreedCode(breedCode);
			/* 品种code只要被使用过（即不管是否已被删除），都不能再次使用 */
			//breedTmp.setStatus(Short.valueOf("1"));//有效的
		List<Breed> breedList = breedDao.selectList(breedTmp);
		if(breedList != null && breedList.size()>0){
			return breedList.get(0);
		}
		return null;
	}
	
	@Override
	public Breed selectBreedByNameIgnoreStatus(String breedName) {
		Breed breedTmp = new Breed();
		breedTmp.setBreedName(breedName);
		List<Breed> breedList = breedDao.selectList(breedTmp);
		return CollectionUtils.isEmpty(breedList)?null:breedList.get(0);
	}

	@Override
	public Breed findBreedByBreedName(String breedName) {
		return breedDao.selectBreedByBreedName(breedName);
	}
	
	@Override
	public List<Breed> findBreedsByName(String breedName) {
		return breedDao.selectBreedsByName(breedName);
	}


	@Override
	public List<Breed> selectBreedByKeyword(String breedName) {
		return breedDao.selectBreedByKeyword(breedName);
	}
}
