package com.jointown.zy.common.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.jointown.zy.common.dto.BusiPurchaseDto;
import com.jointown.zy.common.model.BossUser;
import com.jointown.zy.common.model.BusiPurchase;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.vo.BusiPurchaseVo;
import com.jointown.zy.common.vo.UcUserVo;

public interface BusiPurchaseService {
	
	/**
	 * 
	 * @Description: 根据会员ID获取其对应的业务员信息
	 * @Author: fanyuna
	 * @Date: 2015年10月13日
	 * @param userId 会员ID
	 * @return 没有绑定，则为空
	 */
	public BossUser getSalemanByUcuserId(Long userId);
	
	/**
	 * 
	 * @Description: 根据会员ID获取其采购单对应的业务员姓名及电话
	 * 		如果会员绑定的有业务员则读取业务员的姓名及电话；若未绑定，读取配置文件中配置的业务员的姓名及电话。
	 * @Author: fanyuna
	 * @Date: 2015年10月13日
	 * @param userId 会员ID
	 * @return name 姓名，phone 电话
	 */
	public Map<String,String> getSalesmanOfPurchase(Long userId);
	
	/**
	 * 
	 * @Description: 获取采购批次号
	 * @Author: fanyuna
	 * @Date: 2015年10月14日
	 * @param userId 会员ID
	 * @return
	 */
	public String createPurchaseCode(Long userId);

	/**
	 * 
	 * @Description: 单条采购保存
	 * @Author: fanyuna
	 * @Date: 2015年10月16日
	 * @param busiPurchaseDto
	 * @param user
	 * @param purchaseCode
	 * @return
	 */
	public int savePurchase(BusiPurchaseDto busiPurchaseDto,UcUserVo user,String purchaseCode);

	/**
	 * 采购详情
	 * @Author: shangcuijuan
	 * @Date: 2015年10月15日
	 * @param purchaseId
	 * @return
	 */
	public BusiPurchaseVo selectPurchaseInfo(String purchaseId);
	/**
	 * 
	 * @Description: 根据会员名获取其采购单对应的业务员姓名及电话
	 * 		如果会员绑定的有业务员则读取业务员的姓名及电话；若未绑定，读取配置文件中配置的业务员的姓名及电话。
	 * @Author: shangcuijuan
	 * @Date: 2015年10月15日
	 * @param username 会员名
	 * @return name 姓名，phone 电话
	 */
	public Map<String,String> getSalesOfPurchase(String username);
	/**
	 * 
	 * @Description: 同类采购 显示与此采购单的采购品种相同的，最新通过审核的5条采购信息 
	 * @Author: shangcuijuan
	 * @Date: 2015年10月15日
	 * @param breedId 采购品种Id
	 * @return 
	 */
	public List<BusiPurchaseVo> selectSameBreedList(Long breedId);
	
	/**
	 * 
	 * @Description: 同类采购 显示与此采购单的采购品种相同的，最新通过审核的5条采购信息 
	 * @Author: shangcuijuan
	 * @Date: 2015年10月23日
	 * @param breedName 采购品种名称
	 * @return 
	 */
	public List<BusiPurchaseVo> selectSameBreedNameList(String breedName);
	/**
	 * 
	 * @Description: 统计交易员的成交数（交易员绑定的采购单数，不要求完成交易。）
	 * @Author: shangcuijuan
	 * @Date: 2015年10月15日
	 * @param saleName 交易员名称
	 * @return 
	 */
	public List<BusiPurchase> selectPurOfSalerInfo(String saleName);

	/**
	 * 
	 * @Description: 批量采购保存
	 * @Author: fanyuna
	 * @Date: 2015年10月16日
	 * @param fis
	 * @param user
	 * @param purchaseCode
	 * @return
	 * @throws Exception
	 */
	public boolean batchSavePurchase(final UcUserVo user,final String purchaseCode,final Workbook wb,final Sheet sheet) throws Exception;
	
	/**
	 * @Description: 获取已经过期的采购信息
	 * @Author: 刘漂
	 * @Date: 2015年10月18日
	 * @return
	 */
	public List<BusiPurchaseVo> getExpiredPurchases();
	
	/**
	 * @Description: 过期采购
	 * @Author: 刘漂
	 * @Date: 2015年10月18日
	 * @param purchaseCode
	 * @return
	 */
	public Boolean expirePurchases(final BusiPurchaseVo purchaseBatch) throws Exception;

	
	/**
	 * 
	 * @Description: 获取Excel真实行数 （即采购品种等数据 行数）
	 * @Author: fanyuna
	 * @Date: 2015年10月19日
	 * @param fis
	 * @return
	 * @throws IOException
	 */
	public int getExcelRows(Workbook wb,Sheet sheet) throws Exception;
	
	
	/**
	 * 
	 * @Description: 根据查询条件 查询我的采购列表（用户中心）
	 * @Author: fanyuna
	 * @Date: 2015年10月21日
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<BusiPurchaseVo> selectMyPurchaseListBy(Page<BusiPurchaseVo> page) throws Exception;
	
	/**
	 * 
	 * @Description: 根据采购单主键获取其对象
	 * @Author: fanyuna
	 * @Date: 2015年10月21日
	 * @param purchaseId
	 * @return
	 */
	public BusiPurchase getPurchaseDetailById(String purchaseId) throws Exception;
	
	/**
	 * @Description: 前台采购信息展示页的最近成交列表
	 * @Author: 赵航
	 * @Date: 2015年10月19日
	 * @param maxCount 最大显示条数，设置null表示查所有
	 * @return
	 * @throws Exception
	 */
	public List<BusiPurchaseVo> selectRecentlyPurchaseList(Integer maxCount) throws Exception;
	
	
	/**
	 * @Description: 首页2.0 大货采购数据查询
	 * @Author: Calvin.wh
	 * @Date: 2015-11-11
	 * @return
	 */
	public List<BusiPurchase> getHomePageNewPurchase();
	
	/**
	 * @Description: 首页2.0 最新采购数据 数据默认为30条 最新采购有效数据
	 * @Author: Calvin.wh
	 * @Date: 2015-11-9
	 */
	public List<BusiPurchase> getBigPurchase();
	
}
