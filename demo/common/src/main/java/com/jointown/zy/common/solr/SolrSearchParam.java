/**
 * Copyright © 2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.solr;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: SolrSearchParam
 * @Description: TODO
 * @Author: robin.liu
 * @Date: 2015年7月29日
 * @Version: 1.0
 */
public class SolrSearchParam {
	
	Map<String, String[]> queryValues = new HashMap<String, String[]>();
	Map<String, String[]> filterValues = new HashMap<String, String[]>();
	Map<String, String> sortValues = new HashMap<String, String>();
	
	StringBuilder queryString = new StringBuilder();
	
	public Map<String, String[]> getQueryValues() {
		return queryValues;
	}
	public SolrSearchParam setQueryValues(Map<String, String[]> queryValues) {
		this.queryValues = queryValues;
		return this;
	}
	public Map<String, String[]> getFilterValues() {
		return filterValues;
	}
	public SolrSearchParam setFilterValues(Map<String, String[]> filterValues) {
		this.filterValues = filterValues;
		return this;
	}
	public Map<String, String> getSortValues() {
		return sortValues;
	}
	public SolrSearchParam setSortValues(Map<String, String> sortValues) {
		this.sortValues = sortValues;
		return this;
	}
	public StringBuilder getQueryString() {
		return queryString;
	}
	public SolrSearchParam setQueryString(StringBuilder queryString) {
		this.queryString = queryString;
		return this;
	}
	
}
