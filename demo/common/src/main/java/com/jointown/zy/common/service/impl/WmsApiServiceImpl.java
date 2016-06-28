package com.jointown.zy.common.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.jointown.zy.common.constant.ConfigConstant;
import com.jointown.zy.common.constant.MessageConstant;
import com.jointown.zy.common.dao.BossUserDao;
import com.jointown.zy.common.dao.BreedDao;
import com.jointown.zy.common.dao.BusiListingDao;
import com.jointown.zy.common.dao.BusiListingLogDao;
import com.jointown.zy.common.dao.BusiOrderDao;
import com.jointown.zy.common.dao.BusiOrderDepositDao;
import com.jointown.zy.common.dao.BusiOrderLogDao;
import com.jointown.zy.common.dao.BusiOrderSalesmanDao;
import com.jointown.zy.common.dao.BusiQualityDao;
import com.jointown.zy.common.dao.BusiQualityInfoDao;
import com.jointown.zy.common.dao.BusiQualityInfoNewDao;
import com.jointown.zy.common.dao.BusiQualityItemDao;
import com.jointown.zy.common.dao.BusiQualityPicDao;
import com.jointown.zy.common.dao.BusiWareHouseDao;
import com.jointown.zy.common.dao.BusiWhlistDao;
import com.jointown.zy.common.dao.DictInfoDao;
import com.jointown.zy.common.dao.UcUserDao;
import com.jointown.zy.common.dto.WmsNewWlInfo;
import com.jointown.zy.common.dto.WmsOldWlInfo;
import com.jointown.zy.common.dto.WmsPicInfoDto;
import com.jointown.zy.common.dto.WmsQualityDto;
import com.jointown.zy.common.dto.WmsQualityItemDto;
import com.jointown.zy.common.dto.WmsWareHouseDto;
import com.jointown.zy.common.dto.WmsWlDto;
import com.jointown.zy.common.dto.WmsWlSplitDto;
import com.jointown.zy.common.enums.ApiFlagEnums;
import com.jointown.zy.common.enums.BusiListingFlagEnum;
import com.jointown.zy.common.enums.BusiOrderDepositTypeEnum;
import com.jointown.zy.common.enums.BusiOrderSplitEnum;
import com.jointown.zy.common.enums.BusiOrderStateEnum;
import com.jointown.zy.common.enums.BusiOrderTypeEnum;
import com.jointown.zy.common.enums.BusiParamEnum;
import com.jointown.zy.common.enums.BusiWhlistStateEnum;
import com.jointown.zy.common.enums.BusinessLogEnum;
import com.jointown.zy.common.enums.SolrContentTypeEnum;
import com.jointown.zy.common.enums.SolrOperationTypeEnum;
import com.jointown.zy.common.exception.ErrorMessage;
import com.jointown.zy.common.messageconfig.MessageConfigManage;
import com.jointown.zy.common.model.BossUser;
import com.jointown.zy.common.model.Breed;
import com.jointown.zy.common.model.BusiListing;
import com.jointown.zy.common.model.BusiOrder;
import com.jointown.zy.common.model.BusiOrderSalesman;
import com.jointown.zy.common.model.BusiQualityInfoNew;
import com.jointown.zy.common.model.BusiQualityItem;
import com.jointown.zy.common.model.BusiQualityinfo;
import com.jointown.zy.common.model.BusiQualitypic;
import com.jointown.zy.common.model.BusiWareHouse;
import com.jointown.zy.common.model.BusiWhlist;
import com.jointown.zy.common.model.DictInfo;
import com.jointown.zy.common.model.SyncDataLog;
import com.jointown.zy.common.model.UcUser;
import com.jointown.zy.common.rabbitmq.RabbitmqProducerManager;
import com.jointown.zy.common.service.BaseService;
import com.jointown.zy.common.service.BreedService;
import com.jointown.zy.common.service.BusiWareHouseService;
import com.jointown.zy.common.service.BusiWhlistService;
import com.jointown.zy.common.service.CategorysService;
import com.jointown.zy.common.service.SyncDataLogService;
import com.jointown.zy.common.service.WmsApiService;
import com.jointown.zy.common.task.EmailTaskSend;
import com.jointown.zy.common.util.BigDecimalUtil;
import com.jointown.zy.common.util.GetEmailContext;
import com.jointown.zy.common.util.GetMessageContext;
import com.jointown.zy.common.util.GsonFactory;
import com.jointown.zy.common.util.SftpConfigInfo;
import com.jointown.zy.common.util.SysLogUtil;
import com.jointown.zy.common.util.TimeUtil;
import com.jointown.zy.common.vo.BusiWhlistVo;
import com.jointown.zy.common.vo.MessageVo;
import com.jointown.zy.common.wms.WmsApiCommon;

@Service
public class WmsApiServiceImpl extends BaseService implements WmsApiService{
	private static final Logger logger = LoggerFactory.getLogger(WmsApiServiceImpl.class);
	@Resource
	private UcUserDao ucUserDao;
	@Resource
	private BreedService breedService;
	@Resource
	private CategorysService categorysService;
	@Autowired
	private BusiWhlistDao busiWhlistDao;
	@Autowired
	private BusiQualityDao busiQualityDao;
	@Autowired
	private BusiQualityPicDao busiQualityPicDao;
	@Autowired
	private BusiWareHouseDao busiWareHouseDao;
	@Autowired
	private BusiWhlistService busiWhlistService;
	@Autowired
	private BusiWareHouseService busiWareHouseService;
	@Autowired
	private BusiQualityInfoDao busiQualityInfoDao;
	@Autowired
	private SftpConfigInfo sftpConfigInfo;
//	@Autowired
//	private WmsApi wmsApi;
	@Autowired
	private BusiOrderDao busiOrderDao;
	@Autowired
	private BusiOrderLogDao busiOrderLogDao;
	@Autowired
	private BusiQualityInfoNewDao busiQualityInfoNewDao;
	@Autowired
	private BusiQualityItemDao busiQualityItemDao;
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	@Autowired
	private SyncDataLogService syncDataLogService;
	@Autowired 
	private  BusiOrderDepositDao busiOrderDepositDao;
	@Autowired
	private BusiListingDao busiListingDao;
	@Autowired
	private BossUserDao bossUserDao;
	@Autowired
	private MessageConfigManage messageConfigManage;
	@Autowired
	private BusiOrderSalesmanDao busiOrderSalesmanDao;
	@Autowired
	private BreedDao breedDao;
	@Autowired
	private DictInfoDao dictInfoDao;
	
	
	/**
	 * 挂牌操作日志数据dao
	 * @date 2015.8.27
	 */
	@Autowired
	private BusiListingLogDao busiListingLogDao;
	
	/**
	 * 将json转换的对象信息保存在数据库中
	 * @param wlDto 将json转换的对象
	 * update by fanyuna 2015.08.27 仓单新增成功后记仓单日志
	 * @return
	 */
	public int syncWlInfo(final WmsWlDto wlDto,final Map<String,String> picInfo){
		Integer cou =getTransactionTemplate().execute(new TransactionCallback<Integer>() {
		@Override
		public Integer doInTransaction(TransactionStatus status){
		try{
		if(wlDto!=null){
				//判断是否存在
			   BusiWhlistVo wl = busiWhlistService.findBusiWhlistByWlId(wlDto.getWlId());
			   if(wl!=null){
				   return 1;
			   }
				BusiWhlist busiWhlist = wlDtoToModel(wlDto);
			   //保存仓单基本信息
				int num = busiWhlistDao.insertBusiWhlist(busiWhlist);
				if(num>0){
				//保存质检信息 纵表
				WmsQualityDto qualityInfo = wlDto.getQualityInfo();
				if(qualityInfo !=null){
					//保存质检信息
					saveWlQuality(qualityInfo,busiWhlist);
					
					//保存质检图片
				  if(StringUtils.isNotBlank(qualityInfo.getQualityPic())){
					  //根据WMS传的图片地址下载并上传到文件服务器上，数据库保存的是文件服务器上的相对地址
					
					saveWlPic(busiWhlist.getWlId(),picInfo.get("7"),Short.valueOf("7"));//质检图片
				  }
				}
				
				//保存图片
				List<WmsPicInfoDto> picInfoList = wlDto.getPicInfo();
				for(WmsPicInfoDto pic:picInfoList){
					if(pic!=null){
						saveWlPic(busiWhlist.getWlId(),picInfo.get(pic.getPicType()+""),pic.getPicType()!=null?pic.getPicType().shortValue():null);//质检图片
					}
				}
				
				//update by fanyuna 2015.08.27 仓单新增成功后记仓单日志
				busiWhlistService.addBusiWhlistLog(busiWhlist, BusinessLogEnum.WHLIST_ADD.getCodeName(), null, BusinessLogEnum.WHLIST_ADD.getCode(), false);
				
				}
				return new Integer(num);
				
		}
		    return 0;
		}catch(Exception e){
			logger.error("仓单新增同步接口失败："+e.getMessage(),e);
			status.setRollbackOnly();  //回滚事务
			return 0;
		}
		}
		});
		
		return cou.intValue();
	}

	/**
	 * 将Wms仓单DTO转换成BusiWhlist Model对象
	 * @param wlDto
	 * @return
	 */
   public BusiWhlist wlDtoToModel(WmsWlDto wlDto){
	   BusiWhlist busiWhlist = new BusiWhlist();
		//仓单号
			busiWhlist.setWlId(wlDto.getWlId());
			//户主
		if(StringUtils.isNotBlank(wlDto.getUserName())){
			UcUser user = ucUserDao.findUcUserByUserName(wlDto.getUserName());
			busiWhlist.setUserId(user!=null?user.getUserId():null);
			busiWhlist.setAccount(wlDto.getUserName());
		}
			//品种
			String breedCode = wlDto.getBreedID();
			if(StringUtils.isNotBlank(breedCode)){
				Breed breed= breedService.selectBreedByCode(breedCode);
				if(breed != null){
				  busiWhlist.setBreedCode(breed.getBreedId());
				//计量单位
				//  busiWhlist.setWlUnit(breed.getBreedCountUnit());
				//类目  根据品种获取关联的 形态分类下 的类目
				/*List<Categorys> category = categorysService.getCategoryByName(breed.getBreedId());
				if(category !=null && category.size()>0){
					busiWhlist.setCategoryId(category.get(0)!=null?category.get(0).getId():null);
				}*/
				}
			}
		//仓单单位 从WMS获取，因为WMS系统 仓单单位可以用户自己输入	
		if(StringUtils.isNotBlank(wlDto.getOrderWeightUnit())){
			DictInfo dictInfo = dictInfoDao.getDictByValue(wlDto.getOrderWeightUnit());
			if(dictInfo!=null){
				busiWhlist.setWlUnit(dictInfo.getDictCode());
			}else{
				DictInfo dictInfoTmp = new DictInfo();
				Long nextId = dictInfoDao.getMaxId()+1;
					dictInfoTmp.setId(nextId);
					dictInfoTmp.setDictCode("weight_unit_"+nextId);
					dictInfoTmp.setDictType("weight_unit");
					dictInfoTmp.setDictValue(wlDto.getOrderWeightUnit());
					dictInfoTmp.setDictDescr(wlDto.getOrderWeightUnit());
					dictInfoTmp.setCreater(8l);
					dictInfoTmp.setCreateTime(new Date());
					dictInfoTmp.setStatus(new Short("1"));
					dictInfoDao.insert(dictInfoTmp);
				
					busiWhlist.setWlUnit("weight_unit_"+nextId);
					
			}
		}
			busiWhlist.setWmiocode(wlDto.getWmioCode());
			busiWhlist.setSkunumber(wlDto.getSkuNumber());
			busiWhlist.setStorename(wlDto.getStoreName());
			busiWhlist.setSignatureuser(wlDto.getSignatureUser());
			busiWhlist.setSignaturedate(StringUtils.isNotBlank(wlDto.getSignatureDate())?TimeUtil.parseYMD(wlDto.getSignatureDate()):null);
			busiWhlist.setOutednumber(wlDto.getOutedNumber());
			busiWhlist.setContractname(wlDto.getContractName());
			busiWhlist.setPledgename(wlDto.getPledgeName());
			busiWhlist.setStoragetermstart(StringUtils.isNotBlank(wlDto.getStorageTermStart())?TimeUtil.parseYMD(wlDto.getStorageTermStart()):null);
			busiWhlist.setStoragetermend(StringUtils.isNotBlank(wlDto.getStorageTermEnd())?TimeUtil.parseYMD(wlDto.getStorageTermEnd()):null);
			busiWhlist.setStoragefee(wlDto.getStorageFee());
			busiWhlist.setLossstandard(wlDto.getLossStandard());
			
			//仓库编号
			busiWhlist.setWareHouseId(wlDto.getWareHouseID());
			//仓单数量
			busiWhlist.setWlTotal(wlDto.getWltotal());
			//入库日期
			busiWhlist.setWlrkDate(StringUtils.isNotBlank(wlDto.getWlrkdate())?TimeUtil.parseYMDHMS(wlDto.getWlrkdate()):null);
			//产地
			busiWhlist.setOrigin(wlDto.getOrigin());
			//批次号
			busiWhlist.setBatch(wlDto.getBatch());
			//合同编号
			busiWhlist.setContractNum(wlDto.getContractNum());
			//区域  由于目前WMS系统未给区域信息，先指定汉阳区，若给了 来为空时用传过来的区域
			String code = "420105";
			if(StringUtils.isNotBlank(wlDto.getAreaID()))
				code = wlDto.getAreaID();
			busiWhlist.setAreaId(code);
			//包装方式
			busiWhlist.setPackingWay(wlDto.getPackingWay());
			
			//状态
			busiWhlist.setWlFlag(0);
			if(StringUtils.isNotBlank(wlDto.getWlstate())){
				//有效  即未质押
				if(Integer.valueOf(wlDto.getWlstate())==1){
					busiWhlist.setWlState(Integer.valueOf(BusiWhlistStateEnum.PLEDGED.getId()));
				}
				//冻结 质押
				if(Integer.valueOf(wlDto.getWlstate())==2){
					busiWhlist.setWlState(Integer.valueOf(BusiWhlistStateEnum.NOPLEDGED.getId()));
				}
				//完成 已出库 无效仓单
				if(Integer.valueOf(wlDto.getWlstate())==3){
					busiWhlist.setWlFlag(1);
				}
			}
			
			//业务员ID
			if(StringUtils.isNotBlank(wlDto.getSalesmanUserCode())){
				BossUser user = bossUserDao.findBossUserByUserCode(wlDto.getSalesmanUserCode());
				busiWhlist.setCreaterId(user!=null?new Long(user.getUserId()):null);
			}
			
			return busiWhlist;
   }
   
   /**
    * 保存质检信息
    * @updater fanyuna 2015.07.20 仓单可以跳过质检信息，即质检信息可以为空，但等级规格在质检信息里面且等级规格必填
    * @param qualityInfo  WMS仓单质检DTO
    * @param busiWhlist 仓单基本信息Model
    */
   @Transactional
   public void saveWlQuality(WmsQualityDto qualityInfo,BusiWhlist busiWhlist){
		if(qualityInfo !=null){
			/*Map<String,String> itemsMap = qualityInfo.getQualityItems();
			for(Entry<String, String> entry: itemsMap.entrySet()){
				BusiQuality busiQualityInfo = new BusiQuality();
				//仓单ID
				busiQualityInfo.setWlid(busiWhlist.getWlId());
				//质检日期
				busiQualityInfo.setQualityTime(qualityInfo.getQualityCheckDate()!=null?TimeUtil.parseYMD(qualityInfo.getQualityCheckDate()):null);
				//报告日期
				busiQualityInfo.setReportDate(qualityInfo.getQualityReportDate()!=null?TimeUtil.parseYMD(qualityInfo.getQualityReportDate()):null);
				//质检项名称
				busiQualityInfo.setQualityItemName(entry.getKey());
				//质检结果
				busiQualityInfo.setQualityItemResult(entry.getValue());
				//质检人
				busiQualityInfo.setQualityPerson(qualityInfo.getQualityPerson());
				//质检规格
				busiQualityInfo.setGrade(qualityInfo.getGrade());
				//检品数量
				busiQualityInfo.setCheckNum(qualityInfo.getCheckNum());
				busiQualityInfo.setCreateTime(new Date());
				busiQualityDao.insert(busiQualityInfo);
			}*/
			BusiQualityInfoNew qualityNew = new BusiQualityInfoNew();
				qualityNew.setQualityPerson(qualityInfo.getQualityPerson());
				qualityNew.setQualityTime(qualityInfo.getQualityCheckDate()!=null?TimeUtil.parseYMD(qualityInfo.getQualityCheckDate()):null);
				qualityNew.setCheckNumber(qualityInfo.getCheckNum());
				qualityNew.setGrade(qualityInfo.getGrade());
				qualityNew.setLevelEva(qualityInfo.getLevelEva());
				qualityNew.setReportDate(qualityInfo.getQualityReportDate()!=null?TimeUtil.parseYMD(qualityInfo.getQualityReportDate()):null);
				qualityNew.setWlid(busiWhlist.getWlId());
				qualityNew.setCreateTime(new Date());
				//2015.07.01  增加QA质检描述字段
				qualityNew.setQaDesc(qualityInfo.getQADesc());
				int num = busiQualityInfoNewDao.insert(qualityNew);
				if(num>0){
				  List<WmsQualityItemDto> qualityItemList = qualityInfo.getQualityItemsInfo();
				 if(qualityItemList!=null){
				  for(WmsQualityItemDto item:qualityItemList){
					saveQualityItem(item,busiWhlist.getWlId(),qualityNew.getQualityInfoId());
				  }
				}
				}
		}
   }
   
   public void saveQualityItem(WmsQualityItemDto item,String wlId,Long qualityInfoId){
	   BusiQualityItem qualityItem = new BusiQualityItem();
		qualityItem.setQualityInfoId(qualityInfoId);
		qualityItem.setWlid(wlId);
		qualityItem.setQualityItemName(item.getQualityItem());
		qualityItem.setQualityItemType(item.getQualityType());
		qualityItem.setQualityItemStandard(item.getQualityStandard());
		qualityItem.setQualityItemResult(item.getQualityResult());
		qualityItem.setCreateTime(new Date());
		qualityItem.setUpdateTime(new Date());
		busiQualityItemDao.insert(qualityItem);
   }
   
   /**
    * 
    * @param qualityInfo
    * @param busiWhlist
    */
   public BusiQualityinfo getWlQualityInfo(WmsQualityDto qualityInfo,BusiWhlist busiWhlist){
	 BusiQualityinfo busiQualityInfo = new BusiQualityinfo();
	 /*if(qualityInfo !=null){
	   
	   	//仓单ID
			busiQualityInfo.setWlid(busiWhlist.getWlId());
			//质检日期
			busiQualityInfo.setAcceptchecktime(qualityInfo.getQualityCheckDate()!=null?TimeUtil.parseYMD(qualityInfo.getQualityCheckDate()):null);
			//报告日期
			busiQualityInfo.setReportdate(qualityInfo.getQualityReportDate()!=null?TimeUtil.parseYMD(qualityInfo.getQualityReportDate()):null);
			//质检规格
			busiQualityInfo.setGrade(qualityInfo.getGrade());
			//检品数量
			busiQualityInfo.setNumberofjc(qualityInfo.getCheckNum());
			busiQualityInfo.setCreatetime(new Date());
			
				Map<String,String> itemsMap = qualityInfo.getQualityItems();
				for(Entry<String, String> entry: itemsMap.entrySet()){
					if(entry.getKey().indexOf("性状")!=-1){
						busiQualityInfo.setMainparacter(entry.getValue());
					}
					if(entry.getKey().indexOf("描述")!=-1){
						busiQualityInfo.setDetailed(entry.getValue());
					}
					if(entry.getKey().indexOf("水分")!=-1){
						busiQualityInfo.setWater(entry.getValue());
					}
					if(entry.getKey().indexOf("灰分")!=-1){
						busiQualityInfo.setAsh(entry.getValue());
					}
					if(entry.getKey().indexOf("二氧化硫")!=-1){
						busiQualityInfo.setSulfurdioxidein(entry.getValue());
					}
					if(entry.getKey().indexOf("含量")!=-1||entry.getKey().indexOf("测定")!=-1){
						busiQualityInfo.setContentcheck(entry.getValue());
					}
				}
				
	   }*/
	 return busiQualityInfo;
   }
   
   /**
    * 保存仓单图片信息
    * @param wlId 仓单ID
    * @param url 图片路径
    * @param index 图片类型
    */
   public void saveWlPic(String wlId,String url,Short index){
	   BusiQualitypic busiQualitypic = new BusiQualitypic();
		//仓单ID
		busiQualitypic.setWlid(wlId);
		//图片url
		busiQualitypic.setPath(url);
		busiQualitypic.setPicindex(index);
		busiQualityPicDao.insertBusiQualitypic(busiQualitypic);
   }
	
	@Override
	public int wmsWlUpdate(final WmsWlDto wlDto,final Map<String,String> updatePicInfo) {
	Integer cou = getTransactionTemplate().execute(new TransactionCallback<Integer>() {
		@Override
		public Integer doInTransaction(TransactionStatus status){
			/** 仓单基本信息修改*****start*********************/
			/**
			 * 2015.07.01  fanyuna
			 * 调用修改接口时，如果此用户或品种或仓单不存在，返回不存在错误编码（定为106），客户端需要将更新后的记录再调用新增接口
			 * 先查询，看此仓单是否存在   start
			 */
		 try{
			//判断是否存在
			  BusiWhlistVo wl = busiWhlistService.findBusiWhlistByWlId(wlDto.getWlId());
			  if(wl==null){
				  logger.info("调用仓单修改同步接口，仓单："+wlDto.getWlId()+"，不存在，返回106");
				   return 106;
			   }
			/**
			 * 先查询，看此仓单是否存在   end
			 */
			BusiWhlist busiWhlist = wlDtoToModel(wlDto);
			BusiWhlist bw = busiWhlistDao.selectWhlistByWlId(wlDto.getWlId());
			//如果仓单总量不为空，即总量要做修改，则可挂数量也要做修改
			if(wlDto.getWltotal()!=null){
//				busiWhlist.setWlSurplus(bw.getWlSurplus()-(bw.getWlTotal()-wlDto.getWltotal().doubleValue()));
				//出库数量=现有总量-剩余总量
				BigDecimal outQty=new BigDecimal(bw.getWlTotal()).subtract(new BigDecimal(wlDto.getWltotal()));
				//可挂数量=现有可挂数量-出库数量
				busiWhlist.setWlSurplus(new BigDecimal(bw.getWlSurplus()).subtract(outQty).doubleValue());
			}
			//仓单信息修改
			int num = busiWhlistDao.updateBusiWhlistByIdForWms(busiWhlist);
			/**仓单基本信息修改*****end*******************/
			if(num>0){
			 //update by fanyuna 2015.08.27 记仓单日志 start
				if(wlDto.getWltotal()!=null){ //出库修改
					busiWhlistService.addBusiWhlistLog(bw, null, BusinessLogEnum.WHLIST_OUT, bw.getWlTotal(),wlDto.getWltotal(),bw.getWlSurplus(),new BigDecimal(bw.getWlSurplus()).subtract(new BigDecimal(bw.getWlTotal()).subtract(new BigDecimal(wlDto.getWltotal()))));
				}
				else{ //非出库的修改即基本信息修改
					busiWhlistService.addBusiWhlistLog(bw, null, BusinessLogEnum.WHLIST_UPDATE);
				}
				//update by fanyuna 2015.08.27 记仓单日志 end
			/**质检信息修改（先删除，再修改）****start*********/
				
			WmsQualityDto qualityInfo = wlDto.getQualityInfo();
			if(qualityInfo !=null){
				//删除对应ID的质检信息
			  /*if(qualityInfo.getQualityItems()!=null && qualityInfo.getQualityItems().size()>0){
				busiQualityDao.deleteByWlID(wlDto.getWlId());
				saveWlQuality(qualityInfo,busiWhlist);
			  }*/
				BusiQualityInfoNew qualityNew = busiQualityInfoNewDao.selectQualityByWlId(wlDto.getWlId());
				if(qualityNew != null){
				BusiQualityInfoNew qualityNewTmp = new BusiQualityInfoNew();
					qualityNewTmp.setQualityInfoId(qualityNew.getQualityInfoId());
					qualityNewTmp.setCheckNumber(qualityInfo.getCheckNum());
					qualityNewTmp.setQualityPerson(qualityInfo.getQualityPerson());
					qualityNewTmp.setQualityTime(qualityInfo.getQualityCheckDate()!=null?TimeUtil.parseYMD(qualityInfo.getQualityCheckDate()):null);
					qualityNewTmp.setGrade(qualityInfo.getGrade());
					qualityNewTmp.setLevelEva(qualityInfo.getLevelEva());
					qualityNewTmp.setReportDate(qualityInfo.getQualityReportDate()!=null?TimeUtil.parseYMD(qualityInfo.getQualityReportDate()):null);
					qualityNewTmp.setWlid(wlDto.getWlId());
					qualityNewTmp.setUpdateTime(new Date());
					//2015.07.01 增加QA质检描述字段
					qualityNewTmp.setQaDesc(qualityInfo.getQADesc());
					busiQualityInfoNewDao.updateByPrimaryKeySelective(qualityNewTmp);
				//删除质检项信息，再保存	
				 List<WmsQualityItemDto> itemList = qualityInfo.getQualityItemsInfo();
				if(itemList!=null && itemList.size()>0){
					busiQualityItemDao.delItemByQualityId(wlDto.getWlId());
					 for(WmsQualityItemDto item:itemList){
							saveQualityItem(item,busiWhlist.getWlId(),qualityNew.getQualityInfoId());
						  }
				}
			}
				/**质检图片修改（先删除，后插入）*/
				//此处删除该仓单下所有图片（6个细节照+1个质检图片照）
//				busiQualityPicDao.deleteByWlID(wlDto.getWlId());
			  //若没有质检图片则不保存
			 if(StringUtils.isNotBlank(qualityInfo.getQualityPic())){
				 //删除质检图
				 busiQualityPicDao.deleteQualityPicByWlID(wlDto.getWlId());
				//update by fanyuna 注释掉，因为wms可以跳过质检，因此质检图片在新增同步时也可以不传
//				 int delQuaNum = busiQualityPicDao.deleteQualityPicByWlID(wlDto.getWlId());
//				 if(delQuaNum>0)  
				  saveWlPic(busiWhlist.getWlId(),updatePicInfo.get("7"),Short.valueOf("7"));//质检图片
//				saveWlPic(busiWhlist.getWlId(),qualityInfo.getQualityPic(),Short.valueOf("7"));//质检图片
			 }
				
				//保存之前的质检信息表
//				BusiQualityinfo busiQualityInfo = getWlQualityInfo(qualityInfo,busiWhlist);
//				busiQualityInfoDao.updateByWLIDSelective(busiQualityInfo);
			}
			/**质检信息修改（先删除，再修改）****end********/
			
			/**仓单图片修改（先删除，后插入）***start******/
			List<WmsPicInfoDto> picInfoList = wlDto.getPicInfo();
		    if(picInfoList!=null && picInfoList.size()>0){
		    	//先删除仓单图片
		    	int delPicNum = busiQualityPicDao.deleteWlPicByID(wlDto.getWlId());
		    	if(delPicNum>0){
				for(WmsPicInfoDto pic:picInfoList){
					if(pic!=null){
						saveWlPic(busiWhlist.getWlId(),updatePicInfo.get(pic.getPicType()+""),pic.getPicType()!=null?pic.getPicType().shortValue():null);//质检图片
		//				saveWlPic(busiWhlist.getWlId(),pic.getPicUrl(),pic.getPicType()!=null?pic.getPicType().shortValue():null);//质检图片
					}
				}
		      }
		    }
		  }
			/** 仓单图片修改（先删除，后插入）***end******/
			return new Integer(num);
		}catch(Exception e){
			logger.error("仓单修改同步接口失败："+e.getMessage(),e);
			status.setRollbackOnly();  //回滚事务
			return 0;
			
		}
		}
	});
	
	//仓单修改成功后，要发消息通知solr更新仓单信息
	if(cou.intValue()==1){
		RabbitmqProducerManager.getInstance().pushMsgForSolr(SolrOperationTypeEnum.UPDATE, SolrContentTypeEnum.WHLIST, wlDto.getWlId());
	}
		return cou.intValue();
	}
	
	@Override
	public int wmsWareHouseAdd(WmsWareHouseDto wmsWareHouse) {
		//幂等
		if (null != busiWareHouseService.findBusiWareHouseById(wmsWareHouse.getWareHouseCode())) {
			return 1;
		}
		BusiWareHouse busiWareHouse = wareHouseDtoToModel(wmsWareHouse);
		return busiWareHouseDao.insertBusiWareHouse(busiWareHouse);
	}

	@Override
	public int wmsWareHouseUpdate(WmsWareHouseDto wmsWareHouse) {
		/**
		 * 2015.07.02 fanyuna  如果不存在，则返回106，客户端需要将更新后的记录再调用新增接口 start
		 */
		if (null == busiWareHouseService.findBusiWareHouseById(wmsWareHouse.getWareHouseCode())) {
			return 106;
		}
		/**
		 * end 
		 */
		BusiWareHouse busiWareHouse = wareHouseDtoToModel(wmsWareHouse);
		//状态为1删除，0为有效
		if(wmsWareHouse.getStatus()!=null && wmsWareHouse.getStatus().intValue()==1){
			return busiWareHouseDao.delWarehouseById(wmsWareHouse.getWareHouseCode());
		}
			
		//return busiWareHouseDao.updateBusiWareHouseById(busiWareHouse);
		return busiWareHouseDao.updateByPrimaryKeySelective(busiWareHouse);
	}
	
	public BusiWareHouse wareHouseDtoToModel(WmsWareHouseDto wmsWareHouse){
		BusiWareHouse busiWareHouse = new BusiWareHouse();
		busiWareHouse.setWareHouseId(wmsWareHouse.getWareHouseCode());
		busiWareHouse.setWareHouseDes(wmsWareHouse.getWareHouseDes());
		busiWareHouse.setWareHouseName(wmsWareHouse.getWareHouseName());
		busiWareHouse.setWhtype(wmsWareHouse.getType());
		busiWareHouse.setWhcategory(wmsWareHouse.getCategory());
		busiWareHouse.setWhcontact(wmsWareHouse.getContact());
		busiWareHouse.setWhtelephone(wmsWareHouse.getTelephone());
		busiWareHouse.setWhaddress(wmsWareHouse.getAddress());
		busiWareHouse.setProvince(wmsWareHouse.getDistrict());
		if(wmsWareHouse.getStatus()!=null)
		busiWareHouse.setStatus(wmsWareHouse.getStatus().shortValue());
		else
			busiWareHouse.setStatus(new Short("0"));
		return busiWareHouse;
	}

	@Override
	public boolean unfreezeTrade(String wlId, double unfreezeCount,String orderId) {
		if(StringUtils.isBlank(wlId)){
			return false;
		}
		
		JsonObject jsonObject = new JsonObject();
		  jsonObject.addProperty("wlId", wlId);
		  jsonObject.addProperty("actualCount", unfreezeCount);
		  jsonObject.addProperty("orderId", orderId);
		  try {
//			String statusCode = "101";
			 String respData = WmsApiCommon.wmsEncAndSign(WmsApiCommon.WMS_WL_UNFREEZE_URL, jsonObject.toString());
			 if(StringUtils.isBlank(respData))
				return false;
			 String statusCode = WmsApiCommon.jsonResolve(respData, "statusCode");
//			String statusCode = wmsApi.sendWmsApiReq(WmsApiCommon.WMS_WL_UNFREEZE_URL, jsonObject.toString());
			String status = ApiFlagEnums.SYNC_LOG_SYNC_STATUS_FAIL.getFlag();
			if("101".equals(statusCode)){
				status = ApiFlagEnums.SYNC_LOG_SYNC_STATUS_SUCCESS.getFlag();
			}
			//记日志表
			wmsApiSynLog(ApiFlagEnums.WL_UNFREEZE.name(),orderId, jsonObject.toString(), statusCode, Integer.parseInt(status),ApiFlagEnums.SYNC_LOG_TYPE_WMS);
			if ("101".equals(statusCode)){
				return true;
			}else{
				//接口调用失败发邮件通知开发、运营、产品
				taskExecutor.execute(new EmailTaskSend(GetEmailContext.EMAIL_CALL_INTERFACE_FAIL,ConfigConstant.EMAIL_FROM_WMS, GetEmailContext.getEmailAddress(MessageConstant.INTERFACE_FAIL_DEV_EMAIL_ADDRESS.getMessageKey())+","+GetEmailContext.getEmailAddress(MessageConstant.INTERFACE_FAIL_OPERATE_EMAIL_ADDRESS.getMessageKey())+","+GetEmailContext.getEmailAddress(MessageConstant.INTERFACE_FAIL_PRODUCT_EMAIL_ADDRESS.getMessageKey()), GetEmailContext.getInterfaceEmailMsg(ApiFlagEnums.WL_UNFREEZE,orderId,jsonObject.toString(),respData),ConfigConstant.EMAIL_FROM_WMS_USERNAME,ConfigConstant.EMAIL_FROM_WMS_PASSWORD));
				return false;
			}
		} catch (Exception e) {
			//接口调用失败发邮件通知开发、运营、产品
			taskExecutor.execute(new EmailTaskSend(GetEmailContext.EMAIL_CALL_INTERFACE_FAIL,ConfigConstant.EMAIL_FROM_WMS, GetEmailContext.getEmailAddress(MessageConstant.INTERFACE_FAIL_DEV_EMAIL_ADDRESS.getMessageKey())+","+GetEmailContext.getEmailAddress(MessageConstant.INTERFACE_FAIL_OPERATE_EMAIL_ADDRESS.getMessageKey())+","+GetEmailContext.getEmailAddress(MessageConstant.INTERFACE_FAIL_PRODUCT_EMAIL_ADDRESS.getMessageKey()), GetEmailContext.getInterfaceEmailMsg(ApiFlagEnums.WL_UNFREEZE,orderId,jsonObject.toString(),e.getMessage()),ConfigConstant.EMAIL_FROM_WMS_USERNAME,ConfigConstant.EMAIL_FROM_WMS_PASSWORD));
			logger.error("订单："+orderId+"，调用解冻接口，请求串为："+jsonObject.toString()+"调用失败，"+e.getMessage(),e);
			return false;
		}
		
	}

	@Override
	public int wmsFreezeSuccess(final Map<String, String> map) {
		final BusiOrder busiOrder = busiOrderDao.selectBusiOrderById(map.get("orderId"));
		final BusiListing busiListing = busiListingDao.selectSingleListing(busiOrder.getListingid());
		final BigDecimal volume = BigDecimalUtil.formateBigDecimal2(new BigDecimal(map.get("wlActualCount")));
		Integer cou =getTransactionTemplate().execute(new TransactionCallback<Integer>() {
			@Override
			public Integer doInTransaction(TransactionStatus status){
				try{
					int num =0;
					//2015.09.15  add by fanyuna 判断目前订单是否处于已付保证金状态，如果不是，则说明此订单不接受备货操作
					if(busiOrder.getOrderstate().compareTo(Integer.valueOf(BusiOrderStateEnum.PREPAID_DEPOSIT.getCode()))==0){
						  
						//更改订单表中的交易数量为实际冻结数量
						BusiOrder order = new BusiOrder();
						order.setOrderid(map.get("orderId"));
						order.setVolume(volume);
						Integer orderState = null;
						if(BusiOrderTypeEnum.FULLPAY_ORDER.getCode().equals(String.valueOf(busiOrder.getOrderType()))){
							//如果是全款支付，这直接设定为已支付尾款
							orderState = Integer.valueOf(BusiOrderStateEnum.PAYED_ORDER.getCode());
						} else {
							//否则为已备货
							orderState = Integer.valueOf(BusiOrderStateEnum.READY_WARE.getCode());
						}
						order.setOrderstate(orderState); //已备货
						Date date = new Date();
						order.setExpiretime(TimeUtil.moveDays(date, Integer.parseInt(BusiParamEnum.BUSI_DEPOSIT_DELAY.getInfo())));  //过期时间=当前时间+7
						order.setUpdatetime(date);
						num = busiOrderDao.updateOrderByWmsFreezeSuccess(order);
						
						if(num>0){
							
							//当交易数量（即冻结数量）与买家购买的数量不一致时，如果挂牌取消了，则将差值返还到可挂数量；如果挂牌未取消，则将差值返还到可摘数量
							if(busiOrder.getAmount().compareTo(new BigDecimal(map.get("wlActualCount")))!=0){
								//挂牌取消
								if(BusiListingFlagEnum.LISTING_CANCEL.getCode().equals(String.valueOf(busiListing.getListingflag()))){
									BusiWhlist bWhlist = busiWhlistDao.selectWhlistByWlId(busiListing.getWlid());
									BusiWhlist bw = new BusiWhlist();
									bw.setWlId(busiListing.getWlid());
									bw.setWlSurplus(new BigDecimal(bWhlist.getWlSurplus()).add(busiOrder.getAmount().subtract(new BigDecimal(map.get("wlActualCount")))).doubleValue());
									busiWhlistDao.updateBusiWhlistByIdForWms(bw);
									//update by fanyuna 2015.08.27 增加仓单日志
									busiWhlistService.addBusiWhlistLog(bWhlist, null, BusinessLogEnum.WHLIST_UPDATE_FREEZE_LISTING_CANCEL, busiOrder.getOrderid(),busiOrder.getListingid(),bWhlist.getWlSurplus(),bw.getWlSurplus());
								}
								else{
									BusiListing bl = new BusiListing();
									bl.setListingid(busiListing.getListingid());
									bl.setSurpluses(busiListing.getSurpluses().add(busiOrder.getAmount().subtract(new BigDecimal(map.get("wlActualCount")))));
									busiListingDao.updateByIdSelective(bl);
									
									//update by fanyuna 2015.08.27 增加挂牌日志
									busiListingLogDao.insertBusiListingLog(busiListing, BusinessLogEnum.LISTING_UPDATE_FREEZE, null, busiOrder.getOrderid(),busiOrder.getListingid(),busiListing.getSurpluses(),bl.getSurpluses());
								}
							}
							//增加一条订单日志表记录
							busiOrderLogDao.insertBusiOrderLog(busiOrder, "冻结成功", null, BusinessLogEnum.ORDER_GOODS_PREPARED.getCode());
							
						}
					}
					else{
						logger.info("订单："+busiOrder.getOrderid()+"，当前状态为："+BusiOrderStateEnum.obtainCodeName(busiOrder.getOrderstate()+"")+"，不接受备货操作");
						num=-1;
					}
					return new Integer(num);
				}catch(Exception e){
					logger.error("订单："+map.get("orderId")+"，调用冻结成功接口异常，回滚操作。"+e.getMessage(),e);
					status.setRollbackOnly();  //回滚事务
					return 0;
				}
			}
		});
		if(cou.intValue()==1){
			if(busiOrder.getAmount().compareTo(volume)!=0&&
					!(BusiListingFlagEnum.LISTING_CANCEL.getCode().equals(String.valueOf(busiListing.getListingflag())))){
				//数量返还挂牌时，要发消息通知solr更新挂牌索引
				RabbitmqProducerManager.getInstance().pushMsgForSolr(SolrOperationTypeEnum.UPDATE, SolrContentTypeEnum.LISTING, busiListing.getListingid());
			}
			
			//如果是全款方式
			if(BusiOrderTypeEnum.FULLPAY_ORDER.getCode().equals(String.valueOf(busiOrder.getOrderType()))){
				BigDecimal totalPrice = BigDecimalUtil.formateBigDecimal2(busiOrder.getUnitprice().multiply(volume));
				try {
					//实际付款比应付款多时，发送信息提醒业务员分润时需向买家退款
					if(busiOrder.getAmount().compareTo(volume) > 0){
						BusiOrderSalesman salesmans=busiOrderSalesmanDao.selectByOrderid(busiOrder.getOrderid());//获取到订单的对应的业务员信息
						if(salesmans!=null){
							//卖家业务员不为空时，发送短息
							if(salesmans.getSellerSalesmanid() != null){
								BossUser sellerInfo = bossUserDao.getBossUserByUserId(salesmans.getSellerSalesmanid());//卖家关联的业务员信息
								String realName = ucUserDao.getCertifyNameByUserId(busiOrder.getUserid());//卖家认证名称
								String userName = ucUserDao.findUcUserById(busiOrder.getUserid().intValue()).getUserName();//卖家用户名
								taskExecutor.execute(messageConfigManage.getMessageChannelTask(sellerInfo.getMobile(),
										GetMessageContext.getOrderFullPay4SellerSalesMan(busiOrder.getOrderid(), realName+"("+userName+")", totalPrice, busiOrder.getActualPayment())));
							}
							//买家业务员不为空时，发送短息
							if(salesmans.getBuyerSalesmanid() != null){
								BossUser buyerInfo = bossUserDao.getBossUserByUserId(salesmans.getBuyerSalesmanid());//买家关联的业务员信息
								String realName = ucUserDao.getCertifyNameByUserId(busiOrder.getBuyer());//买家认证名称
								String userName = ucUserDao.findUcUserById(busiOrder.getBuyer().intValue()).getUserName();//买家用户名
								taskExecutor.execute(messageConfigManage.getMessageChannelTask(buyerInfo.getMobile(),
										GetMessageContext.getOrderFullPay4BuyerSalesMan(busiOrder.getOrderid(), realName+"("+userName+")", totalPrice, busiOrder.getActualPayment())));
							}
						}
						//跟单员
						String trackerEmail = GetEmailContext.getTrackerEmail();
						if(StringUtils.isNotEmpty(trackerEmail)){
							taskExecutor.execute(new EmailTaskSend(GetEmailContext.EMAIL_FULLPAYMENT_TITLE, trackerEmail, GetEmailContext.getFullPaymentEmailMsg(busiOrder.getOrderid(), totalPrice, busiOrder.getActualPayment())));
						}
					}
				} catch (Exception e) {
					logger.error("全款支付时，向业务员和跟单员发信息异常，"+e.getMessage(), e);
				}
				
				try{
					taskExecutor.execute(new Runnable() {
						@Override
						public void run() {
							//如果是全款支付，调用分割接口
							UcUser ucBuyer = ucUserDao.getUcUserById(busiOrder.getBuyer()+"");
							JsonObject jsonObjectWms = new JsonObject();
							jsonObjectWms.addProperty("wlId", busiOrder.getWlid());
							jsonObjectWms.addProperty("freezeCount", volume.doubleValue());
							jsonObjectWms.addProperty("orderId", busiOrder.getOrderid());
							//通知分割仓单
							SysLogUtil.logForWMS(jsonObjectWms.toString());
							boolean ok = applyWlSplit(busiOrder.getWlid(), volume.doubleValue(), busiOrder.getOrderid(), ucBuyer.getUserName());
							if(!ok){
								logger.error("通知WMS分割仓单失败！");
							}
						}
					},2000);
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}
			//否则发送备货成功信息
			else {
				try{
					//给买家发送短信
					UcUser buyer = ucUserDao.getUcUserById(busiOrder.getBuyer()+"");
					taskExecutor.execute(messageConfigManage.getMessageChannelTask(buyer.getMobile(), GetMessageContext.getBuyerGoodsPreparedMsg(busiOrder.getOrderid(), BusiParamEnum.BUSI_DEPOSIT_DELAY.getInfo())));
					//给卖家发短信
					UcUser seller = ucUserDao.getUcUserById(busiOrder.getUserid()+"");
					taskExecutor.execute(messageConfigManage.getMessageChannelTask(seller.getMobile(), GetMessageContext.getOwnerGoodsPreparedMsg(busiOrder.getOrderid(), BusiParamEnum.BUSI_DEPOSIT_DELAY.getInfo())));
					//给运营发邮件
					taskExecutor.execute(new EmailTaskSend(GetEmailContext.EMAIL_SUBJECT_FREEZE_SUCCESS, GetEmailContext.getWarehouseOperatorEmail(), GetEmailContext.getOperatorEmailMsg(busiOrder.getOrderid())));
					//added by biran 20151021 给业务员发短信
					PreparedSendMsg4SalesMans(busiOrder.getOrderid(),busiOrder.getWlid(),busiOrder.getUserid(),busiOrder.getBuyer());
					//added end
				}catch(Exception e){
					logger.error("冻结成功即备货成功后给买家、卖家发短信，给运营发邮件异常，"+e.getMessage(), e);
				}
			}
		}
		return cou.intValue();
	}

	@Override
	@Transactional
	public int wmsWlSplitSuccess(WmsWlSplitDto wmsWlSplitDto) {
		
		BusiOrder busiOrderBeforeUpdate = busiOrderDao.selectBusiOrderById(wmsWlSplitDto.getNewWlInfo().getOrderId());
		//add by fanyuna 2015.09.15 如果订单已经被分割 ，则直接返回0 即分割失败
		if(busiOrderBeforeUpdate.getSplitFlag()!=null && busiOrderBeforeUpdate.getSplitFlag().compareTo(new Short(BusiOrderSplitEnum.ORDER_SPLIT.getCode()))==0){
					logger.info("订单："+busiOrderBeforeUpdate.getOrderid()+"，已经分割 ，因此分割失败");
					return new Integer(-1);
				}
		//更新原仓单信息
		WmsOldWlInfo oldWlInfo = wmsWlSplitDto.getOldWlInfo();
		BusiWhlist oldWh = busiWhlistDao.selectWhlistByWlId(oldWlInfo.getWlId());
		BusiWhlist oldWhlist = new BusiWhlist();
			oldWhlist.setWlId(oldWlInfo.getWlId());
			oldWhlist.setWlTotal(oldWlInfo.getRemainCount());
			oldWhlist.setContractNum(oldWlInfo.getContractNum());
			//add by fanyuna 2015.11.11 原仓单的商品件数，已出库数量及状态变化
			oldWhlist.setSkunumber(oldWlInfo.getSkuNumber());
			oldWhlist.setOutednumber(oldWlInfo.getOutedNumber());
			if(oldWlInfo.getStatus()!=null){
				//有效  即未质押
				if(oldWlInfo.getStatus()==1){
					oldWhlist.setWlState(Integer.valueOf(BusiWhlistStateEnum.PLEDGED.getId()));
				}
				//冻结 质押
				if(oldWlInfo.getStatus()==2){
					oldWhlist.setWlState(Integer.valueOf(BusiWhlistStateEnum.NOPLEDGED.getId()));
				}
				//完成 已出库 无效仓单
				if(oldWlInfo.getStatus()==3){
					oldWhlist.setWlFlag(1);
				}
			}
			
			//
			long begin = System.currentTimeMillis();
			logger.info("wmsWlSplitSuccess.busiWhlistDao.updateBusiWhlistByIdForWms,update begin,time is:"+begin);
			int num = busiWhlistDao.updateBusiWhlistByIdForWms(oldWhlist);
			long end = System.currentTimeMillis();
			logger.info("wmsWlSplitSuccess.busiWhlistDao.updateBusiWhlistByIdForWms,update end,time is:"+end);
			logger.info("wmsWlSplitSuccess.busiWhlistDao.updateBusiWhlistByIdForWms cost:"+(end-begin)/1000+" seconds!");
			//
			if(num>0){
				//新增分割的新仓单
				BusiWhlist oldWl = busiWhlistDao.selectWhlistByWlId(oldWlInfo.getWlId());
				WmsNewWlInfo newWlInfo = wmsWlSplitDto.getNewWlInfo();
				BusiWhlist newWl = new BusiWhlist();
					newWl.setWlId(newWlInfo.getWlId());
					//户主
					UcUser user = ucUserDao.findUcUserByUserName(newWlInfo.getUserName());
					if(StringUtils.isNotBlank(newWlInfo.getUserName())){
						
						newWl.setUserId(user!=null?user.getUserId():null);
						newWl.setAccount(newWlInfo.getUserName());
					}
						
					newWl.setBreedCode(oldWl.getBreedCode());
							//计量单位
					newWl.setWlUnit(oldWl.getWlUnit());
							//类目  根据品种获取关联的 形态分类下 的类目
					newWl.setCategoryId(oldWl.getCategoryId());
						//仓库编号
					newWl.setWareHouseId(oldWl.getWareHouseId());
						//仓单数量
					newWl.setWlTotal(newWlInfo.getActualCount());
						//入库日期
					newWl.setWlrkDate(oldWl.getWlrkDate());
						//产地
					newWl.setOrigin(oldWl.getOrigin());
						//批次号
					newWl.setBatch(oldWl.getBatch());
						//合同编号
					newWl.setContractNum(newWlInfo.getContractNum());
						//区域  由于目前WMS系统未给区域信息，先指定汉阳区，若给了 来为空时用传过来的区域
					newWl.setAreaId(oldWl.getAreaId());
						//包装方式
					newWl.setPackingWay(oldWl.getPackingWay());
						
						//状态
					newWl.setWlFlag(0);
//					newWl.setWlState(oldWl.getWlState());
					//update by fanyuna 2015.07.22 业务员
//					newWl.setCreaterId(oldWl.getCreaterId());   //新分割仓单的 ，仓单的业务员不复制老仓单的
					newWl.setpWlid(newWlInfo.getpWlId());
					newWl.setOrderId(newWlInfo.getOrderId());
					
					//add by fanyuna 2015.11.11 原仓单的商品件数，已出库数量及状态变化
					newWl.setSkunumber(newWlInfo.getSkuNumber());
					newWl.setOutednumber(newWlInfo.getOutedNumber());
					if(newWlInfo.getStatus()!=null){
						//有效  即未质押
						if(newWlInfo.getStatus()==1){
							newWl.setWlState(Integer.valueOf(BusiWhlistStateEnum.PLEDGED.getId()));
						}
						//冻结 质押
						if(newWlInfo.getStatus()==2){
							newWl.setWlState(Integer.valueOf(BusiWhlistStateEnum.NOPLEDGED.getId()));
						}
						//完成 已出库 无效仓单
						if(newWlInfo.getStatus()==3){
							newWl.setWlFlag(1);
						}
					}
					
					newWl.setWmiocode(oldWl.getWmiocode());
					newWl.setStorename(oldWl.getStorename());
					newWl.setSignatureuser(oldWl.getSignatureuser());
					newWl.setSignaturedate(oldWl.getSignaturedate());
					newWl.setContractname(oldWl.getContractname());
					newWl.setPledgename(oldWl.getPledgename());
					//存储开始日期、结束日期、仓储费先复制原仓单的，个人觉得实际业务中应该不能这样
					newWl.setStoragetermstart(oldWl.getStoragetermstart());
					newWl.setStoragetermend(oldWl.getStoragetermend());
					newWl.setStoragefee(oldWl.getStoragefee());
					newWl.setLossstandard(oldWl.getLossstandard());
					
					//保存仓单基本信息
					int num1 = busiWhlistDao.insertBusiWhlist(newWl);
					if(num1>0){
						//update by fanyuna 2015.08.28 增加仓单日志 start
						busiWhlistService.addBusiWhlistLog(oldWh, null, BusinessLogEnum.WHLIST_SPLIT, oldWh.getWlTotal(),oldWlInfo.getRemainCount(),newWlInfo.getWlId(),newWlInfo.getActualCount());
						
						//update by fanyuna 2015.08.28 增加仓单日志 end 
						
						//保存新仓单质检基本信息
						BusiQualityInfoNew oldQualityInfo = busiQualityInfoNewDao.selectQualityByWlId(oldWl.getWlId());
						BusiQualityInfoNew qualityNew = new BusiQualityInfoNew();
						qualityNew.setCheckNumber(oldQualityInfo.getCheckNumber());
						qualityNew.setGrade(oldQualityInfo.getGrade());
						qualityNew.setLevelEva(oldQualityInfo.getLevelEva());
						qualityNew.setWlid(newWl.getWlId());
						qualityNew.setQualityPerson(oldQualityInfo.getQualityPerson());
						qualityNew.setQualityTime(oldQualityInfo.getQualityTime());
						qualityNew.setReportDate(oldQualityInfo.getReportDate());
						qualityNew.setCreateTime(new Date());
						qualityNew.setUpdateTime(new Date());
						int num2 =busiQualityInfoNewDao.insert(qualityNew);
						if(num2>0){
							//保存新仓单质检项信息
							List<BusiQualityItem> oldQualityItemList = busiQualityItemDao.selectQualityItemByWlId(oldWl.getWlId());
							if(oldQualityItemList!=null && oldQualityItemList.size()>0){
							for(BusiQualityItem item:oldQualityItemList){
								BusiQualityItem qualityItem = new BusiQualityItem();
								qualityItem.setQualityInfoId(qualityNew.getQualityInfoId());
								qualityItem.setWlid(newWl.getWlId());
								qualityItem.setQualityItemName(item.getQualityItemName());
								qualityItem.setQualityItemType(item.getQualityItemType());
								qualityItem.setQualityItemStandard(item.getQualityItemStandard());
								qualityItem.setQualityItemResult(item.getQualityItemResult());
								qualityItem.setCreateTime(new Date());
								qualityItem.setUpdateTime(new Date());
								busiQualityItemDao.insert(qualityItem);
							}
							}
						}
						
						//保存新仓单图片信息
						List<BusiQualitypic> oldWlPicList =  busiQualityPicDao.selectPicByWLID(oldWl.getWlId());
						if(oldWlPicList != null && oldWlPicList.size()>0){
						for(BusiQualitypic pic:oldWlPicList){
							BusiQualitypic newWlPic = new BusiQualitypic();
								newWlPic.setWlid(newWl.getWlId());
								newWlPic.setPath(pic.getPath());
								newWlPic.setPicindex(pic.getPicindex());
								busiQualityPicDao.insertBusiQualitypic(newWlPic);
						}
						}
						
						//更新订单状态为已完成
						
						BusiOrder order = new BusiOrder();
						 order.setOrderid(newWlInfo.getOrderId());
						 order.setSplitFlag(Short.valueOf(BusiOrderSplitEnum.ORDER_SPLIT.getCode()));  //更新订单分割标识为已分割 
							//add by fanyuna 2015.09.07 普通订单分割成功，更新订单状态为已完成；账期订单分割成功，看订单状态是否为已付款，若是则更新订单状态为已完成
						  if(busiOrderBeforeUpdate.getOrderstate().toString().equals(BusiOrderStateEnum.PAYED_ORDER.getCode())){
							 order.setOrderstate(Integer.valueOf(BusiOrderStateEnum.COMPLETED_ORDER.getCode()));
							}
						busiOrderDao.updateByIdSelective(order);
						
						//update by fanyuna 2015.08.28 增加订单日志 start
						//add by fanyuna 2015.09.07 普通订单分割成功，订单状态为已完成；账期订单分割成功，看订单状态是否为已付款，若是则订单状态为已完成
					if(busiOrderBeforeUpdate.getOrderstate().toString().equals(BusiOrderStateEnum.PAYED_ORDER.getCode()))
						busiOrderLogDao.insertBusiOrderLog(busiOrderBeforeUpdate, BusinessLogEnum.ORDER_FINISHED.getCodeName(), null, BusinessLogEnum.ORDER_FINISHED.getCode());
					else
						busiOrderLogDao.insertBusiOrderLog(busiOrderBeforeUpdate, BusinessLogEnum.ORDER_SPLIT.getCodeName(), null, BusinessLogEnum.ORDER_SPLIT.getCode());
						//update by fanyuna 2015.08.28 增加订单日志 end 
						
					//add by fanyuna 2015.09.07 订单交易完成方可插入划账信息、及给买家 卖家发短信通知
					if(busiOrderBeforeUpdate.getOrderstate().toString().equals(BusiOrderStateEnum.PAYED_ORDER.getCode())){
						//插入划账信息
						try {
							BusiOrder busiOrder = busiOrderDao.selectBusiOrderById(newWlInfo.getOrderId());
							busiOrderDepositDao.insertOrderDeposit(busiOrder,BusiOrderDepositTypeEnum.ORDER_FINISHED_DEPOSIT.getCode());
						} catch (Exception e1) {
							logger.error("仓单分割成功即订单完成时，插入划账信息时异常，"+e1.getMessage(), e1);
						}
						
						try{
						//给买家发送短信
						UcUser buyer = ucUserDao.getUcUserById(user.getUserId()+"");
						taskExecutor.execute(messageConfigManage.getMessageChannelTask(buyer.getMobile(), GetMessageContext.getBuyerOrderMsg(newWlInfo.getOrderId())));
						//给卖家发短信
						UcUser seller = ucUserDao.getUcUserById(oldWl.getUserId()+"");
						taskExecutor.execute(messageConfigManage.getMessageChannelTask(seller.getMobile(), GetMessageContext.getOwnerOrderMsg(newWlInfo.getOrderId())));
						}catch(Exception e){
							logger.error("仓单分割成功即交易成功后给买家、卖家发短信异常，"+e.getMessage(), e);
						}
					}
					
					//更新solr 挂牌
					RabbitmqProducerManager.getInstance().pushMsgForSolr(SolrOperationTypeEnum.UPDATE, SolrContentTypeEnum.LISTING, busiOrderBeforeUpdate.getListingid());
					}
			}
		return num;
	}
	

	@Override
	public boolean applyWlFreeze(String wlId, double freezeCount, String orderId) {
		if(StringUtils.isBlank(wlId)||StringUtils.isBlank(orderId)){
			return false;
		}
		
		JsonObject jsonObject = new JsonObject();
		  jsonObject.addProperty("wlId", wlId);
		  jsonObject.addProperty("wlCount", freezeCount);
		  jsonObject.addProperty("orderId", orderId);
		  try {
//			String statusCode = "101";
		    String respData = WmsApiCommon.wmsEncAndSign(WmsApiCommon.WMS_WL_FREEZE_URL, jsonObject.toString());
			if(StringUtils.isBlank(respData))
				return false;
			String statusCode = WmsApiCommon.jsonResolve(respData, "statusCode");
//			String statusCode = wmsApi.sendWmsApiReq(WmsApiCommon.WMS_WL_FREEZE_URL, jsonObject.toString());
			String status = ApiFlagEnums.SYNC_LOG_SYNC_STATUS_FAIL.getFlag();
			if("101".equals(statusCode)){
				status = ApiFlagEnums.SYNC_LOG_SYNC_STATUS_SUCCESS.getFlag();
			}
			//记日志表
			wmsApiSynLog(ApiFlagEnums.WL_FREEZE.name(),orderId, jsonObject.toString(), statusCode, Integer.parseInt(status),ApiFlagEnums.SYNC_LOG_TYPE_WMS);
			if ("101".equals(statusCode)){
				return true;
			}else{
				//接口调用失败发邮件通知开发、运营、产品
				taskExecutor.execute(new EmailTaskSend(GetEmailContext.EMAIL_CALL_INTERFACE_FAIL,ConfigConstant.EMAIL_FROM_WMS, GetEmailContext.getEmailAddress(MessageConstant.INTERFACE_FAIL_DEV_EMAIL_ADDRESS.getMessageKey())+","+GetEmailContext.getEmailAddress(MessageConstant.INTERFACE_FAIL_OPERATE_EMAIL_ADDRESS.getMessageKey())+","+GetEmailContext.getEmailAddress(MessageConstant.INTERFACE_FAIL_PRODUCT_EMAIL_ADDRESS.getMessageKey()), GetEmailContext.getInterfaceEmailMsg(ApiFlagEnums.WL_FREEZE,orderId,jsonObject.toString(),respData),ConfigConstant.EMAIL_FROM_WMS_USERNAME,ConfigConstant.EMAIL_FROM_WMS_PASSWORD));
		
				return false;
			}
		} catch (Exception e) {
			//接口调用失败发邮件通知开发、运营、产品
			taskExecutor.execute(new EmailTaskSend(GetEmailContext.EMAIL_CALL_INTERFACE_FAIL,ConfigConstant.EMAIL_FROM_WMS, GetEmailContext.getEmailAddress(MessageConstant.INTERFACE_FAIL_DEV_EMAIL_ADDRESS.getMessageKey())+","+GetEmailContext.getEmailAddress(MessageConstant.INTERFACE_FAIL_OPERATE_EMAIL_ADDRESS.getMessageKey())+","+GetEmailContext.getEmailAddress(MessageConstant.INTERFACE_FAIL_PRODUCT_EMAIL_ADDRESS.getMessageKey()), GetEmailContext.getInterfaceEmailMsg(ApiFlagEnums.WL_FREEZE,orderId,jsonObject.toString(),e.getMessage()),ConfigConstant.EMAIL_FROM_WMS_USERNAME,ConfigConstant.EMAIL_FROM_WMS_PASSWORD));
			logger.error("订单："+orderId+"申请冻结接口失败 "+e.getMessage(),e);
			return false;
		}
		
	}

	@Override
	public boolean applyWlSplit(String wlId, double freezeCount,
			String orderId, String buyer) {
		  if(StringUtils.isBlank(wlId)||StringUtils.isBlank(orderId)){
			return false;
		  }
		  String statusCode = null;
		  String status = ApiFlagEnums.SYNC_LOG_SYNC_STATUS_FAIL.getFlag();
		  JsonObject jsonObject = new JsonObject();
		  jsonObject.addProperty("wlId", wlId);
		  jsonObject.addProperty("wlCount", freezeCount);
		  jsonObject.addProperty("orderId", orderId);
		  jsonObject.addProperty("buyer", buyer); 
		  try {
			//查订单的分割标识，如果已分割 则不再请求
			  BusiOrder busiOrder = busiOrderDao.selectBusiOrderById(orderId);
			  if(busiOrder.getSplitFlag().compareTo(Short.valueOf(BusiOrderSplitEnum.ORDER_SPLIT.getCode()))==0){
				  return true;
			  }
				  
		    String respData = WmsApiCommon.wmsEncAndSign(WmsApiCommon.WMS_WL_APPLY_SPLIT_URL, jsonObject.toString());
		    if(StringUtils.isBlank(respData)){
			   return false;
		    }
		    statusCode = WmsApiCommon.jsonResolve(respData, "statusCode");
			if(ApiFlagEnums.WMS_API_RES_SUCCESS.getFlag().equals(statusCode)){
				status = ApiFlagEnums.SYNC_LOG_SYNC_STATUS_SUCCESS.getFlag();
				return true;
			}else{
				//接口调用失败发邮件通知开发、运营、产品
				taskExecutor.execute(new EmailTaskSend(GetEmailContext.EMAIL_CALL_INTERFACE_FAIL,ConfigConstant.EMAIL_FROM_WMS, GetEmailContext.getEmailAddress(MessageConstant.INTERFACE_FAIL_DEV_EMAIL_ADDRESS.getMessageKey())+","+GetEmailContext.getEmailAddress(MessageConstant.INTERFACE_FAIL_OPERATE_EMAIL_ADDRESS.getMessageKey())+","+GetEmailContext.getEmailAddress(MessageConstant.INTERFACE_FAIL_PRODUCT_EMAIL_ADDRESS.getMessageKey()), GetEmailContext.getInterfaceEmailMsg(ApiFlagEnums.WL_APPLY_SPLIT,orderId,jsonObject.toString(),respData),ConfigConstant.EMAIL_FROM_WMS_USERNAME,ConfigConstant.EMAIL_FROM_WMS_PASSWORD));
				return false;
			}
		  } catch (Exception e) {
			//接口调用失败发邮件通知开发、运营、产品
			taskExecutor.execute(new EmailTaskSend(GetEmailContext.EMAIL_CALL_INTERFACE_FAIL,ConfigConstant.EMAIL_FROM_WMS, GetEmailContext.getEmailAddress(MessageConstant.INTERFACE_FAIL_DEV_EMAIL_ADDRESS.getMessageKey())+","+GetEmailContext.getEmailAddress(MessageConstant.INTERFACE_FAIL_OPERATE_EMAIL_ADDRESS.getMessageKey())+","+GetEmailContext.getEmailAddress(MessageConstant.INTERFACE_FAIL_PRODUCT_EMAIL_ADDRESS.getMessageKey()), GetEmailContext.getInterfaceEmailMsg(ApiFlagEnums.WL_APPLY_SPLIT,orderId,jsonObject.toString(),e.getMessage()),ConfigConstant.EMAIL_FROM_WMS_USERNAME,ConfigConstant.EMAIL_FROM_WMS_PASSWORD));
			logger.error("订单："+orderId+"申请分割接口失败 "+e.getMessage(),e);
			return false;
		  }finally{
			//记日志表
			wmsApiSynLog(ApiFlagEnums.WL_APPLY_SPLIT.name(),orderId, jsonObject.toString(), statusCode, Integer.parseInt(status),ApiFlagEnums.SYNC_LOG_TYPE_WMS);
		  }
	}

	
	/**
	 * wms api接口同步日志
	 * @param apiFlag
	 * 			接口标识
	 * @param data
	 * 			同步数据
	 * @param reasons
	 * 			失败原因
	 * @param synFlag
	 * 			同步是否成功，1成功 2失败
	 * @param flag 1 wms,2金融链
	 */
	public void wmsApiSynLog(String apiFlag,String dataId,String data,String reasons,Integer synStatus,ApiFlagEnums flag){
		SyncDataLog syncDataLog = new SyncDataLog();
		syncDataLog.setDataType(Integer.parseInt(flag.getFlag()));//1wms 2供应链金融
		syncDataLog.setOperation(apiFlag);
		syncDataLog.setDataId(dataId);
		syncDataLog.setData(data);
		syncDataLog.setReason(reasons);
		syncDataLog.setSyncStatus(synStatus);
		syncDataLog.setTime(new Date());
		syncDataLogService.addSyncDataLog(syncDataLog);
		SysLogUtil.logForWMS(GsonFactory.createGson("yyyy-MM-dd HH:mm:ss").toJson(syncDataLog));
	}

	/**
	 * 账期订单调用的申请分割接口ServiceImpl
	 * 
	 */
	@Override
	public MessageVo applyWlSplit(String json) {
		MessageVo  msg = new MessageVo();
		if(StringUtils.isBlank(json)){
			return msg.addError(new ErrorMessage("NULL_PARAM","参数为空"));
		}
		Gson gson = new Gson();
		Map<String,String> userMap = gson.fromJson(json, new TypeToken<Map<String,String>>(){}.getType());
		String status = ApiFlagEnums.SYNC_LOG_SYNC_STATUS_FAIL.getFlag();
		String statusCode = null;
		try {
		    String respData = WmsApiCommon.wmsEncAndSign(WmsApiCommon.WMS_WL_APPLY_SPLIT_URL, json);
		    if(StringUtils.isBlank(respData)){
			   return msg.addError(new ErrorMessage("NULL_RESPONSE","接口返回值为空"));
		    }
		    statusCode = WmsApiCommon.jsonResolve(respData, "statusCode");
			if(ApiFlagEnums.WMS_API_RES_SUCCESS.getFlag().equals(statusCode)){
				status = ApiFlagEnums.SYNC_LOG_SYNC_STATUS_SUCCESS.getFlag();
				return msg.setOk(true);
			}else{
				String reason = WmsApiCommon.jsonResolve(respData, "reason"); //错误原因
				//接口调用失败发邮件通知开发、运营、产品
				taskExecutor.execute(new EmailTaskSend(GetEmailContext.EMAIL_CALL_INTERFACE_FAIL,ConfigConstant.EMAIL_FROM_WMS, GetEmailContext.getEmailAddress(MessageConstant.INTERFACE_FAIL_DEV_EMAIL_ADDRESS.getMessageKey())+","+GetEmailContext.getEmailAddress(MessageConstant.INTERFACE_FAIL_OPERATE_EMAIL_ADDRESS.getMessageKey())+","+GetEmailContext.getEmailAddress(MessageConstant.INTERFACE_FAIL_PRODUCT_EMAIL_ADDRESS.getMessageKey()), GetEmailContext.getInterfaceEmailMsg(ApiFlagEnums.WL_APPLY_SPLIT,userMap.get("orderId"),json,respData),ConfigConstant.EMAIL_FROM_WMS_USERNAME,ConfigConstant.EMAIL_FROM_WMS_PASSWORD));
				return msg.addError(new ErrorMessage(statusCode,reason));
			}
		} catch (Exception e) {
			//接口调用失败发邮件通知开发、运营、产品
			taskExecutor.execute(new EmailTaskSend(GetEmailContext.EMAIL_CALL_INTERFACE_FAIL,ConfigConstant.EMAIL_FROM_WMS, GetEmailContext.getEmailAddress(MessageConstant.INTERFACE_FAIL_DEV_EMAIL_ADDRESS.getMessageKey())+","+GetEmailContext.getEmailAddress(MessageConstant.INTERFACE_FAIL_OPERATE_EMAIL_ADDRESS.getMessageKey())+","+GetEmailContext.getEmailAddress(MessageConstant.INTERFACE_FAIL_PRODUCT_EMAIL_ADDRESS.getMessageKey()), GetEmailContext.getInterfaceEmailMsg(ApiFlagEnums.WL_APPLY_SPLIT,userMap.get("orderId"),json,e.getMessage()),ConfigConstant.EMAIL_FROM_WMS_USERNAME,ConfigConstant.EMAIL_FROM_WMS_PASSWORD));
			logger.error("订单："+userMap.get("orderId")+"申请分割接口失败 "+e.getMessage(),e);
			return msg.addError(new ErrorMessage("EXCEPTION",e.getMessage()));
		}finally{
			//记日志表
			wmsApiSynLog(ApiFlagEnums.WL_APPLY_SPLIT.name(),userMap.get("orderId"), json, statusCode, Integer.parseInt(status),ApiFlagEnums.SYNC_LOG_TYPE_WMS);
		}
	}
	
	
	/*WMS备货后，给业务员发短信*/
	private void PreparedSendMsg4SalesMans(String orderId,String wlId,Long sellerId,Long BuyerId){
		try {
			BusiOrderSalesman salesmans=busiOrderSalesmanDao.selectByOrderid(orderId);//获取到订单的对应的业务员信息
			if(salesmans!=null){
				Long breedCode=busiWhlistDao.selectWhlistByWlId(wlId).getBreedCode();//品种信息
				String breedName=breedDao.selectByPrimaryKey(breedCode).getBreedName();
				if(salesmans.getSellerSalesmanid()>new Long(0)){//卖家业务员不为空时
					BossUser sellerInfo = bossUserDao.getBossUserByUserId(salesmans.getSellerSalesmanid());//卖家关联的业务员信息
					String realName=ucUserDao.getCertifyNameByUserId(sellerId);//卖家认证名称
					String UserName=ucUserDao.findUcUserById(sellerId.intValue()).getUserName();//卖家用户名
					String buyerSalesManName="";//买家业务员名称
					if(salesmans.getBuyerSalesmanid()>new Long(0) ){
						buyerSalesManName=  bossUserDao.getBossUserByUserId(salesmans.getBuyerSalesmanid()).getUserName();
					}
					taskExecutor.execute(messageConfigManage.getMessageChannelTask(sellerInfo.getMobile(),
					GetMessageContext.getOrderPreparedMsg4SellerSalesMan(orderId,realName+"("+UserName+")",breedName,buyerSalesManName),
							"订单[" + orderId + "]WMS备货后，给卖家业务员发送短信通知出错，错误信息是："));
				}
				if(salesmans.getBuyerSalesmanid()>new Long(0)){//买家业务员不为空时
					BossUser buyerInfo = bossUserDao.getBossUserByUserId(salesmans.getBuyerSalesmanid());//买家关联的业务员信息
					String realName=ucUserDao.getCertifyNameByUserId(BuyerId);//买家认证名称
					String UserName=ucUserDao.findUcUserById(BuyerId.intValue()).getUserName();//买家用户名
					String sellerSalesManName="";//买家业务员名称
					if(salesmans.getSellerSalesmanid()>new Long(0) ){
						sellerSalesManName=  bossUserDao.getBossUserByUserId(salesmans.getSellerSalesmanid()).getUserName();
					}
					taskExecutor.execute(messageConfigManage.getMessageChannelTask(buyerInfo.getMobile(),
					GetMessageContext.getOrderPreparedMsg4BuyerSalesMan(orderId,realName+"("+UserName+")",breedName,sellerSalesManName),
							"订单[" + orderId + "]WMS备货后，给买家业务员发送短信通知出错，错误信息是："));
				}
			}
		} catch (Exception e) {
			logger.error("WMS备货后,给业务员发送发短信失败："+e.getMessage());
		} finally{
		}
	}
	
}
