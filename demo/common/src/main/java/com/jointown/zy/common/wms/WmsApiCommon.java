package com.jointown.zy.common.wms;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jointown.zy.common.model.BossUser;
import com.jointown.zy.common.model.UcUser;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.redis.RedisManager;
import com.jointown.zy.common.util.DES2;
import com.jointown.zy.common.util.EncryptUtil;
import com.jointown.zy.common.util.SpringUtil;
import com.jointown.zy.common.util.TimeUtil;

/**
 * wms api 公用方法
 * @author ldp
 * date 2015-03-11
 * version 0.1
 */
public class WmsApiCommon {
	
	private static final Logger log = LoggerFactory.getLogger(WmsApiCommon.class);
	
	public static RedisManager redisManager;
	
	/**md5签名key*/
	public static final String MD5_KEY;
	/**des加密key*/
	public static final String DES_KEY;
	public static final String WMS_ADD_USER_URL;
	public static final String WMS_UPDATE_USER_URL;
	public static final String WMS_BOSS_USER_ADD_URL;
	public static final String WMS_BOSS_USER_UPDATE_URL;
	public static final String WMS_BREED_ADD_URL;
	public static final String WMS_BREED_UPDATE_URL;
	//仓单解冻接口URL
	public static final String WMS_WL_UNFREEZE_URL;
	//交易冻结
	public static final String WMS_WL_FREEZE_URL;
	//申请分割
	public static final String WMS_WL_APPLY_SPLIT_URL;
	
	public static final String SCF_ADD_USER_URL;
	public static final String SCF_UPDATE_USER_URL;
	public static final String SCF_BOSS_USER_ADD_URL;
	public static final String SCF_BOSS_USER_UPDATE_URL;
	public static final String SCF_BREED_ADD_URL;
	public static final String SCF_BREED_UPDATE_URL;
	
	//仓单新增同步URL
	public static final String WMS_WL_ADD_URL;
	//仓单修改同步URL
	public static final String WMS_WL_UPDATE_URL;
	//仓库新增同步URL
	public static final String WMS_WAREHOUSE_ADD_URL;
	//仓库修改同步URL
	public static final String WMS_WAREHOUSE_UPDATE_URL;
	//仓单冻结成功URL
	public static final String WMS_WL_FREEZE_SUCCESS_URL;
	//仓单分割成功URL
	public static final String WMS_WL_SPLIT_SUCCESS_URL;
	//仓单出库阈值查询URL
	public static final String WMS_WL_EXMAXCOUNT_URL;
	
	
	static{
		MD5_KEY = SpringUtil.getConfigProperties("wms.md5.key");
		DES_KEY = SpringUtil.getConfigProperties("wms.des.key");
		WMS_ADD_USER_URL = SpringUtil.getConfigProperties("wms.user.add.api.url");
		WMS_UPDATE_USER_URL = SpringUtil.getConfigProperties( "wms.user.update.api.url");
		WMS_BOSS_USER_ADD_URL = SpringUtil.getConfigProperties("wms.bossuser.add.api.url");
		WMS_BOSS_USER_UPDATE_URL = SpringUtil.getConfigProperties( "wms.bossuser.update.api.url");
		WMS_BREED_ADD_URL =SpringUtil.getConfigProperties( "wms.breed.add.api.url");
		WMS_BREED_UPDATE_URL = SpringUtil.getConfigProperties("wms.breed.update.api.url");
		redisManager = (RedisManager) SpringUtil.getBean("redisManager");
		WMS_WL_UNFREEZE_URL = SpringUtil.getConfigProperties("wms.wl.unfreeze.api.url");
		WMS_WL_FREEZE_URL = SpringUtil.getConfigProperties("wms.wl.freeze.api.url");
		WMS_WL_APPLY_SPLIT_URL =SpringUtil.getConfigProperties("wms.wl.apply.split.api.url");
		
		WMS_WL_ADD_URL = SpringUtil.getConfigProperties("wms.wl.add.api.url");
		WMS_WL_UPDATE_URL = SpringUtil.getConfigProperties( "wms.wl.update.api.url");
		WMS_WAREHOUSE_ADD_URL = SpringUtil.getConfigProperties("wms.warehouse.add.api.url");
		WMS_WAREHOUSE_UPDATE_URL = SpringUtil.getConfigProperties( "wms.warehouse.update.api.url");
		WMS_WL_FREEZE_SUCCESS_URL = SpringUtil.getConfigProperties( "wms.wl.freeze.success.api.url");
		WMS_WL_SPLIT_SUCCESS_URL = SpringUtil.getConfigProperties( "wms.wl.split.success.api.url");
		WMS_WL_EXMAXCOUNT_URL = SpringUtil.getConfigProperties("wms.wl.exmaxcount.api.url");
		
		SCF_ADD_USER_URL = SpringUtil.getConfigProperties( "scf.user.add.api.url");
		SCF_UPDATE_USER_URL = SpringUtil.getConfigProperties( "scf.user.update.api.url");
		SCF_BOSS_USER_ADD_URL = SpringUtil.getConfigProperties( "scf.bossuser.add.api.url");
		SCF_BOSS_USER_UPDATE_URL = SpringUtil.getConfigProperties( "scf.bossuser.update.api.url");
		SCF_BREED_ADD_URL = SpringUtil.getConfigProperties( "scf.breed.add.api.url");
		SCF_BREED_UPDATE_URL = SpringUtil.getConfigProperties( "scf.breed.update.api.url");
	}
	
	/**
	 * 公用加密、签名方法
	 * @param url
	 * 			接口地址
	 * @param jsonStr
	 * 			json格式数据串
	 * @return 
	 * 			返回响应数据
	 */
	public static String wmsEncAndSign(String url,String jsonStr)throws Exception{
		log.info("wms api url is：" + url);
		log.info("encrypted before data is :" + jsonStr);
		log.info("charset is :" + Charset.defaultCharset());
		DES2 des2 = null;
		String encryptedInfo = null;
		String respData = null;
		des2 = new DES2();//加密
		encryptedInfo = des2.encrypt(jsonStr);
		String digest = EncryptUtil.getMD5(encryptedInfo + MD5_KEY, "UTF-8");
		//请求数据
		JsonObject json = new JsonObject();
		json.addProperty("data", encryptedInfo);
		json.addProperty("digest", digest);
		log.info("wms request params:" + json.toString());
		respData = WmsRestHttpUtil.wmsRestPost(url, String.valueOf(json));
		log.info("wms api response data is:" + respData);
		//签名
		return respData;
	}
	
	/**
	 * json串解析
	 * @param jsonStr
	 * 			json字符串
	 * @param jsonKey
	 * @return
	 */
	public static String jsonResolve(String jsonStr,String jsonKey){
		Gson gson = new Gson();
		JsonObject respJson = gson.fromJson(jsonStr, JsonObject.class);
		return respJson.get(jsonKey).getAsString();
	}
	
	/**
	 * 调接口3次不成功，将信息保存到redis
	 * @param apiFlag
	 * @param data
	 * @param reasons
	 */
	public static void wmsFail3SaveRedis(String apiFlag,String data,String reasons) throws Exception{
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("sysFlag", "wms");
		jsonObject.addProperty("apiFlag", apiFlag);
		jsonObject.addProperty("data", data);
		jsonObject.addProperty("statusCode", reasons);
		jsonObject.addProperty("date", TimeUtil.getYMDHMS(new Date()));
		String key = RedisEnum.KEY_PREFIX_WMS_API_FLAG.getValue();
		redisManager.rpush(key,String.valueOf(jsonObject));
		log.info("wms api save redis key is:" + key);
		
	}
	
	/**
	 * UcUser转换成json串
	 * @param ucUser
	 * @author fanyuna  2015.08.07 增加实名认证名称参数
	 * @author fanyuna  2015.11.16 邮箱、备注字段为空时，传至wms为""，否则将邮箱或备注清空时，wms不予修改
	 * @param name  实名认证名称
	 * @return
	 */
	public static String ucUserToJsonStr(UcUser ucUser,String name){
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("certify", ucUser.getCertifyStatus());
		//add by fanyuna 2015.08.07 将userName的值改为认证后的名称 start
		/*if (null != ucUser.getCompanyName() && !"".equals(ucUser.getCompanyName())) {
			jsonObject.addProperty("companyName", ucUser.getCompanyName());
		}*/
		if (null != name && !"".equals(name)) {
			jsonObject.addProperty("companyName", name);
		}
		//add by fanyuna 2015.08.07 将userName的值改为认证后的名称 end
//		if (null != ucUser.getEmail() && !"".equals(ucUser.getEmail())) {
			jsonObject.addProperty("email", ucUser.getEmail()!=null?ucUser.getEmail():"");
//		}
		if (null != ucUser.getMobile() && !"".equals(ucUser.getMobile())) {
			jsonObject.addProperty("mobile", ucUser.getMobile());
		}
//		if (null != ucUser.getRemark() && !"".equals(ucUser.getRemark())) {
			jsonObject.addProperty("remark", ucUser.getRemark()!=null?ucUser.getRemark():"");
//		}
		if (null != ucUser.getStatus()) {
			jsonObject.addProperty("status", ucUser.getStatus());
		}
		
		jsonObject.addProperty("userName", ucUser.getUserName());
		
		JsonObject plainJson = new JsonObject();
		plainJson.addProperty("dataId", ucUser.getUserName());
		plainJson.addProperty("data", String.valueOf(jsonObject));
		return String.valueOf(plainJson);
	}
	
	
	/**
	 * bossUser转换成json串
	 * @return
	 */
	public static String bossUserToJsonStr(BossUser bossUser2){
		JsonObject jsonObject = new JsonObject();
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		jsonObject.addProperty("createTime", sdFormat.format(bossUser2.getCreateTime()));
		if (null != bossUser2.getEmail() && !"".equals(bossUser2.getEmail())) {
			jsonObject.addProperty("email", bossUser2.getEmail());
		}
		if (null != bossUser2.getMobile() && !"".equals(bossUser2.getMobile())) {
			jsonObject.addProperty("mobile", bossUser2.getMobile());
		}
		if (null != bossUser2.getPhone() && !"".equals(bossUser2.getPhone())) {
			jsonObject.addProperty("phone", bossUser2.getPhone());
		}
		jsonObject.addProperty("status", bossUser2.getStatus());
		jsonObject.addProperty("userCode", bossUser2.getUserCode());
		if (null != bossUser2.getUserName() && !"".equals(bossUser2.getUserName())) {
			jsonObject.addProperty("userName", bossUser2.getUserName());
		}
		JsonObject plainJson = new JsonObject();
		plainJson.addProperty("dataId", bossUser2.getUserCode());
		plainJson.addProperty("data", String.valueOf(jsonObject));
		return String.valueOf(plainJson);
	}
	
	/**
	 * 验签，接受到wms数据之后进行验签
	 * @param data
	 * 			加密数据
	 * @param digest
	 * 			接受的签名数据
	 * @return
	 */
	public static boolean wmsVerity(String data,String digest)throws Exception{
		String signData = EncryptUtil.getMD5(data + MD5_KEY, "UTF-8");
		if(signData.equals(digest)){
			return true;
		}
		return false;
	}
	
	/**
	 * 解密wms请求的数据
	 * @param data
	 * 			加密的数据
	 * @return
	 */
	public static String decWms(String data)throws Exception{
		DES2 des2 = new DES2();
		return des2.decrypt(data);
	}
	
	
}
