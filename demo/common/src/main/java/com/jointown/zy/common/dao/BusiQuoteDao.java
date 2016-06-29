package com.jointown.zy.common.dao;

import java.util.List;

import com.jointown.zy.common.model.BusiQuote;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.vo.BusiQuoteVo;

public interface BusiQuoteDao {
	/**
	 * 分页查询报价记录
	 */
	List<BusiQuoteVo> selectQuoteListByCondition(Page<BusiQuoteVo> page);
	
    
    int deleteByPrimaryKey(String quoteId);
	/**
	 * 插入报价记录
	 */
    int insertQuote(BusiQuote busiQuote);

    int insertSelective(BusiQuote record);

    BusiQuote selectByPrimaryKey(String quoteId);

    int updateByPrimaryKeySelective(BusiQuote record);

    int updateByPrimaryKey(BusiQuote record);
    
    /**
     * @Description: 根据采购信息ID，查询报价信息列表
     * @Author: 赵航
     * @Date: 2015年10月14日
     * @param page
     * @return
     */
    List<BusiQuote> selectQuotePageByPurchaseId(Page<BusiQuote> page);
    
    /**
     * @Description: 根据采购信息ID，更新除指定的报价信息外的报价
     * @Author: 赵航
     * @Date: 2015年10月15日
     * @param record
     * @return
     */
    int updateQuoteByPurchaseIdAndQuoteId(BusiQuote record);
    
    /**
     * 
     * @Description: 根据采购批次号，得到所有的相关的报价
     * @Author: 尚翠娟
     * @Date: 2015年10月21日
     * @param purCode
     * @return
     */
    List<BusiQuote> selectQuoteByPurchaseCode(String purCode);
}