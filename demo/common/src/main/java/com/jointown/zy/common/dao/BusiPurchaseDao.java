package com.jointown.zy.common.dao;

import java.util.List;

import com.jointown.zy.common.model.BusiPurchase;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.vo.BusiPurchaseMobileEmailMsgVo;
//import com.jointown.zy.common.vo.BusiPurchaseBatchVo;
import com.jointown.zy.common.vo.BusiPurchaseVo;

public interface BusiPurchaseDao {

    int deleteByPrimaryKey(String purchaseId);

    int insert(BusiPurchase record);

    int insertSelective(BusiPurchase record);

    BusiPurchase selectByPrimaryKey(String purchaseId);

    int updateByPrimaryKeySelective(BusiPurchase record);

    int updateByPrimaryKey(BusiPurchase record);
    
    /**
     * @Description: 后台-审核采购信息列表
     * @Author: 赵航
     * @Date: 2015年10月13日
     * @param page
     * @return
     */
    List<BusiPurchaseVo> selectPurchaseAuditPage(Page<BusiPurchaseVo> page);
    
    /**
     * @Description: 后台-审核采购信息详情
     * @Author: 赵航
     * @Date: 2015年10月13日
     * @param purchaseId
     * @return
     */
    BusiPurchaseVo selectPurchaseDetail(String purchaseId);
    
    /**
     * @Description: 后台-采购信息管理列表
     * @Author: 赵航
     * @Date: 2015年10月14日
     * @param page 查询条件
     * @return
     */
    List<BusiPurchaseVo> selectPurchaseManagePage(Page<BusiPurchaseVo> page);

    
    List<BusiPurchaseVo> selectSameBreedList(Long breedId);
    
    List<BusiPurchaseVo> selectSameBreedNameList(String breedName);
	
    List<BusiPurchase> selectPurOfSalerInfo(String saleName);

    
    /**
     * @Description: 根据采购批次号查询采购信息
     * @Author: 赵航
     * @Date: 2015年10月16日
     * @param purchaseCode 采购批次号
     * @return
     */
    List<BusiPurchase> selectPurchaseByCode(String purchaseCode);
    
    /**
     * @Description: 发送短信邮件时，需要查询的相关信息
     * @Author: 赵航
     * @Date: 2015年10月16日
     * @param purchaseId 采购ID
     * @return
     */
    BusiPurchaseMobileEmailMsgVo selectPurchaseMobileEmailMsgById(String purchaseId);
    
    /**
     * 
     * @Description: 查询过期的采购信息
     * @Author: 刘漂
     * @Date: 2015年10月18日
     * @return
     */
    List<BusiPurchaseVo> selectExpiredPurchaseInfo();
    
//    /**
//     * 
//     * @Description: 根据采购批次号查询采购批次信息
//     * @Author: 刘漂
//     * @Date: 2015年10月18日
//     * @return
//     */
//    List<BusiPurchaseBatchVo> selectPurchaseBatchByCode(String purchaseCode);
    
    /**
     * 
     * @Description: 通过采购号过期采购
     * @Author: 刘漂
     * @Date: 2015年10月18日
     * @param purchaseId
     * @return
     */
    boolean expirePurchaseByPurchaseId(String purchaseId);
    

    /**
     * @Description: 查询最近成交的采购
     * @Author: 赵航
     * @Date: 2015年10月19日
     * @param maxCount 最大查询条数， 设置null表示查询全部
     * @return
     */
    List<BusiPurchaseVo> selectRecentlyPurchases(Integer maxCount);
    /**
     *
     * @Description: 初次报价，改变采购信息的状态为洽谈中
     * @Author: 尚翠娟
     * @Date: 2015年10月21日
     * @param purchaseId
     * @return
     */
    boolean negotiaPurchaseByPurchaseCode(String purchaseId);
    
    /**
     * @Description: 用户中心-管理采购列表
     * @Author: fanyuna
     * @Date: 2015年10月21日
     * @param page
     * @return
     */
    public List<BusiPurchaseVo> getPurchaseManagePage(Page<BusiPurchaseVo> page);
    
    /**
     * 
     * @Description: 后台大货采购类型数据查询
     * @Author: Calvin.wh
     * @Date: 2015-11-11
     * @return
     */
    public List<BusiPurchase> queryBigPurchase();
    
    /**
     * 
     * @Description: 首页2.0 大货采购数据查询
     * @Author: Calvin.wh
     * @Date: 2015-11-11
     * @return
     */
    public List<BusiPurchase> queryHomePagePurchase();
    
    /**
     * @Description: 首页2.0 最新采购数据
     * @Author: Calvin.wh
     * @Date: 2015-11-11
     * @return
     */
	public List<BusiPurchase> queryHomePageNewPurchase();
	
	/**
	 * 
	 * @Description: 批量插入采购单
	 * @Author: fanyuna
	 * @Date: 2015年11月23日
	 * @param list
	 * @return
	 */
	int batchInsert(List<BusiPurchase> list);

}