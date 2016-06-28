package com.jointown.zy.common.solr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.SpellCheckResponse;
import org.apache.solr.client.solrj.response.SpellCheckResponse.Suggestion;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.util.TimeUtil;

public class SolrManager {
	
	private Logger logger = LoggerFactory.getLogger(getClass());  
	
	public enum SOLR_TYPE{
		/**大于**/
		OPERATE_GREATER_THAN("GT");
		
		private String code;
		
		SOLR_TYPE(String code){
			this.code = code;
		}
		public String getCode(){
			return code;
		}
	}
	
	/**
	 * 
	 * @ClassName: SolrCollection
	 * @Description: solr collection 常量
	 * @Author: 刘漂
	 * @Date: 2015年10月13日
	 * @Version: 1.0
	 */
	public enum SolrCollection{
		listing("listingId"),
		purchase("purchaseId");
		
		private String idField;
		
		private SolrCollection(String idField) {
			this.idField = idField;
		}
		
		public String idField(){
			return idField;
		}
	}
	
	private static Map<SolrCollection, SolrManager> collectionSolrManagers = new HashMap<SolrCollection, SolrManager>();
	
	private SolrServer server;
	private String serverType;
	private int connectTimeout;
	private int clientTimeout;
	private int maxConntionsPerTime;
	private String address;
	private SolrCollection collection;
	
	
	public SolrManager(String serverType,String address,int connectTimeout,int clientTimeout,int maxConntionsPerTime){
		this.serverType = serverType;
		this.address = address;
		this.connectTimeout = connectTimeout;
		this.clientTimeout = clientTimeout;
		this.maxConntionsPerTime = maxConntionsPerTime;
	}
	
	public SolrManager(SolrManager solr){
		this.serverType = solr.getServerType();
		this.address = solr.getAddress();
		this.connectTimeout = solr.getConnectTimeout();
		this.clientTimeout = solr.getClientTimeout();
		this.maxConntionsPerTime = solr.getMaxConntionsPerTime();
	}
	
	/**
	 * 
	 * @Description: 根据collection名称获取相应的collection实例
	 * @Author: 刘漂
	 * @Date: 2015年10月13日
	 * @param collectionName
	 * @return
	 */
	public static SolrManager getInstance(SolrCollection collection){
		return collectionSolrManagers.get(collection);
	}
	
	/**
	 * 
	 * @Description: 判断是否可用
	 * @Author: 刘漂
	 * @Date: 2015年10月13日
	 * @return
	 */
	public boolean isActive(){
		return server!=null;
	}
	
	/**
	 * 
	 * @Description: 判断是否是SolrManger门面
	 * @Author: 刘漂
	 * @Date: 2015年10月13日
	 * @return
	 */
	public boolean isFacade(){
		return getCollection()==null;
	}
	
	
	/**
	 * 销毁方法
	 */
	public void destroy(){
		if(isFacade()){
			for(Map.Entry<SolrCollection, SolrManager> entry:collectionSolrManagers.entrySet()){
				entry.getValue().destroy();
			}
		}else{
			if(!isActive()){
				server.shutdown();
				logger.debug("destroy solr client["+getCollection().name()+"]!");
			}
		}
		server = null;
	}
	
	/**
	 * 初始化方法,需要的项目自行调用
	 */
	public void init(){
		if(isFacade()){
			//初始化并添加所有collection的solrmanager到Map中
			if(collectionSolrManagers.isEmpty()){
				for(SolrCollection collection:SolrCollection.values()){
					SolrManager manager = new SolrManager(this);
					manager.collection = collection;
					manager.init();
					collectionSolrManagers.put(collection, manager);
				}
			}
		}else{
			if(!isActive()){
				if(getServerType().equalsIgnoreCase("http")){
					initHttp();
				}else if(getServerType().equalsIgnoreCase("cloud")){
					initCloud();
				}
				logger.debug("init solr client ["+getCollection().name()+"]!");
			}
		}
	}
	
	/**
	 * 
	 * @Description: 初始化Http类型的solrServer
	 * @Author: 刘漂
	 * @Date: 2015年10月13日
	 */
	private void initHttp(){
		HttpSolrServer httpSolr = new HttpSolrServer(address+"/"+getCollection().name());
		httpSolr.setSoTimeout(getClientTimeout()); // socket read timeout 
		httpSolr.setConnectionTimeout(getConnectTimeout()); 
		httpSolr.setDefaultMaxConnectionsPerHost(getMaxConntionsPerTime()); 
		httpSolr.setMaxTotalConnections(getMaxConntionsPerTime()); 
		httpSolr.setFollowRedirects(false); // defaults to false 
		// allowCompression defaults to false. Server side must support gzip or deflate for this to have any effect. 
		httpSolr.setAllowCompression(true); 
		httpSolr.setMaxRetries(1); // defaults to 0. > 1 not recommended. 
		// sorlr J 目前使用二进制的格式作为默认的格式
		//server.setParser(new jsonreXMLResponseParser()); 
		this.server = httpSolr;
	}
	
	/**
	 * 
	 * @Description: 初始化Cloud类型的solrServer
	 * @Author: 刘漂
	 * @Date: 2015年10月13日
	 */
	private void initCloud(){
		CloudSolrServer cloudSolr = new CloudSolrServer(address);
		cloudSolr.setDefaultCollection(getCollection().name());
		cloudSolr.setIdField(getCollection().idField());
		cloudSolr.setZkConnectTimeout(getConnectTimeout());
		cloudSolr.setZkClientTimeout(getClientTimeout());
		cloudSolr.connect();
		this.server = cloudSolr;
	}
	
	/**
	 * 
	 * @Description: 在操作远程solr之前确保可以执行
	 * @Author: 刘漂
	 * @Date: 2015年10月13日
	 */
	private void guardian(){
		if(!isActive()){
			throw new UnsupportedOperationException("Not ready for use now!");
		}
	}
	
	/**
	 * @Description: 中文分词类型的字段名
	 * @Author: robin.liu
	 * @Date: 2015年7月29日
	 * @param name
	 * @return
	 */
	public String chineseTypeSearchField(String name){
		return name+"_chn";
	}
	
	public <T> boolean saveIndexes(List<T> solrDocuments) { 
		guardian();
		boolean flag = false;
		if(!CollectionUtils.isEmpty(solrDocuments)){
			try {
				long t1 = System.currentTimeMillis();
				List<T> dealList = new ArrayList<T>();
				for(int i=0;i<solrDocuments.size();i++){
					dealList.add(solrDocuments.get(i));
					if((i+1)%50==0){
						server.addBeans(dealList);
						server.commit();
						dealList.clear();
					}
				}
				if(dealList.size()>0){
					server.addBeans(dealList);
					server.commit();
				}
				flag = true;
				long t2 = System.currentTimeMillis();
				logger.info("Save docs successfully, indexed record: "+solrDocuments.size()+", cost time: "+(t2-t1)+" milimiliSeconds!");
			} catch (SolrServerException e) {
				logger.error("Save docs Exception, the exception is: "+e.getMessage());
			} catch (IOException e) {
				logger.error("Save docs Exception, the exception is: "+e.getMessage());
			}
		}
	    return flag;
    }  
	
	public boolean saveIndexes(Collection<SolrInputDocument> solrDocuments) { 
		guardian();
		boolean flag = false;
		if(!CollectionUtils.isEmpty(solrDocuments)){
			try {
				long t1 = System.currentTimeMillis();
		    	UpdateResponse response = server.add(solrDocuments);
				server.commit();
				flag = true;
				long t2 = System.currentTimeMillis();
				logger.info("Save docs successfully, indexed record: "+solrDocuments.size()+", cost time: "+(t2-t1)+" milimiliSeconds!");
			} catch (SolrServerException e) {
				logger.error("Save docs Exception, the exception is: "+e.getMessage());
			} catch (IOException e) {
				logger.error("Save docs Exception, the exception is: "+e.getMessage());
			}
		}
	    return flag;
    } 
	
	public <T> boolean addIndexes(List<T> solrDocuments) { 
		return saveIndexes(solrDocuments);
    }  
	
	public boolean addIndexes(Collection<SolrInputDocument> solrDocuments) { 
		return saveIndexes(solrDocuments);
    }  
	
	public <T> boolean updateIndexes(Collection<SolrInputDocument> solrDocuments){
		return saveIndexes(solrDocuments);
	}
	
	public <T> boolean updateIndexes(List<T> solrDocuments){
		return saveIndexes(solrDocuments);
	}
	
	public boolean deleteIndexes(List<String> ids){
		guardian();
		boolean flag = false;
		if(!CollectionUtils.isEmpty(ids)){
			try {
				UpdateResponse response = server.deleteById(ids);
				server.commit();
				flag = true;
				logger.info("Delete docs successfully, deleted record: "+ids.size()+", cost time: "+""+" miliSeconds!");
			} catch (SolrServerException e) {
				logger.error("Delete docs Exception, the exception is: "+e.getMessage());
			} catch (IOException e) {
				logger.error("Delete docs Exception, the exception is: "+e.getMessage());
			}
		}
		return flag;
	}
	
	public <P> List<String> suggest(SolrSearchParam param,Page<P> page){
		guardian();
		List<String>  result = new ArrayList<String>();
        SolrQuery query = new SolrQuery();  
        this.assembleQueryParams(param, query);
        query.setRequestHandler("/suggest");//请求到suggest中
		query.set("spellcheck.count", page.getPageSize());//返回数量
		query.set("distrib", "false");//分布
		//query.set("spellcheck", "on");
		//query.set("spellcheck.build", "true");
		QueryResponse rsp;
		try {
			rsp = server.query(query);
			SpellCheckResponse re=rsp.getSpellCheckResponse();//获取拼写检查的结果集
			if (re != null) {
			     for(Suggestion s:re.getSuggestions()){
				      for(String spellWord:s.getAlternatives()){//获取所有 的检索词
					     result.add(spellWord);//建议词汇
				      }
			    }
			 }
		} catch (SolrServerException e) {
			logger.error("Search suggestion Exception, the exception is: "+e.getMessage());
		}
		return result;
	}
	
	public <T,P> List<T> search(SolrSearchParam param,Class<T> beanClass,Page<P> page) {
		guardian();
		List<T> result = null;
        SolrQuery query = new SolrQuery();  
        this.assembleQueryParams(param, query);
		//设置分页参数
        query.setStart(page.getOffset())
        	 .setRows(page.getPageSize());
        try {  
        	logger.debug("Solr search url is: "+query.toString());
            QueryResponse response = server.query(query); 
            result = response.getBeans(beanClass);
            page.setTotalRecord((int)response.getResults().getNumFound());
            logger.debug("Search docs successfully, record: "+result.size()+", cost time: "+""+" miliSeconds!");
        } catch (SolrServerException e) {  
        	logger.error("Search docs Exception, the exception is: "+e.getMessage());
        } catch(Exception e) {  
        	logger.error("Search docs Exception, the exception is: "+e.getMessage());
        }  
        return result;
	} 
	
	public SolrManager putParams(Map<String,String[]> queryValues,String type,String field,String...values){
		if(type==null){
			queryValues.put(field, values);
		}else if(type.equalsIgnoreCase("Date")){
			String[] dateValues = new String[values.length];
			for(int i=0;i<values.length;i++){
				if(!values[i].equals("*")){
					dateValues[i] = TimeUtil.getYMDHMSTZ(TimeUtil.parseYMD(values[i]));
				}else{
					dateValues[i] = values[i];
				}
			}
			queryValues.put(field, dateValues);
		}
		return this;
	}
	
	public String all(){
		return "*:*";
	}
	
	public SolrManager begin(StringBuilder oldQuery,String field,String... queryValues){
		Map<String, String[]> values = new HashMap<String, String[]>();
		values.put(field, queryValues);
		return operateQ(oldQuery, values);
	}
	
	public SolrManager and(StringBuilder oldQuery,Map<String,String[]> queryValues){
		return operateQ(oldQuery, queryValues, "AND");
	}
	
	public SolrManager or(StringBuilder oldQuery,Map<String,String[]> queryValues){
		return operateQ(oldQuery, queryValues, "OR");
	}
	
	public SolrManager not(StringBuilder oldQuery,Map<String,String[]> queryValues){
		return operateQ(oldQuery, queryValues, "NOT");
	}
	
	public String sort(String query,String fieldName){
		return query;
	}
	
	/**
	 * @Description: 
	 * @Author: 宋威
	 * @Date: 2015年6月2日
	 * @param oldQuery
	 * @param queryValues
	 * @param type
	 * @return SolrManager
	 * @update By Mr.song 根据AND OR 等多值条件拼接solr查询url；如多仓库，多等级规格，多产品查询。
	 */
	public SolrManager operateQ(StringBuilder oldQuery,Map<String,String[]> queryValues,String... type){
		for(Map.Entry<String, String[]> entry:queryValues.entrySet()){
			if(type.length>0 && oldQuery.length()>0){
				oldQuery.append(" "+type[0]+" (");
			}else{
				oldQuery.append("(");
			}
			if(entry.getValue().length==1){
				oldQuery.append(entry.getKey()).append(":");
				if(!entry.getValue()[0].equals("*")){
					if(entry.getValue()[0].contains("^")){
						/**************多值查询条件拼接，如多个仓库同时搜索出结果 start*****************/
						String value= entry.getValue()[0].substring(1,entry.getValue()[0].length());
						String[] arr =value.split("\\^"); 
						oldQuery.append("(");
						int i=0;
						for(String obj:arr){
							oldQuery.append("\""+obj+"\"");
							if(i<arr.length-1)
								oldQuery.append(",");
							i++;
						}
						oldQuery.append(")");
						/**************多值查询条件拼接，如多个仓库同时搜索出结果 end*****************/
					}else{
						oldQuery.append("\""+entry.getValue()[0]+"\"");
					}
				}else{
					oldQuery.append(entry.getValue()[0]);
				}
			}else if(entry.getValue().length==2){
				oldQuery.append(entry.getKey()).append(":["+entry.getValue()[0]).append(" TO ").append(entry.getValue()[1]).append("]");
			}
			oldQuery.append(")");
		}
		return this;
	}
	
	public SolrManager filter(List<String> filter,Map<String,String[]> filterValues){
		StringBuilder sb = new StringBuilder();
		for(Map.Entry<String, String[]> entry:filterValues.entrySet()){
			if(entry.getValue().length==1){
				sb.append(entry.getKey()).append(":");
				if(!entry.getValue()[0].equals("*")){
					sb.append("\""+entry.getValue()[0]+"\"");
				}else{
					sb.append(entry.getValue()[0]);
				}
			}else if(entry.getValue().length==2){
				sb.append(entry.getKey()).append(":["+entry.getValue()[0]).append(" TO ").append(entry.getValue()[1]).append("]");
			}else if(entry.getValue().length==3){
				String solrOperatetype = entry.getValue()[0];
				if(SOLR_TYPE.OPERATE_GREATER_THAN.getCode().equalsIgnoreCase(solrOperatetype)){
					sb.append(entry.getKey()).append(":{"+entry.getValue()[1]).append(" TO ").append(entry.getValue()[2]).append("]");
				}else{
					sb.append(entry.getKey()).append(":["+entry.getValue()[1]).append(" TO ").append(entry.getValue()[2]).append("]");
				}
			}
			if(sb.length()>0){
				filter.add(sb.toString());
				sb.delete(0, sb.length());
			}
		}
		return this;
	}
	
	/**
	 * 
	 * @Description: 装配solrquery所需要的参数
	 * @Author: robin.liu
	 * @Date: 2015年7月31日
	 * @param param
	 * @param query
	 * @return
	 */
	public SolrManager assembleQueryParams(SolrSearchParam param,SolrQuery query){
        //设置q参数
        query.setQuery(param.getQueryString().toString()); 
        //设置fq参数
        List<String> filter = new ArrayList<String>();
        this.filter(filter, param.getFilterValues());
        query.setFilterQueries(filter.toArray(new String[filter.size()]));
        //设置sort参数
		for(Map.Entry<String, String> entry:param.getSortValues().entrySet()){
			query.addSort(entry.getKey(), entry.getValue().equalsIgnoreCase(ORDER.asc.name())?ORDER.asc:ORDER.desc);
	    }
		return this;
	}
	
	/**
	 * @Description: 根据
	 * @Author: 赵航
	 * @Date: 2015年7月28日
	 * @param queryString 检索条件语句(q)
	 * @param filterQueryString (fq，可以多个)
	 * @param fields 分组字段（可多个）
	 * @return 各分组字段查询后的map集合（key：分组字段；value：分组字段统计）
	 * @throws Exception
	 */
	public Map<String, FacetField> facetFields(String queryString, List<String> filterQueryString, String...fields) throws Exception{
		guardian();
		Map<String, FacetField> map = null;
		if(null == fields || StringUtils.isEmpty(queryString)){
			throw new IllegalArgumentException("Argument[queryString, fields] can not be null");
		}
		
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setFacet(true);//启用facet组件
		solrQuery.setQuery(queryString);
		if(!CollectionUtils.isEmpty(filterQueryString)){
        	for(String fq:filterQueryString){
        		solrQuery.addFilterQuery(fq);
        	}
        }
		solrQuery.setFacetMinCount(1);//分组下某一条目的最小数据量为1
		solrQuery.setRows(0);//分组查询设置为0
		solrQuery.addFacetField(fields);//添加分组字段
		try {
			logger.debug("Solr search url is: " + solrQuery.toString());
			QueryResponse queryResponse = server.query(solrQuery);
			map = new HashMap<String, FacetField>();
			for(String field : fields){
				map.put(field, queryResponse.getFacetField(field));
			}
		} catch (SolrServerException e) {
			logger.error("SolrManager.facetFields error is " + e.getMessage());
		}
		return map;
	}
	
	
	
	
	public SolrServer getServer() {
		return server;
	}

	public String getServerType() {
		return serverType;
	}

	public int getConnectTimeout() {
		return connectTimeout;
	}

	public int getClientTimeout() {
		return clientTimeout;
	}

	public int getMaxConntionsPerTime() {
		return maxConntionsPerTime;
	}

	public String getAddress(){
		return this.address;
	}

	public SolrCollection getCollection() {
		return collection;
	}

}
