/**
 * Copyright © 2014-2015 珍药材版权所有
 */
package com.jointown.zy.common.service;

import java.util.List;
import com.jointown.zy.common.dto.PurchaseSearchDto;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.solr.SolrPurchaseVo;

/**
 * @ClassName: PurchaseSolrSearchService
 * @Description: 采购信息搜索Service
 * @Author: 赵航
 * @Date: 2015年10月15日
 * @Version: 1.0
 */
public interface PurchaseSolrSearchService {
	
	/**
	 * @Description: 前台采购信息搜索
	 * @Author: 赵航
	 * @Date: 2015年10月15日
	 * @param query 搜索条件
	 * @return 采购信息列表
	 * @throws Exception
	 */
	Page<SolrPurchaseVo> searchSolrPurchasePage(PurchaseSearchDto query) throws Exception;
	
	/**
	 * @Description: 前台采购信息搜索-热门品种
	 * @Author: 赵航
	 * @Date: 2015年10月16日
	 * @param maxShowCount 最大显示个数,设置为空时，显示所有
	 * @return 热门品种名称集合
	 * @throws Exception
	 */
	List<String> searchHotPurchaseBreed(Integer maxShowCount) throws Exception;
	
}
