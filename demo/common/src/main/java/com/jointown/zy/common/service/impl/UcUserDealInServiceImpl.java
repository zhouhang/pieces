package com.jointown.zy.common.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.jointown.zy.common.dao.UcUserBreedDao;
import com.jointown.zy.common.dao.UcUserScopeDao;
import com.jointown.zy.common.dto.UcUserScopeDto;
import com.jointown.zy.common.exception.ErrorRepository;
import com.jointown.zy.common.model.Breed;
import com.jointown.zy.common.model.UcUserBreed;
import com.jointown.zy.common.model.UcUserScope;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.service.UcUserDealInService;
import com.jointown.zy.common.util.BeanUtil;
import com.jointown.zy.common.vo.MessageVo;
import com.jointown.zy.common.vo.UcUserVo;

/**
 * @ClassName: UcUserDealInServiceImpl
 * @Description: 经营信息service
 * @Author: ldp
 * @Date: 2015年10月19日
 * @Version: 1.0
 */
@Service
	
public class UcUserDealInServiceImpl implements UcUserDealInService {
	private static final Logger log = LoggerFactory.getLogger(UcUserDealInServiceImpl.class);
	
	@Autowired
	private UcUserScopeDao ucUserScopeDao;
	@Autowired
	private UcUserBreedDao ucUserBreedDao;

	@Override
	public String addUserDealInfo(UcUserScopeDto ucUserScopeDto) throws Exception {
		JsonObject json = new JsonObject();
		//参数校验
		MessageVo mvo = new MessageVo();
		mvo = validate(ucUserScopeDto);
		log.info("UcUserDealInServiceImpl.addUserDealInfo validate:" + mvo.isOk());
		if (!mvo.isOk()) {
			json.addProperty("status", "no");
			json.addProperty("errorMsg", BeanUtil.objectToJson(mvo.getErrorMessages()));
			return json.toString();
		}
		//创建实体model
		UcUserScope ucUserScope = new UcUserScope();
		ucUserScope.setUserId(getUcUserVo().getUserId());
		ucUserScope.setDealType(Integer.parseInt(ucUserScopeDto.getDealType()));
		ucUserScope.setDealRole(Integer.parseInt(ucUserScopeDto.getDealRole()));
		ucUserScope.setScale(Integer.parseInt(ucUserScopeDto.getScale()));
		ucUserScope.setProvinceCode(ucUserScopeDto.getProvinceCode());
		ucUserScope.setCityCode(ucUserScopeDto.getCityCode());
		ucUserScope.setAddress(StringUtils.isNotBlank(ucUserScopeDto.getAddress())?ucUserScopeDto.getAddress().trim():"");
		ucUserScope.setAreaCode(StringUtils.isNotBlank(ucUserScopeDto.getAreaCode())?ucUserScopeDto.getAreaCode().trim():"");
		ucUserScope.setFax(StringUtils.isNotBlank(ucUserScopeDto.getFax())?ucUserScopeDto.getFax().trim():"");
		ucUserScope.setZipCode(StringUtils.isNotBlank(ucUserScopeDto.getZipCode())?ucUserScopeDto.getZipCode().trim():"");
		ucUserScope.setCreateTime(new Date());
		ucUserScope.setUpdateTime(new Date());
		
		//判断经营信息是否为空，如果经营信息为空执行插入操作，否则执行更新操作
		UcUserScope userScope = selectUcUserScope();
		if (userScope == null) {
			int flag = ucUserScopeDao.addUcUserScope(ucUserScope);
			json.addProperty("status", flag);
			//add品种信息
			if(flag == 1) {
				addBreed(ucUserScopeDto.getBreeds());
				UcUserVo user = (UcUserVo)SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_UC.getValue());
				user.setHasScope(Boolean.TRUE);
				SecurityUtils.getSubject().getSession().setAttribute(RedisEnum.SESSION_USER_UC.getValue(), user);
			}
			
			return json.toString();
		}
		//更新操作
		ucUserScope.setScopeId(userScope.getScopeId());
		int upFlag = ucUserScopeDao.updateUcUserScope(ucUserScope);
		if(upFlag == 1) 
			delUcUser(ucUserScopeDto.getBreeds());
		json.addProperty("status", upFlag);
		return json.toString();
	}
	
	@Override
	public String addAnyUserDealInfo(UcUserScopeDto ucUserScopeDto)
			throws Exception {
		JsonObject json = new JsonObject();
		//参数校验
		MessageVo mvo = new MessageVo();
		mvo = validate(ucUserScopeDto);
		log.info("UcUserDealInServiceImpl.addAnyUserDealInfo validate:" + mvo.isOk());
		if (!mvo.isOk()) {
			json.addProperty("status", "no");
			json.addProperty("errorMsg", BeanUtil.objectToJson(mvo.getErrorMessages()));
			return json.toString();
		}
		//创建实体model
		UcUserScope ucUserScope = new UcUserScope();
		ucUserScope.setUserId(Long.valueOf(ucUserScopeDto.getUserId()));
		ucUserScope.setDealType(Integer.parseInt(ucUserScopeDto.getDealType()));
		ucUserScope.setDealRole(Integer.parseInt(ucUserScopeDto.getDealRole()));
		ucUserScope.setScale(Integer.parseInt(ucUserScopeDto.getScale()));
		ucUserScope.setProvinceCode(ucUserScopeDto.getProvinceCode());
		ucUserScope.setCityCode(ucUserScopeDto.getCityCode());
		ucUserScope.setAddress(ucUserScopeDto.getAddress());
		ucUserScope.setAreaCode(ucUserScopeDto.getAreaCode());
		ucUserScope.setFax(ucUserScopeDto.getFax());
		ucUserScope.setZipCode(ucUserScopeDto.getZipCode());
		ucUserScope.setCreateTime(new Date());
		ucUserScope.setUpdateTime(new Date());
		
		//判断经营信息是否为空，如果经营信息为空执行插入操作，否则执行更新操作
		UcUserScope userScope = selectUcUserScopeByUserId(ucUserScope.getUserId());
		if (userScope == null) {
			int flag = ucUserScopeDao.addUcUserScope(ucUserScope);
			json.addProperty("status", flag);
			//add品种信息
			if(flag == 1) 
				addBreedByUserId(ucUserScopeDto.getBreeds(),Long.valueOf(ucUserScopeDto.getUserId()));
			return json.toString();
		}
		//更新操作
		ucUserScope.setScopeId(userScope.getScopeId());
		int upFlag = ucUserScopeDao.updateUcUserScope(ucUserScope);
		if(upFlag == 1) 
			delUcUserByUserId(ucUserScopeDto.getBreeds(),Long.valueOf(ucUserScopeDto.getUserId()));
		json.addProperty("status", upFlag);
		return json.toString();
	}
	
	
	/**
	 * @Description: 获取会员用户信息
	 * @Author: ldp
	 * @Date: 2015年10月19日
	 * @return
	 */
	public UcUserVo getUcUserVo(){
		Subject subject = SecurityUtils.getSubject();
		UcUserVo ucUserVo = (UcUserVo) subject.getSession().getAttribute(RedisEnum.SESSION_USER_UC.getValue());
		return ucUserVo;
	}
	
	/**
	 * @Description: 各参数校验
	 * @Author: ldp
	 * @Date: 2015年10月19日
	 * @param ucUserScopeDto
	 * @return
	 */
	public MessageVo validate(UcUserScopeDto ucUserScopeDto){
		MessageVo mvo = new MessageVo();
		mvo.setOk(true);
		if (null == ucUserScopeDto) {
			mvo.setOk(false);
			mvo.addError("error1",ErrorRepository.UC_DEALIN_INFO_NOT_NULL.getMessage());
			return mvo;
		}
		if (StringUtils.isBlank(ucUserScopeDto.getDealType())) {
			mvo.setOk(false);
			mvo.addError("dealType",ErrorRepository.UC_DEALIN_DEALTYPE_NOT_NULL.getMessage());
		}
		if (StringUtils.isBlank(ucUserScopeDto.getDealRole())) {
			mvo.setOk(false);
			mvo.addError("dealRole",ErrorRepository.UC_DEALIN_DEALROLE_NOT_NULL.getMessage());
		}
		if (validateBreeds(ucUserScopeDto.getBreeds()) == false) {
			mvo.setOk(false);
			mvo.addError("breed", ErrorRepository.UC_DEALIN_BREED_NOT_NULL.getMessage());
		}
		
		if (StringUtils.isBlank(ucUserScopeDto.getScale())) {
			mvo.setOk(false);
			mvo.addError("scale",ErrorRepository.UC_DEALIN_SCALE_NOT_NULL.getMessage());
		}
		if (StringUtils.isBlank(ucUserScopeDto.getProvinceCode())) {
			mvo.setOk(false);
			mvo.addError("provinceCode",ErrorRepository.UC_DEALIN_PROVINCECODE_NOT_NULL.getMessage());
		}
		if (StringUtils.isBlank(ucUserScopeDto.getCityCode())) {
			mvo.setOk(false);
			mvo.addError("cityCode",ErrorRepository.UC_DEALIN_CITYCODE_NOT_NULL.getMessage());
		}
		if (StringUtils.isBlank(ucUserScopeDto.getAddress())) {
			mvo.setOk(false);
			mvo.addError("address", ErrorRepository.UC_DEALIN_ADDRESS_NOT_NULL.getMessage());
		}
		String zipCodeReg = "^[1-9]\\d{5}(?!\\d)$";
		//校验邮政编码
		if (StringUtils.isNotBlank(ucUserScopeDto.getZipCode())) {
			if (!Pattern.compile(zipCodeReg).matcher(ucUserScopeDto.getZipCode().trim()).matches()) {
				mvo.setOk(false);
				mvo.addError("zipCode", ErrorRepository.UC_DEALIN_ZIPCODE_ERROR.getMessage());
			}
		}
		return mvo;
	}
	
	/**
	 * @Description: 验证品种,只要要输入一个
	 * @Author: ldp
	 * @Date: 2015年10月27日
	 * @param breeds
	 * @return
	 */
	public boolean validateBreeds(List<UcUserBreed> breeds){
		if (breeds == null || breeds.size() == 0) {
			return false;
		}
		int listLen = breeds.size();
		int count = 0;
		for(int i = 0;i<listLen;i++){
			if (StringUtils.isBlank(breeds.get(i).getBreedName())) {
				count++;
			}
		}
		if (count == listLen) {
			return false;
		}
		return true;
	}
	
	/**
	 * @Description: 获取经营信息
	 * @Author: ldp
	 * @Date: 2015年10月19日
	 * @return
	 * @throws Exception
	 */
	public UcUserScope selectUcUserScope() throws Exception {
		return ucUserScopeDao.selectUcUserScopeById(getUcUserVo().getUserId());
	}
	
	/**
	 * @Description: 获取经营信息
	 * @Author: ff
	 * @Date: 2015年10月21日
	 * @return
	 * @throws Exception
	 */
	public UcUserScope selectUcUserScopeByUserId(Long userId) throws Exception {
		return ucUserScopeDao.selectUcUserScopeById(userId);
	}

	/**
	 * @Description: 模糊查询品种信息
	 * @Author: Calvin.wh
	 * @Date: 2015-10-19
	 * @return breedId  breedName
	 */
	public List<Breed> getBreeds(String param) {
		return ucUserScopeDao.getBreeds(param);
	}
	
	/**
	 * @Description: 品种去重
	 * 1.品种名为空不存 2.品种名不存在则按品种名去重 3.品种名存在 按品种id去重
	 * @Author: Calvin.wh
	 * @Date: 2015-11-2
	 * @param breeds
	 * @throws Exception
	 */
	public void addBreed(List<UcUserBreed> breeds) throws Exception {
		Map<Object, String> map = new HashMap<Object, String>();
		for (int i = 0; i < breeds.size(); i++) {
			//品种名为空不做保存
			if (!StringUtils.isBlank(breeds.get(i).getBreedName())) {
				UcUserBreed ub = new UcUserBreed();
				ub.setUserId(getUcUserVo().getUserId());
				ub.setBreedName(breeds.get(i).getBreedName());
				ub.setStatus(1);
				ub.setCreateTime(new Date());
				ub.setUpdateTime(new Date());
				//若品种不存在品种库,按品种名去重
				if (null == breeds.get(i).getBreedId()) {
					if(!map.containsKey(breeds.get(i).getBreedName())){
						ucUserBreedDao.addUcUserBreed(ub);
						map.put(breeds.get(i).getBreedName(), breeds.get(i).getBreedName()); 
					}
				} else {
					//品种存在,按品种id重
					if (!map.containsKey(breeds.get(i).getBreedId())) {
						ub.setBreedId(breeds.get(i).getBreedId());
						ucUserBreedDao.addUcUserBreed(ub);
						map.put(breeds.get(i).getBreedId(), breeds.get(i).getBreedName());
					}
				}
			}
		}
	}
	
	public void addBreedByUserId(List<UcUserBreed> breeds,Long userId) throws Exception {
		Map<Object, String> map = new HashMap<Object, String>();
		for (int i = 0; i < breeds.size(); i++) {
			if (!StringUtils.isBlank(breeds.get(i).getBreedName())) {
				UcUserBreed ub = new UcUserBreed();
				ub.setUserId(userId);
				ub.setBreedName(breeds.get(i).getBreedName());
				ub.setStatus(1);
				ub.setCreateTime(new Date());
				ub.setUpdateTime(new Date());
				//如果只有品种名 , 没有实际品种库
				if (null == breeds.get(i).getBreedId()) {
					if(!map.containsKey(breeds.get(i).getBreedName())){
						ucUserBreedDao.addUcUserBreed(ub);
						map.put(breeds.get(i).getBreedName(), breeds.get(i).getBreedName()); 
					}
				} else {
					// 去重
					if (!map.containsKey(breeds.get(i).getBreedId())) {
						ub.setBreedId(breeds.get(i).getBreedId());
						ucUserBreedDao.addUcUserBreed(ub);
						map.put(breeds.get(i).getBreedId(), breeds.get(i).getBreedName());
					}
				}
			}
		}
	}
	/**
	 * @Description: 逻辑删除当前用户下的品种信息 新增最新品种信息
	 * @Author: Calvin.wh
	 * @Date: 2015-10-21
	 * @param breeds
	 * @throws Exception
	 */
	public void delUcUser(List<UcUserBreed> breeds) throws Exception{
		UcUserBreed breed = new UcUserBreed();
		breed.setUserId(getUcUserVo().getUserId());
		//获取当前用户下所有品种
		List<UcUserBreed> breedList = ucUserBreedDao.getUcUserBreed(breed);
		if(null != breedList && breedList.size() > 0){
			for(UcUserBreed current : breedList){
				//物理删除当前用户下的品种
				current.setStatus(0);
				ucUserBreedDao.delUcUserBreed(current);
			}
		}
		//插入最新的品种信息
		addBreed(breeds);
	}

	@Override
	public UcUserScope getUcUserScope(Long userId) throws Exception {
		return ucUserScopeDao.selectUcUserScopeById(userId);
	}
	
	public void delUcUserByUserId(List<UcUserBreed> breeds,Long userId) throws Exception{
		UcUserBreed breed = new UcUserBreed();
		breed.setUserId(userId);
		//获取当前用户下所有品种
		List<UcUserBreed> breedList = ucUserBreedDao.getUcUserBreed(breed);
		if(null != breedList && breedList.size() > 0){
			for(UcUserBreed current : breedList){
				//物理删除当前用户下的品种
				current.setStatus(0);
				ucUserBreedDao.delUcUserBreed(current);
			}
			//插入最新的品种信息
			addBreedByUserId(breeds,userId);
		}
	}
}
