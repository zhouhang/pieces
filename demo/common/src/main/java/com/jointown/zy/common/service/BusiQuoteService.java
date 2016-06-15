package com.jointown.zy.common.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jointown.zy.common.dto.BusiQuoteDto;
import com.jointown.zy.common.model.BusiQuote;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.vo.BusiQuoteVo;

public interface BusiQuoteService {
	/**
	 * 报价列表信息显示
	 * @param page
	 * @return
	 */
	 List<BusiQuoteVo> selectQuoteListByCondition(Page<BusiQuoteVo> page);
	/**
	 * 添加报价
	 * @param busiQuote
	 * @return
	 */
	 BusiQuote quoteReturn(BusiQuoteDto busiQuoteDto,HttpServletRequest request);
	 
	 /**
	     * @Description: 根据采购信息ID，查询报价信息列表
	     * @Author: 赵航
	     * @Date: 2015年10月14日
	     * @param page
	     * @return
	     */
	    List<BusiQuote> selectQuotePageByPurchaseId(Page<BusiQuote> page);
}
