package com.jointown.zy.common.wms;

import java.io.OutputStream;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.google.gson.JsonObject;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;
import com.jointown.zy.common.constant.ConfigConstant;
import com.jointown.zy.common.constant.MessageConstant;
import com.jointown.zy.common.dto.WmsPicInfoDto;
import com.jointown.zy.common.dto.WmsQualityDto;
import com.jointown.zy.common.dto.WmsWareHouseDto;
import com.jointown.zy.common.dto.WmsWlDto;
import com.jointown.zy.common.dto.WmsWlSplitDto;
import com.jointown.zy.common.enums.ApiFlagEnums;
import com.jointown.zy.common.enums.SyslogEnum;
import com.jointown.zy.common.model.SyncDataLog;
import com.jointown.zy.common.rabbitmq.RabbitmqProducerManager;
import com.jointown.zy.common.service.SyncDataLogService;
import com.jointown.zy.common.service.WmsApiService;
import com.jointown.zy.common.task.EmailTaskSend;
import com.jointown.zy.common.util.BeanUtil;
import com.jointown.zy.common.util.GetEmailContext;
import com.jointown.zy.common.util.GsonFactory;
import com.jointown.zy.common.util.ImageHelper;
import com.jointown.zy.common.util.SFTPUtil;
import com.jointown.zy.common.util.SftpConfigInfo;
import com.jointown.zy.common.util.TimeUtil;
import com.jointown.zy.common.util.image.UploadUtils;

/**
 * Wms接口对接
 * @author ldp
 *
 */
@Component("wmsApi")
public class WmsApi {
	
	private static final Logger log = LoggerFactory.getLogger(WmsApi.class);
	
	@Autowired
	private SyncDataLogService syncDataLogService;
	@Autowired
	private WmsApiService wmsApiService;
	@Autowired
	private SftpConfigInfo sftpConfigInfo;
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	
	/**
	 * wms consumer
	 * @updater  fanyuna 增加返回值，以便调用方根据结果处理
	 * @param url
	 * @param json
	 */
	public int wmsConsumer(ApiFlagEnums apiFlag,String url,String plainJson,ApiFlagEnums flag){
		log.debug("apiFlag is :" + String.valueOf(apiFlag));
		log.debug("plain message is:" + plainJson);
		String dataId = null;
		String json = null;
		try {
			dataId = WmsApiCommon.jsonResolve(plainJson, "dataId");
			json = WmsApiCommon.jsonResolve(plainJson, "data");
			int i = 0;
			while(i < 3){
				String respdata = WmsApiCommon.wmsEncAndSign(url, json);
				String statusCode = WmsApiCommon.jsonResolve(respdata, "statusCode");
//				String statusCode = sendWmsApiReq(url,json);
				if ("101".equals(statusCode)) {
					wmsApiService.wmsApiSynLog(apiFlag.name(),dataId, json, respdata, Integer.parseInt(ApiFlagEnums.SYNC_LOG_SYNC_STATUS_SUCCESS.getFlag()),flag);
					break;
				}
				//修改操作时如果不存在返回106 客户端需要将更新后的记录再调用新增接口
				else if("106".equals(statusCode)){
					if(apiFlag.equals(ApiFlagEnums.BREED_UPDATE)){
						RabbitmqProducerManager.getInstance().putMsgForApi(ApiFlagEnums.BREED_ADD, plainJson);
					}else if(apiFlag.equals(ApiFlagEnums.BOSS_USER_UPDATE)){
						RabbitmqProducerManager.getInstance().putMsgForApi(ApiFlagEnums.BOSS_USER_ADD, plainJson);
					}else if(apiFlag.equals(ApiFlagEnums.UC_USER_UPDATE)){
						RabbitmqProducerManager.getInstance().putMsgForApi(ApiFlagEnums.UC_USER_ADD, plainJson);
					}
					wmsApiService.wmsApiSynLog(apiFlag.name(),dataId, json, respdata, Integer.parseInt(ApiFlagEnums.SYNC_LOG_SYNC_STATUS_FAIL.getFlag()),flag);
					break;
				}
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					log.debug("Thread Interrupt exception:",e);
				}
				i++;
				if(i == 3){
					wmsApiService.wmsApiSynLog(apiFlag.name(),dataId, json, respdata, Integer.parseInt(ApiFlagEnums.SYNC_LOG_SYNC_STATUS_FAIL.getFlag()),flag);
					WmsApiCommon.wmsFail3SaveRedis(String.valueOf(apiFlag),json, statusCode);
					
					//接口调用失败发邮件通知开发、运营、产品
					taskExecutor.execute(new EmailTaskSend(GetEmailContext.EMAIL_CALL_INTERFACE_FAIL,ConfigConstant.EMAIL_FROM_WMS,GetEmailContext.getEmailAddress(MessageConstant.INTERFACE_FAIL_DEV_EMAIL_ADDRESS.getMessageKey())+","+GetEmailContext.getEmailAddress(MessageConstant.INTERFACE_FAIL_OPERATE_EMAIL_ADDRESS.getMessageKey())+","+GetEmailContext.getEmailAddress(MessageConstant.INTERFACE_FAIL_PRODUCT_EMAIL_ADDRESS.getMessageKey()), GetEmailContext.getInterfaceEmailMsg(apiFlag,dataId,json,statusCode),ConfigConstant.EMAIL_FROM_WMS_USERNAME,ConfigConstant.EMAIL_FROM_WMS_PASSWORD));
				}
			}
			return 1;
		} catch (Exception e) {
			log.debug("send wms api req error is:",e);
			wmsApiService.wmsApiSynLog(apiFlag.name(),dataId, json, e.getMessage(), Integer.parseInt(ApiFlagEnums.SYNC_LOG_SYNC_STATUS_FAIL.getFlag()),flag);
			try {
				WmsApiCommon.wmsFail3SaveRedis(String.valueOf(apiFlag),json, e.getMessage());
				
				//接口调用失败发邮件通知开发、运营、产品
				taskExecutor.execute(new EmailTaskSend(GetEmailContext.EMAIL_CALL_INTERFACE_FAIL,ConfigConstant.EMAIL_FROM_WMS, GetEmailContext.getEmailAddress(MessageConstant.INTERFACE_FAIL_DEV_EMAIL_ADDRESS.getMessageKey())+","+GetEmailContext.getEmailAddress(MessageConstant.INTERFACE_FAIL_OPERATE_EMAIL_ADDRESS.getMessageKey())+","+GetEmailContext.getEmailAddress(MessageConstant.INTERFACE_FAIL_PRODUCT_EMAIL_ADDRESS.getMessageKey()), GetEmailContext.getInterfaceEmailMsg(apiFlag,dataId,json,e.getMessage()),ConfigConstant.EMAIL_FROM_WMS_USERNAME,ConfigConstant.EMAIL_FROM_WMS_PASSWORD));
		
			} catch (Exception e1) {
				log.debug(e.getMessage());
			}
			return 0;
		}
	}
	
	/**
	 * 调用接口请求
	 * @param url
	 * @param json
	 * @return
	 */
	public String sendWmsApiReq(String url,String json)throws Exception{
		String respdata = null;
		respdata = WmsApiCommon.wmsEncAndSign(url, json);
		if (null == respdata || "".equals(respdata)) {
			log.debug("url:"+url+"json:"+json+" respdata is empty!");
			return String.valueOf(104);
		}
		String statusCode = WmsApiCommon.jsonResolve(respdata, "statusCode");
		log.debug("statusCode is :" + statusCode);
		if ("101".equals(statusCode)) {
			log.debug("url:"+url+"json:"+json+" api success!");
		}else if("102".equals(statusCode)){
			log.debug("url:"+url+"json:"+json+" api fail!");
		}else if ("103".equals(statusCode)) {
			log.debug("url:"+url+"json:"+json+" api vertify fail!");
		}
		return statusCode;
	}
	
	
	/**
	 * 接受wms的请求后，处理数据
	 * @param json
	 * @return
	 */
	public String receiveWmsApiReq(ApiFlagEnums apiFlag,String json){
		log.debug("wms req data is:" + json);
		JsonObject respJson = new JsonObject();
		String jsonData = null;
		StringBuilder dataId = new StringBuilder();
		try {
			//获取数据的数据为空，返回104
			if(StringUtils.isBlank(json) ){
				respJson.addProperty("statusCode", 104);
				respJson.addProperty("reason", "请求json串为空");
				log.info("[statusCode:104]wms request data is null");
				return String.valueOf(respJson);
			}
			Map<String, String> params = BeanUtil.jsonToMap(json);;
			String data = params.get("data");//加密数据
			String digest = params.get("digest");//签名数据
			if(StringUtils.isBlank(data)||StringUtils.isBlank(digest)){
				respJson.addProperty("statusCode", 104);
				respJson.addProperty("reason", "请求串中加密或签名数据为空");
				log.info("[statusCode:104]data or digest is null");
				return String.valueOf(respJson);
			}
			//验签
			if(!WmsApiCommon.wmsVerity(data, digest)){
				respJson.addProperty("statusCode", 103);
				respJson.addProperty("reason", "验签失败");
				log.info("[statusCode:103]signature is fail");
				return String.valueOf(respJson);//验签失败返回
			}
			//解密数据
			jsonData = WmsApiCommon.decWms(data);
			
			//业务逻辑处理
			Map<String,String> serviceMap = wmsService(apiFlag,jsonData,dataId);
		  if(serviceMap!=null && StringUtils.isNotBlank(serviceMap.get("code"))){
			if(serviceMap.get("code").equals("1")){
				respJson.addProperty("statusCode", 101);
				log.info("[statusCode:101]success");
				wmsApiService.wmsApiSynLog(apiFlag.name(), dataId.toString(), jsonData, "101[success]", Integer.parseInt(ApiFlagEnums.SYNC_LOG_SYNC_STATUS_SUCCESS.getFlag()),ApiFlagEnums.SYNC_LOG_TYPE_WMS);
				return String.valueOf(respJson);
			}
			//不存在的编码
			else if(serviceMap.get("code").equals("106")){
				respJson.addProperty("statusCode", 106);
				respJson.addProperty("reason", "此记录不存在");
				log.info("[statusCode:106]fail");
				wmsApiService.wmsApiSynLog(apiFlag.name(), dataId.toString(), jsonData, "106 "+serviceMap.get("reason"), Integer.parseInt(ApiFlagEnums.SYNC_LOG_SYNC_STATUS_FAIL.getFlag()),ApiFlagEnums.SYNC_LOG_TYPE_WMS);
				return String.valueOf(respJson);
			}else if(serviceMap.get("code").equals("105")){
				respJson.addProperty("statusCode", 105);
				respJson.addProperty("reason", serviceMap.get("reason"));
				log.info("[statusCode:105]fail");
				wmsApiService.wmsApiSynLog(apiFlag.name(), dataId.toString(), jsonData, "105 "+serviceMap.get("reason"), Integer.parseInt(ApiFlagEnums.SYNC_LOG_SYNC_STATUS_FAIL.getFlag()),ApiFlagEnums.SYNC_LOG_TYPE_WMS);
				return String.valueOf(respJson);
			}else if(serviceMap.get("code").equals("104")){
				respJson.addProperty("statusCode", 104);
				respJson.addProperty("reason", serviceMap.get("reason"));
				log.info("[statusCode:104]fail");
				wmsApiService.wmsApiSynLog(apiFlag.name(), dataId.toString(), jsonData, "104 "+serviceMap.get("reason"), Integer.parseInt(ApiFlagEnums.SYNC_LOG_SYNC_STATUS_FAIL.getFlag()),ApiFlagEnums.SYNC_LOG_TYPE_WMS);
				return String.valueOf(respJson);
			}else{
				respJson.addProperty("statusCode", 102);
				String reason="操作失败";
				if(StringUtils.isNotBlank(serviceMap.get("reason")))
					reason=serviceMap.get("reason");
				respJson.addProperty("reason", reason);
				log.info("[statusCode:102]fail");
				wmsApiService.wmsApiSynLog(apiFlag.name(), dataId.toString(), jsonData, "102", Integer.parseInt(ApiFlagEnums.SYNC_LOG_SYNC_STATUS_FAIL.getFlag()),ApiFlagEnums.SYNC_LOG_TYPE_WMS);
				return String.valueOf(respJson);
			}
		  }
		  return null;
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("wms api service error is:",e);
			respJson.addProperty("statusCode", 104);
			respJson.addProperty("reason", "其他异常");
			wmsApiService.wmsApiSynLog(apiFlag.name(), dataId.toString(), jsonData, "104[wms api service]", Integer.parseInt(ApiFlagEnums.SYNC_LOG_SYNC_STATUS_FAIL.getFlag()),ApiFlagEnums.SYNC_LOG_TYPE_WMS);
			return String.valueOf(respJson);
		}
		
	}

	/**
	 * wms api接口同步，业务处理
	 * @param apiFlag
	 * 			接口类型
	 * @param jsonData
	 * @param dataId
	 * @return
	 */
	public Map<String,String> wmsService(ApiFlagEnums apiFlag,String jsonData,StringBuilder dataId)throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		int flag =0;
		switch (apiFlag) {
			case WL_ADD:
				//解密之后的数据转对象
				WmsWlDto wmsWl = BeanUtil.jsonToObject(jsonData, WmsWlDto.class);
				dataId.append(wmsWl.getWlId());
				Map<String,String> wlCheckMap = WmsReqParamsCheck.wlCheckParams(wmsWl);
				if(wlCheckMap!=null && wlCheckMap.get("code")!=null && wlCheckMap.get("code").equals("105")){
					map.put("code", wlCheckMap.get("code"));
					map.put("reason", wlCheckMap.get("reason"));					
					break;
				}
				//添加仓单之前先上传图片
				Map<String,String> picInfo = getUploadPicInfo(wmsWl);
				if(picInfo==null){
					map.put("code", "104");
					map.put("reason", "上传图片失败");
					break;
				}
				flag = wmsApiService.syncWlInfo(wmsWl,picInfo);
				map.put("code", flag+"");
				break;
			case WL_UPDATE:
				WmsWlDto wmsWlUpdate = BeanUtil.jsonToObject(jsonData, WmsWlDto.class);
				dataId.append(wmsWlUpdate.getWlId());
				//仓单修改 只需传修改的项
				/*if(WmsReqParamsCheck.wlCheckParams(wmsWlUpdate)==105){
					flag=105;
					break;
				}*/
				//更新仓单之前先上传图片
			//仓单图片不为空
			Map<String,String> updatePicInfo = new HashMap<String,String>();
			List<WmsPicInfoDto> picInfoList = wmsWlUpdate.getPicInfo();
			 if(picInfoList!=null && picInfoList.size()>0){
			     for(WmsPicInfoDto pic : picInfoList){
					if(pic != null){
						String picPath = uploadPic(pic.getPicUrl(),pic.getPicType());
						if(picPath==null){
							map.put("code", "104");
							map.put("reason", "上传仓单图片失败");
							break;
						}
						updatePicInfo.put(pic.getPicType()+"", picPath);
						}
					}
			   }
			 //质检图URL不为空
			  WmsQualityDto qualityInfo =  wmsWlUpdate.getQualityInfo();
			  if(qualityInfo!=null && StringUtils.isNotBlank(qualityInfo.getQualityPic())){
				  String qulityPath = uploadPic(qualityInfo.getQualityPic(),7);
					if(qulityPath==null){
						map.put("code", "104");
						map.put("reason", "上传仓单质检图片失败");
						break;
					}
					updatePicInfo.put("7", qulityPath);
			  }
				flag = wmsApiService.wmsWlUpdate(wmsWlUpdate,updatePicInfo);
				map.put("code", flag+"");
				break;
			case WARE_HOUSE_ADD:
				WmsWareHouseDto wmsWareHouse = BeanUtil.jsonToObject(jsonData, WmsWareHouseDto.class);
				dataId.append(wmsWareHouse.getWareHouseCode());
				Map<String,String> whCheckMap = WmsReqParamsCheck.wareHouseParams(wmsWareHouse);
				if(whCheckMap!=null && whCheckMap.get("code")!=null && whCheckMap.get("code").equals("105")){
					map.put("code", whCheckMap.get("code"));
					map.put("reason", whCheckMap.get("reason"));					
					break;
				}
				flag = wmsApiService.wmsWareHouseAdd(wmsWareHouse);
				map.put("code", flag+"");
				break;
			case WARE_HOUSE_UPDATE:
				WmsWareHouseDto wmsWareHouseUpdate = BeanUtil.jsonToObject(jsonData, WmsWareHouseDto.class);
				dataId.append(wmsWareHouseUpdate.getWareHouseCode());
				Map<String,String> whuCheckMap = WmsReqParamsCheck.wareHouseParams(wmsWareHouseUpdate);
				if(whuCheckMap!=null && whuCheckMap.get("code")!=null && whuCheckMap.get("code").equals("105")){
					map.put("code", whuCheckMap.get("code"));
					map.put("reason", whuCheckMap.get("reason"));					
					break;
				}
				flag = wmsApiService.wmsWareHouseUpdate(wmsWareHouseUpdate);
				map.put("code", flag+"");
				break;
			case WL_FREEZE_SUCCESS:
				Map<String,String> frzeeMap = BeanUtil.jsonToMap(jsonData);
				dataId.append(frzeeMap.get("orderId"));
				if (StringUtils.isBlank(frzeeMap.get("wlId"))) {
					log.info("WL_FREEZE_SUCCESS api:wlId is not null");
					map.put("code", "105");
					map.put("reason", "仓单号为空");	
					break;
				}
				if (StringUtils.isBlank(frzeeMap.get("wlActualCount"))) {
					log.info("WL_FREEZE_SUCCESS api:wlActualCount is not null");
					map.put("code", "105");
					map.put("reason", "实际冻结数量为空");	
					break;
				}
				if (StringUtils.isBlank(frzeeMap.get("orderId"))) {
					log.info("WL_FREEZE_SUCCESS api:orderId is not null");
					map.put("code", "105");
					map.put("reason", "订单号为空");
					break;
				}
				flag = wmsApiService.wmsFreezeSuccess(frzeeMap);
				if(flag ==-1){
					map.put("code", "102");
					map.put("reason", "此订单不接受备货操作");
				}else
				map.put("code", flag+"");
				break;
			case WL_SPLIT_SUCCESS:
				WmsWlSplitDto wlSplitDto = BeanUtil.jsonToObject(jsonData,WmsWlSplitDto.class);
				dataId.append(wlSplitDto.getNewWlInfo().getOrderId());
				Map<String,String> wsCheckMap = WmsReqParamsCheck.wlSplitCheckParams(wlSplitDto);
				if(wsCheckMap!=null && wsCheckMap.get("code")!=null && wsCheckMap.get("code").equals("105")){
					map.put("code", wsCheckMap.get("code"));
					map.put("reason", wsCheckMap.get("reason"));					
					break;
				}
				flag = wmsApiService.wmsWlSplitSuccess(wlSplitDto);
				if(flag ==-1){
					map.put("code", "102");
					map.put("reason", "此订单已经分割");
				}else
				map.put("code", flag+"");
				break;
			default:
				break;
			}
		return map;
	}
	
	/**
	 * 处理wms仓单之前，先上传图片
	 * @param wl
	 * @return
	 */
	public Map<String,String> getUploadPicInfo(WmsWlDto wl){
		Map<String,String> map = new HashMap<String,String>();
		String qualityPic = wl.getQualityInfo().getQualityPic();
		//质检图片可以为空
		if(StringUtils.isNotBlank(qualityPic)){
			String qulityPath = uploadPic(qualityPic,7);
			if(qulityPath==null)
				return null;
			map.put("7", qulityPath);
		}
		
		List<WmsPicInfoDto> picInfoList = wl.getPicInfo();
		for(WmsPicInfoDto pic : picInfoList){
			if(pic != null){
			String picPath = uploadPic(pic.getPicUrl(),pic.getPicType());
			if(picPath==null)
				return null;
			map.put(pic.getPicType()+"", picPath);
			}
		}
		return map;
	}
	
	/**
	 * wms质检图片上传
	 * @param path
	 * @param type
	 * 			图片类型，7是质检报告 
	 * @return
	 */
	public String uploadPic(String path,int type){
		SFTPUtil sftp = SFTPUtil.getSingleton();  //sftp初始化
		ChannelSftp channel = null;               //sftp管道声明
		Session session = null;  //session声明
		try {
			URL url = new URL(path);
			String fileName = UploadUtils.generateFilename("jpg");
			String dateDir = TimeUtil.getTimeShowByTimePartten(new Date(),"yyyyMM");
			session = sftp.openSession(sftpConfigInfo.getSftpIp(), sftpConfigInfo.getSftpUserName(), sftpConfigInfo.getSftpPassword(),Integer.parseInt(sftpConfigInfo.getSftpPort()));
			channel = sftp.getChannelSftp(session,"sftp");  //获取管道符
			
			//上传原图片到资源服务器目录下
			sftp.upload(channel, fileName, url.openStream(), dateDir,sftpConfigInfo.getSftpDataDir(),sftpConfigInfo.getSftpImagesDir(),sftpConfigInfo.getSftpProjectDir());
		    if(type != 7){
				//上传缩略图到资源服务器目录下
				int [] widths={sftpConfigInfo.getSftpMiddleWidth(),sftpConfigInfo.getSftpXBigWidth(),sftpConfigInfo.getSftpXXBigWidth()};
				for (int width : widths) {
			       String scaleFileName = fileName.substring(0,fileName.lastIndexOf("."))+"_"+width+".jpg";
			       OutputStream thumbOutstream = channel.put(scaleFileName);
			       ImageHelper.scaleImage(url.openStream(), thumbOutstream, width, width);
				}
		    }
			return sftpConfigInfo.getSftpImagesDir()+"/"+sftpConfigInfo.getSftpProjectDir()+"/"+dateDir+"/"+fileName;
		} catch (Exception e) {
			log.debug("wms api upload pic error is:",e);
			return null;
		}finally{
			//关闭管道连接
	        try {
				sftp.closeChannel(channel,session);
			} catch (Exception e) {
				log.debug("wms api upload pic error is:",e);
				return null;
			}
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
	/*public void wmsApiSynLog(String apiFlag,String dataId,String data,String reasons,Integer synStatus,ApiFlagEnums flag){
		SyncDataLog syncDataLog = new SyncDataLog();
		syncDataLog.setDataType(Integer.parseInt(flag.getFlag()));//1wms 2供应链金融
		syncDataLog.setOperation(apiFlag);
		syncDataLog.setDataId(dataId);
		syncDataLog.setData(data);
		syncDataLog.setReason(reasons);
		syncDataLog.setSyncStatus(synStatus);
		syncDataLog.setTime(new Date());
		syncDataLogService.addSyncDataLog(syncDataLog);
		log.info(SyslogEnum.WMS_API.getCode()+GsonFactory.createGson("yyyy-MM-dd HH:mm:ss").toJson(syncDataLog));
	}*/
	
	
	
}
