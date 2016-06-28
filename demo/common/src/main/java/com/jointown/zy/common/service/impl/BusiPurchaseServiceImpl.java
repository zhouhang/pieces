package com.jointown.zy.common.service.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.util.CollectionUtils;

import com.jointown.zy.common.constant.MessageConstant;
import com.jointown.zy.common.dao.BossUserDao;
import com.jointown.zy.common.dao.BreedDao;
import com.jointown.zy.common.dao.BusiPurchaseDao;
import com.jointown.zy.common.dao.BusiPurchaseLogDao;
import com.jointown.zy.common.dao.DictInfoDao;
import com.jointown.zy.common.dao.UcUserDao;
import com.jointown.zy.common.dto.BusiPurchaseDto;
import com.jointown.zy.common.dto.HomePageListingDto;
import com.jointown.zy.common.enums.BusiPurchaseStatusEnum;
import com.jointown.zy.common.enums.BusinessLogEnum;
import com.jointown.zy.common.messageconfig.MessageConfigManage;
import com.jointown.zy.common.model.BossUser;
import com.jointown.zy.common.model.Breed;
import com.jointown.zy.common.model.BusiPurchase;
import com.jointown.zy.common.model.DictInfo;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.model.UcUser;
import com.jointown.zy.common.rabbitmq.RabbitmqProducerManager;
import com.jointown.zy.common.service.BaseService;
import com.jointown.zy.common.service.BusiPurchaseService;
import com.jointown.zy.common.task.EmailTaskSend;
import com.jointown.zy.common.util.EmailUtils;
import com.jointown.zy.common.util.GetEmailContext;
import com.jointown.zy.common.util.GetMessageContext;
import com.jointown.zy.common.util.HtmlSymbolsUtil;
import com.jointown.zy.common.util.SpringUtil;
import com.jointown.zy.common.util.TimeUtil;
import com.jointown.zy.common.vo.BusiPurchaseVo;
import com.jointown.zy.common.vo.UcUserVo;

@Service
public class BusiPurchaseServiceImpl extends BaseService implements BusiPurchaseService {

	private static final Logger log = LoggerFactory.getLogger(BusiPurchaseServiceImpl.class);
	@Autowired
	private UcUserDao ucUserDao;
	@Autowired
	private BossUserDao bossUserDao;
	@Autowired
	private BusiPurchaseDao busiPurchaseDao;
	@Autowired
	private BreedDao breedDao;
	@Autowired
	private DictInfoDao dictInfoDao;
	@Autowired
	private BusiPurchaseLogDao busiPurchaseLogDao;
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	@Autowired
	private MessageConfigManage messageConfigManage;
	
	
	@Override
	public BossUser getSalemanByUcuserId(Long userId) {
		UcUser ucUser  = ucUserDao.findUcUserById(userId.intValue());
		Long salesmanId = ucUser.getSalesmanId();
		if(salesmanId != null){
			return bossUserDao.findBossUserByUserId(salesmanId.toString());
		}
		return null;
	}


	@Override
	public Map<String, String> getSalesmanOfPurchase(Long userId) {
		Map<String,String> salesmanInfo = new HashMap<String,String>();
		BossUser bossUser = getSalemanByUcuserId(userId);
		if(bossUser!=null){
			salesmanInfo.put("name", bossUser.getUserName());
			salesmanInfo.put("phone", bossUser.getMobile());
		}else{
			salesmanInfo.put("name", MessageConstant.PURCHASE_ALTERNATIVE_SALESMAN_NAME.getValue());
			salesmanInfo.put("phone", MessageConstant.PURCHASE_ALTERNATIVE_SALESMAN_PHONE.getValue());
			
		}
		return salesmanInfo;
	}
	
	

	@Override
	public int savePurchase(BusiPurchaseDto busiPurchaseDto, UcUserVo user,String purchaseCode) {
		int num = 0;
		BusiPurchase busiPurchase = new BusiPurchase();
		try {
//			BeanUtil.copy(busiPurchaseDto, busiPurchase);
			BeanUtils.copyProperties(busiPurchaseDto, busiPurchase);
		} catch (Exception e) {
			log.error("单条采购，保存采购单时，DTO转Model异常："+e.getMessage(), e);
			return 0;
		}
		
			//状态 待审核
			busiPurchase.setStatus(Integer.valueOf(BusiPurchaseStatusEnum.AUDIT_WAITING.getCode()));
			busiPurchase.setCreateTime(new Date());
		//新增
		if(StringUtils.isBlank(busiPurchaseDto.getPurchaseId()))
			busiPurchase = getBusiPurchaseBy(busiPurchaseDto,user,purchaseCode,busiPurchase);
		
			
			//新增
			if(StringUtils.isBlank(busiPurchaseDto.getPurchaseId())){
				num =  busiPurchaseDao.insert(busiPurchase);
				if(num>0){
					busiPurchaseLogDao.insertByBusiPurchase(busiPurchase, "10", user.getUserName(), "新增单条采购单",false);
					//发邮件
					//此部分为了获取采购单的计量单位
					List<BusiPurchaseVo> busiPurchaseVoList = new ArrayList<BusiPurchaseVo>();
					BusiPurchaseVo vo = new BusiPurchaseVo();
					BeanUtils.copyProperties(busiPurchase, vo);
					List<DictInfo> dictInfoList = dictInfoDao.selectDictByCode(busiPurchase.getWunitCode());
						if(dictInfoList!=null && dictInfoList.size()>0)
							vo.setWunitName(dictInfoList.get(0).getDictValue());
						busiPurchaseVoList.add(vo);
					
					//如果用户未认证，读取其注册时的名称，否则读取其认证名称
//					int certifyStatus = user.getCertifyStatus();
//					String name = certifyStatus==0?user.getCompanyName():ucUserDao.getCertifyNameByUserId(user.getUserId());
					
					taskExecutor.execute(new EmailTaskSend(GetEmailContext.EMAIL_PURCHASE_ADD,GetEmailContext.getXuQiuOperatorEmail()+","+GetEmailContext.getCaigouEmail(), GetEmailContext.getEmailMsgOfAddPurchase(user.getUserName(),user.getMobile(),busiPurchaseVoList,1)));
				}
			}
			else{ //修改
				BusiPurchase beforePurchase = busiPurchaseDao.selectByPrimaryKey(busiPurchaseDto.getPurchaseId()); 
				num = busiPurchaseDao.updateByPrimaryKeySelective(busiPurchase);
				if(num>0){
					busiPurchaseLogDao.insertByBusiPurchase(beforePurchase, "20", user.getUserName(), "修改采购单");
					
					//发邮件
					//此部分为了获取采购单的计量单位
					List<BusiPurchaseVo> busiPurchaseVoList = new ArrayList<BusiPurchaseVo>();
					BusiPurchaseVo vo = new BusiPurchaseVo();
					BeanUtils.copyProperties(busiPurchase, vo);
					List<DictInfo> dictInfoList = dictInfoDao.selectDictByCode(busiPurchase.getWunitCode());
						if(dictInfoList!=null && dictInfoList.size()>0)
							vo.setWunitName(dictInfoList.get(0).getDictValue());
						busiPurchaseVoList.add(vo);
					
					//如果用户未认证，读取其注册时的名称，否则读取其认证名称
//					int certifyStatus = user.getCertifyStatus();
//					String name = certifyStatus==0?user.getCompanyName():ucUserDao.getCertifyNameByUserId(user.getUserId());
					
					taskExecutor.execute(new EmailTaskSend(GetEmailContext.EMAIL_PURCHASE_EDIT,GetEmailContext.getXuQiuOperatorEmail()+","+GetEmailContext.getCaigouEmail(), GetEmailContext.getEmailMsgOfAddPurchase(user.getUserName(),user.getMobile(),busiPurchaseVoList,2)));
				}
			}
			 
			return num;
	}
	
	private BusiPurchase getBusiPurchaseBy(BusiPurchaseDto busiPurchaseDto, UcUserVo user,String purchaseCode,BusiPurchase busiPurchase){
		//采购批次号
			busiPurchase.setPurchaseCode(purchaseCode);
		//采购会员
			busiPurchase.setPurchaser(user.getUserName());
			
			//有效
			busiPurchase.setIsValid("Y");
			
			//关联的业务员
			BossUser bUser = getSalemanByUcuserId(user.getUserId());
			if(bUser !=null)
				busiPurchase.setOperator(bUser.getUserCode());
			return busiPurchase;
	}
	
	
	
	
	/**
	 * 
	 * @Description: 获取采购批次号
	 * @Author: fanyuna
	 * @Date: 2015年10月14日
	 * @param userId 会员ID
	 * @return
	 */
	public String createPurchaseCode(Long userId){
		StringBuffer purchaseCodeBuf = new StringBuffer("CG");
		purchaseCodeBuf.append(TimeUtil.getTimeShowByTimePartten(new Date(), "yyyyMMddHHmm"));
		purchaseCodeBuf.append(userId);
		return purchaseCodeBuf.toString();
	}
	

	/**
	 * 采购详情
	 * @Author: shangcuijuan
	 * @Date: 2015年10月15日
	 * @param purchaseId
	 * @return
	 */
	public BusiPurchase selectByPrimaryKey(String purchaseId) {
		return busiPurchaseDao.selectByPrimaryKey(purchaseId);
	}
	public BusiPurchaseVo selectPurchaseInfo(String purchaseId){
		return busiPurchaseDao.selectPurchaseDetail(purchaseId);
	}

	public boolean batchSavePurchase(final UcUserVo user,final String purchaseCode,final org.apache.poi.ss.usermodel.Workbook wb,final org.apache.poi.ss.usermodel.Sheet sheet) throws Exception {
	final List<BusiPurchaseVo> bpList = new ArrayList<BusiPurchaseVo>();
	Exception exception =getTransactionTemplate().execute(new TransactionCallback<Exception>() {
		@Override
	  public Exception doInTransaction(TransactionStatus status){
	  try{	
		List<BusiPurchaseDto> purchaseDtoList = null;
//		try {
//			 purchaseDtoList = readExcelForXls(fis);
			purchaseDtoList = readExcelOfPoi(wb,sheet);
			
//		} catch (Exception e) {
//			log.error("批量采购，读取Excel内容时异常："+e.getMessage(), e);
//			return false;
//		}
		if(purchaseDtoList==null|| purchaseDtoList.size()<=0){
			log.error("批量采购，读取Excel内容时异常!");
			throw new Exception("系统处理异常，请联系管理员");
		}	 
		List<BusiPurchase> purchaseList = new ArrayList<BusiPurchase>();
		for(BusiPurchaseDto busiPurchaseDto:purchaseDtoList){
				BusiPurchase busiPurchase = new BusiPurchase();
				BeanUtils.copyProperties(busiPurchaseDto, busiPurchase);
				//状态 待审核
				  busiPurchase.setStatus(Integer.valueOf(BusiPurchaseStatusEnum.AUDIT_WAITING.getCode()));
				  busiPurchase.setCreateTime(new Date());
				  busiPurchase = getBusiPurchaseBy(busiPurchaseDto,user,purchaseCode,busiPurchase);
				
				  purchaseList.add(busiPurchase);
				  
				//为发邮件组装对象
					BusiPurchaseVo vo = new BusiPurchaseVo();
					BeanUtils.copyProperties(busiPurchase, vo);
					List<DictInfo> dictInfoList = dictInfoDao.selectDictByCode(busiPurchase.getWunitCode());
						if(dictInfoList!=null && dictInfoList.size()>0)
							vo.setWunitName(dictInfoList.get(0).getDictValue());
					bpList.add(vo);
			/*int  num= busiPurchaseDao.insert(busiPurchase);
			if(num<=0){
				log.error("批量采购，保存采购信息时异常!");
				throw new Exception("系统处理异常，请联系管理员");
			}
			else{
				busiPurchaseLogDao.insertByBusiPurchase(busiPurchase, "10", user.getUserName(), "新增批量采购单");
				//为发邮件组装对象
				BusiPurchaseVo vo = new BusiPurchaseVo();
				BeanUtils.copyProperties(busiPurchase, vo);
				List<DictInfo> dictInfoList = dictInfoDao.selectDictByCode(busiPurchase.getWunitCode());
					if(dictInfoList!=null && dictInfoList.size()>0)
						vo.setWunitName(dictInfoList.get(0).getDictValue());
				bpList.add(vo);
				
			}*/
					
		}
		int num = 0;
		if(purchaseList!=null && purchaseList.size()>0){
			num =busiPurchaseDao.batchInsert(purchaseList);
		}
		if(num>0){
			busiPurchaseLogDao.batchInsertByBusiPurchase(purchaseList, "10", user.getUserName(), "新增批量采购单",false);
		}else{
			log.error("批量采购，保存采购信息时异常!");
			throw new Exception("系统处理异常，请联系管理员");
		}
		
		
		}catch(Exception e){
			log.error("批量采购，批量保存采购信息时异常："+e.getMessage(),e);
			status.setRollbackOnly();  //回滚事务
			return e;
		}
	  return null;
	}
	});
	if(exception != null){
		throw exception;
	}else{	
		
		//发邮件
		//如果用户未认证，读取其注册时的名称，否则读取其认证名称
//		int certifyStatus = user.getCertifyStatus();
//		String name = certifyStatus==0?user.getCompanyName():ucUserDao.getCertifyNameByUserId(user.getUserId());
		
		taskExecutor.execute(new EmailTaskSend(GetEmailContext.EMAIL_PURCHASE_ADD,GetEmailContext.getXuQiuOperatorEmail()+","+GetEmailContext.getCaigouEmail(), GetEmailContext.getEmailMsgOfAddPurchase(user.getUserName(),user.getMobile(),bpList,1)));
		
		return true;
	}
		
	}

	
	/**
	 * 
	 * @Description: 获取Excel真实行数 （即采购品种等数据 行数）
	 * @Author: fanyuna
	 * @Date: 2015年10月19日
	 * @param fis
	 * @return
	 * @throws IOException
	 * @throws InvalidFormatException 
	 */
	public int getExcelRows(org.apache.poi.ss.usermodel.Workbook wb,org.apache.poi.ss.usermodel.Sheet sheet) throws Exception{
		int num = 0;
//		org.apache.poi.ss.usermodel.Workbook wb =  WorkbookFactory.create(fis); 
//		org.apache.poi.ss.usermodel.Sheet sheet = wb.getSheetAt(0);
			//前三行是公共数据+标题
			for(int rowNum = 3; rowNum <= sheet.getLastRowNum(); rowNum++ ){
				org.apache.poi.ss.usermodel.Row row = sheet.getRow(rowNum);  
				if(row!=null && row.getCell(0)!=null && StringUtils.isNotBlank(row.getCell(0).getStringCellValue())){
					num++;
				}
			}
//			if(wb !=null)
//				 wb.close();  //关闭
			return num;
					
	}
	
	/**
	 * 
	 * @Description: POI 解析Excel  兼容2003、2007
	 * @Author: fanyuna
	 * @Date: 2015年10月19日
	 * @param fis
	 * @return
	 * @throws IOException
	 */
	public List<BusiPurchaseDto> readExcelOfPoi(org.apache.poi.ss.usermodel.Workbook wb,org.apache.poi.ss.usermodel.Sheet sheet) throws Exception{
		List<BusiPurchaseDto>	purchaseDtoList = new ArrayList<BusiPurchaseDto>();
//		org.apache.poi.ss.usermodel.Workbook wb =  WorkbookFactory.create(fis); 
//		org.apache.poi.ss.usermodel.Sheet sheet = wb.getSheetAt(0);
		 for(int rowNum = 3; rowNum <= sheet.getLastRowNum(); rowNum++ ){  
			 org.apache.poi.ss.usermodel.Row row = sheet.getRow( rowNum);  
		        if(row == null){  
		          continue;  
		        }  
		      //如果品种名称就为空，则不保存
			    if(row.getCell(0)==null || getCellValue(row.getCell(0))==null || StringUtils.isBlank(getCellValue(row.getCell(0)).toString()))
			    	continue;
			    BusiPurchaseDto purchaseDto = new BusiPurchaseDto();
				purchaseDto.setPurchaserOrg(getCellValue(sheet.getRow(0).getCell(0))!=null?String.valueOf(getCellValue(sheet.getRow(0).getCell(0))):null);
				purchaseDto.setContact(getCellValue(sheet.getRow(1).getCell(1))!=null?String.valueOf(getCellValue(sheet.getRow(1).getCell(1))):null);
				purchaseDto.setContactPhone(getCellValue(sheet.getRow(1).getCell(5))!=null?String.valueOf(getCellValue(sheet.getRow(1).getCell(5))):null);
				Object period =getCellValue(sheet.getRow(1).getCell(8));
			if(period!=null && StringUtils.isNotBlank(period.toString())){
				purchaseDto.setValidPeriod(period.toString().substring(0, period.toString().length()-1));//把天字去掉
			}
			    
				  purchaseDto.setBreedName(getCellValue(row.getCell(0))!=null?String.valueOf(getCellValue(row.getCell(0))):null);
				  //品种名称不为空时，若此品种存在数据库中，则查其对应的ID  不需要
				  /*if(StringUtils.isNotBlank(row.getCell(0).getStringCellValue())){
					  Breed breed =  breedDao.selectBreedByBreedName(row.getCell(0).getStringCellValue());
					  if(breed!=null)
						  purchaseDto.setBreedId(breed.getBreedId());
				  }*/
				  
				  purchaseDto.setQuantity(getCellValue(row.getCell(1))!=null?String.valueOf(getCellValue(row.getCell(1))):null);
				if(getCellValue(row.getCell(2))!=null&& StringUtils.isNotBlank(getCellValue(row.getCell(2)).toString())){
					DictInfo dictInfo = dictInfoDao.getDictByValue(String.valueOf(getCellValue(row.getCell(2))));
					if(dictInfo != null)
						purchaseDto.setWunitCode(dictInfo.getDictCode());
				}
				purchaseDto.setStandardLevel(getCellValue(row.getCell(3))!=null?String.valueOf(getCellValue(row.getCell(3))):null);
				purchaseDto.setOrigin(getCellValue(row.getCell(4))!=null?String.valueOf(getCellValue(row.getCell(4))):null);
				purchaseDto.setQualityDescription(getCellValue(row.getCell(5))!=null?String.valueOf(getCellValue(row.getCell(5))):null);
				purchaseDto.setDeliveryAddress(getCellValue(row.getCell(6))!=null?String.valueOf(getCellValue(row.getCell(6))):null);
				Object delTime = getCellValue(row.getCell(7));
				if(delTime!=null ){
//					purchaseDto.setExpectDeliveryTime(TimeUtil.parseYMD(row.getCell(7).getStringCellValue()));
					if(delTime instanceof String){
						purchaseDto.setExpectDeliveryTime(TimeUtil.parseYMD(delTime.toString()));
					}else if(delTime instanceof Date)
						purchaseDto.setExpectDeliveryTime((Date)getCellValue(row.getCell(7)));
				}
				purchaseDto.setReceipt(getCellValue(row.getCell(8))!=null?String.valueOf(getCellValue(row.getCell(8))):null);
				if(getCellValue(row.getCell(8))!=null&& StringUtils.isNotBlank(getCellValue(row.getCell(8)).toString())){
					DictInfo dictInfo = dictInfoDao.getDictByValue(getCellValue(row.getCell(8)).toString());
					if(dictInfo != null)
						purchaseDto.setReceiptCode(dictInfo.getDictCode());
				}
				purchaseDto.setNote(getCellValue(row.getCell(9))!=null?String.valueOf(getCellValue(row.getCell(9))):null);
				
				purchaseDtoList.add(purchaseDto);
		 }
		 if(wb !=null)
			 wb.close();  //关闭
		return purchaseDtoList;
		
	}
	
	private Object getCellValue(org.apache.poi.ss.usermodel.Cell cell){
		Object obj =null;
	   if(cell!=null)
		switch(cell.getCellType()){
		//字符串
			case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING:
				obj = HtmlSymbolsUtil.encode(cell.getStringCellValue());
				break;
			//数字 包括日期
			case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)){
					obj=cell.getDateCellValue();
				}else{
					DecimalFormat df = new DecimalFormat("0");  
					obj=df.format(cell.getNumericCellValue());
				}
				break;
			//布尔
			case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BOOLEAN:
				obj = cell.getBooleanCellValue();
				break;
			
			case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BLANK:
				break;
			//方程式
			case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_FORMULA:
				obj = cell.getCellFormula();
				break;
			case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_ERROR:
				break;
			default:
				break;
		
		}
		return obj;
		
	}
	

	@Override
	public Map<String,String> getSalesOfPurchase(String username){
		Map<String,String> salseInfo=new HashMap<String,String>();
		UcUser userInfo=ucUserDao.findUcUserByUserName(username,false);//ucUserDao.findMemberByAllUserName(username);
		Long salesId = null;
		if(userInfo!=null){
			salesId=userInfo.getSalesmanId();
				
		}
		if(salesId != null){
			BossUser bossUser =bossUserDao.findBossUserByUserId(salesId.toString());
			if(bossUser!=null){
				salseInfo.put("name", bossUser.getUserName());
				salseInfo.put("phone", bossUser.getMobile());
				salseInfo.put("issale","Y");//是否绑定了交易员（绑定：Y，没绑定：N；若会员没有绑定业务员则系统默认指定业务员）
			}
		}
		else{
			salseInfo.put("name", SpringUtil.getConfigProperties("jointown.busi.purchase.salesman.name"));
			salseInfo.put("phone", SpringUtil.getConfigProperties("jointown.busi.purchase.salesman.phone"));
			salseInfo.put("issale","N");//是否绑定了交易员（绑定：Y，没绑定：N；若会员没有绑定业务员则系统默认指定业务员）
		}
		return salseInfo;
		
	}
	public List<BusiPurchaseVo> selectSameBreedList(Long breedId){
		return busiPurchaseDao.selectSameBreedList(breedId);
	}
	/**
	 * 
	 * @Description: 同类采购 显示与此采购单的采购品种相同的，最新通过审核的5条采购信息 
	 * @Author: shangcuijuan
	 * @Date: 2015年10月23日
	 * @param breedName 采购品种名称
	 * @return 
	 */
	public List<BusiPurchaseVo> selectSameBreedNameList(String breedName){
		return busiPurchaseDao.selectSameBreedNameList(breedName);
	}
	
	public List<BusiPurchase> selectPurOfSalerInfo(String saleName){
		return busiPurchaseDao.selectPurOfSalerInfo(saleName);
	}

	public  List<BusiPurchaseDto> readExcelForXls(FileInputStream fis) throws Exception{
		List<BusiPurchaseDto>	purchaseDtoList = new ArrayList<BusiPurchaseDto>();
			Workbook workbook = Workbook.getWorkbook(fis);
			Sheet sheet = workbook.getSheet(0); 
//			SheetSettings ss = sheet.getSettings();
//				ss.setPassword("zyc2015");
			//因第一行只有一列是采购单位，第二行 第二列是联系人的值、第四列是联系电话、第六列是有效期，第三行是其他字段标题，因此从第四行遍历
			for (int j = 3; j < sheet.getRows(); j++) {
				Cell[] cells = sheet.getRow(j); 
			    //如果品种名称就为空，则不保存
			    if(StringUtils.isBlank(cells[0].getContents()))
			    	continue;
				
				BusiPurchaseDto purchaseDto = new BusiPurchaseDto();
					purchaseDto.setPurchaserOrg(sheet.getRow(0)[0].getContents());
					purchaseDto.setContact(sheet.getRow(1)[1].getContents());
					purchaseDto.setContactPhone(sheet.getRow(1)[5].getContents());
					String period =sheet.getRow(1)[8].getContents();
				if(StringUtils.isNotBlank(period)){
					purchaseDto.setValidPeriod(period.substring(0, period.length()-1));//把天字去掉
				}
				    
//				  for(int k=0;k<cells.length;k++){
					  purchaseDto.setBreedName(cells[0].getContents());
					  //品种名称不为空时，若此品种存在数据库中，则查其对应的ID
					  if(StringUtils.isNotBlank(cells[0].getContents())){
						  Breed breed =  breedDao.selectBreedByBreedName(cells[0].getContents());
						  if(breed!=null)
							  purchaseDto.setBreedId(breed.getBreedId());
					  }
					  
					  purchaseDto.setQuantity(cells[1].getContents());
					if(StringUtils.isNotBlank(cells[2].getContents())){
						DictInfo dictInfo = dictInfoDao.getDictByValue(cells[2].getContents());
						if(dictInfo != null)
							purchaseDto.setWunitCode(dictInfo.getDictCode());
					}
					purchaseDto.setStandardLevel(cells[3].getContents());
					purchaseDto.setOrigin(cells[4].getContents());
					purchaseDto.setQualityDescription(cells[5].getContents());
					purchaseDto.setDeliveryAddress(cells[6].getContents());
					if(StringUtils.isNotBlank(cells[7].getContents())){
						purchaseDto.setExpectDeliveryTime(TimeUtil.parseYMD(cells[7].getContents()));
					}
					purchaseDto.setReceipt(cells[8].getContents());
					if(StringUtils.isNotBlank(cells[8].getContents())){
						DictInfo dictInfo = dictInfoDao.getDictByValue(cells[8].getContents());
						if(dictInfo != null)
							purchaseDto.setReceiptCode(dictInfo.getDictCode());
					}
					purchaseDto.setNote(cells[9].getContents());
					
//				  }  
					purchaseDtoList.add(purchaseDto);
			}   
		return purchaseDtoList;
	}


	@Override
	public List<BusiPurchaseVo> getExpiredPurchases() {
		return busiPurchaseDao.selectExpiredPurchaseInfo();
	}


	@Override
	public Boolean expirePurchases(final BusiPurchaseVo purchaseInfo) throws Exception{
		if(StringUtils.isEmpty(purchaseInfo.getPurchaseCode())){
			return false;
		}
		Exception exception = getTransactionTemplate().execute(new TransactionCallback<Exception>() {
			@Override
			public Exception doInTransaction(TransactionStatus status) {
				try{
					//查询一个批次号下的采购
//					List<BusiPurchase> purchases = busiPurchaseDao.selectPurchaseByCode(purchaseBatch.getPurchaseCode());
					busiPurchaseDao.expirePurchaseByPurchaseId(purchaseInfo.getPurchaseId());
					//记录采购日志
//					for(BusiPurchase p:purchases){
					BusiPurchase p = new BusiPurchase();
					BeanUtils.copyProperties(purchaseInfo, p);
					busiPurchaseLogDao.insertByBusiPurchase(p, BusinessLogEnum.PURCHASE_FINISHED.getCode(), "系统任务", "系统任务判断采购过期，结束采购！");
//					}
				}catch(Exception e){
					log.error("过期采购时异常："+e.getMessage(),e);
					status.setRollbackOnly();
					return e;
				}
				return null;
			}
		});
		if(exception != null){
			throw exception;
		}else{
			//修改SOLR中采购索引数据
			RabbitmqProducerManager.getInstance().pushPurchaseMsgForSolr(Arrays.asList(purchaseInfo.getPurchaseId()));
//			//发送短息给采购方
//			taskExecutor.execute(messageConfigManage.getMessageChannelTask(purchaseInfo.getPurchaserMobile(), 
//					GetMessageContext.getExpiredPurchaseMsg(purchaseInfo.getPurchaseCode())));
//			//发送短息给交易员
//			taskExecutor.execute(messageConfigManage.getMessageChannelTask(purchaseInfo.getTradersMobileOrAlternative(),
//					GetMessageContext.getExpiredPurchaseMsg(purchaseInfo.getPurchaseCode(),purchaseInfo.getPurchaser(),purchaseInfo.getPurchaserMobile())));
//			//发送邮件给相关交易员
//			String context = GetEmailContext.getExpiredPurchaseEmailMsg(purchaseInfo.getPurchaser(), purchaseInfo.getPurchaserMobile(),
//					purchaseInfo.getPurchaseCode());
//			EmailUtils.sendMail(GetEmailContext.EMAIL_PURCHASE_OVERTIME, purchaseInfo.getTradersEmailOrAlternative() , GetEmailContext.getCaigouEmail(), context);
			return true;
		}
	}


	@Override
	public List<BusiPurchaseVo> selectRecentlyPurchaseList(Integer maxCount)
			throws Exception {
		try {
			log.info("BusiPurchaseServiceImpl.selectRecentlyPurchaseList");
			return busiPurchaseDao.selectRecentlyPurchases(maxCount);
		} catch (Exception e) {
			log.error("BusiPurchaseServiceImpl.selectRecentlyPurchaseList:获取最近成交采购信息失败，错误信息：" + e.getMessage());
			throw setException(e);
		}
	}


	@Override
	public List<BusiPurchaseVo> selectMyPurchaseListBy(Page<BusiPurchaseVo> page)
			throws Exception {
		try {
			log.info("BusiPurchaseServiceImpl.selectMyPurchaseListBy");
			return busiPurchaseDao.getPurchaseManagePage(page);
		} catch (Exception e) {
			log.error("BusiPurchaseServiceImpl.selectMyPurchaseListBy:查询我的采购列表信息失败，错误信息：" + e.getMessage());
			throw setException(e);
		}
	}


	@Override
	public BusiPurchase getPurchaseDetailById(String purchaseId) throws Exception{
		try {
			log.info("BusiPurchaseServiceImpl.getPurchaseDetailById");
			return busiPurchaseDao.selectByPrimaryKey(purchaseId);
		} catch (Exception e) {
			log.error("BusiPurchaseServiceImpl.getPurchaseDetailById:根据主键获取采购信息失败，错误信息：" + e.getMessage());
			throw setException(e);
		}
	}
	
	/**
	 * @Description: 首页2.0 最新采购数据
	 * @Author: Calvin.wh
	 * @Date: 2015-11-9
	 */
	public List<BusiPurchase> getHomePageNewPurchase(){
		log.info("BusiPurchaseServiceImpl.getHomePageNewPurchase");
		List<BusiPurchase> newPurchase = new ArrayList<BusiPurchase>();
		try {
			newPurchase = busiPurchaseDao.queryHomePageNewPurchase();
		} catch (Exception e) {
			log.error("BusiPurchaseServiceImpl.getHomePageNewPurchase:首页2.0最新采收数据查询错误：" + e.getMessage());
		}
		return newPurchase;
	}
	
	
	/**
	 * @Description: 首页2.0 大货采购数据查询 业务逻辑 同 首页全国大仓
	 * @Author: Calvin.wh
	 * @Date: 2015-11-11
	 * @return
	 */
	public List<BusiPurchase> getBigPurchase(){
		log.info("BusiPurchaseServiceImpl.getBigPurchase");
		List<BusiPurchase> bigPurchase = new ArrayList<BusiPurchase>();
		List<BusiPurchase> autoCompletePurchase = new ArrayList<BusiPurchase>();
		try {
			bigPurchase = busiPurchaseDao.queryBigPurchase();
			autoCompletePurchase = busiPurchaseDao.queryHomePagePurchase();
			
			if(CollectionUtils.isEmpty(bigPurchase)){
				for (int i = 0; i < autoCompletePurchase.size();) {
					bigPurchase.add(autoCompletePurchase.get(i));
					break;
				}
			}
			
			for (int bigPurchaseLength = 0 ; bigPurchaseLength  < 9;) {
				if(bigPurchaseLength >= 9){
					return bigPurchase;
				}else if(bigPurchaseLength < 9){
					bigPurchase = completeBigPurchase(bigPurchase,autoCompletePurchase);
				}
				if(CollectionUtils.isEmpty(bigPurchase)){
					return bigPurchase;
				}else{
					bigPurchaseLength += bigPurchase.size() ;
				}
			}
		} catch (Exception e) {
			log.error("BusiPurchaseServiceImpl.getBigPurchase:首页2.0大货采购数据错误：" + e.getMessage());
		}
		return bigPurchase;
	}
	
	private List<BusiPurchase> completeBigPurchase(List<BusiPurchase> bigPurchase,List<BusiPurchase> autoCompletePurchase) throws Exception {
		for (int i = 0; i < autoCompletePurchase.size(); i++) {
			if (!comparePurchaseBreed(bigPurchase, autoCompletePurchase.get(i)))continue;
			bigPurchase.add(autoCompletePurchase.get(i));
			if (bigPurchase.size() >= 9)break;
		}
		return bigPurchase;
	}
	
	/**
	 * @Description: 大货采购数据 品种去重
	 * @Author: Calvin.wh
	 * @Date: 2015-11-11
	 * @param bigPurchase
	 * @param thisPurchase
	 * @return
	 */
	private boolean comparePurchaseBreed(List<BusiPurchase> bigPurchase,BusiPurchase thisPurchase){
		if(StringUtils.isBlank(thisPurchase.getBreedName())) return Boolean.FALSE;
		/** 品种名验证重复 **/
		for(BusiPurchase purchase : bigPurchase){
			if(thisPurchase.getBreedName().equals(purchase.getBreedName()))return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

}
