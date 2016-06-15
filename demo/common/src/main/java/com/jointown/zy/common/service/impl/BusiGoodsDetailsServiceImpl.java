package com.jointown.zy.common.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import com.google.gson.Gson;
import com.jointown.zy.common.dao.BossUserDao;
import com.jointown.zy.common.dao.BreedDao;
import com.jointown.zy.common.dao.BusiCollectionDao;
import com.jointown.zy.common.dao.BusiListingDao;
import com.jointown.zy.common.dao.BusiListingDetailDao;
import com.jointown.zy.common.dao.BusiOrderDao;
import com.jointown.zy.common.dao.BusiOrderDetailDao;
import com.jointown.zy.common.dao.BusiOrderLogDao;
import com.jointown.zy.common.dao.BusiOrderSalesmanDao;
import com.jointown.zy.common.dao.UcUserDao;
import com.jointown.zy.common.dto.BusiBuyGoodsDto;
import com.jointown.zy.common.enums.BusiCollectionStateEnum;
import com.jointown.zy.common.enums.BusiListingFlagEnum;
import com.jointown.zy.common.enums.BusiOrderSplitEnum;
import com.jointown.zy.common.enums.BusiOrderStateEnum;
import com.jointown.zy.common.enums.BusiOrderTypeEnum;
import com.jointown.zy.common.enums.BusinessLogEnum;
import com.jointown.zy.common.enums.SolrContentTypeEnum;
import com.jointown.zy.common.enums.SolrOperationTypeEnum;
import com.jointown.zy.common.messageconfig.MessageConfigManage;
import com.jointown.zy.common.model.BossUser;
import com.jointown.zy.common.model.Breed;
import com.jointown.zy.common.model.BusiCollection;
import com.jointown.zy.common.model.BusiListing;
import com.jointown.zy.common.model.BusiListingDetail;
import com.jointown.zy.common.model.BusiOrder;
import com.jointown.zy.common.model.BusiOrderDetail;
import com.jointown.zy.common.model.BusiOrderSalesman;
import com.jointown.zy.common.model.BusiQualityItem;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.model.UcUser;
import com.jointown.zy.common.rabbitmq.RabbitmqProducerManager;
import com.jointown.zy.common.service.BaseService;
import com.jointown.zy.common.service.BusiGoodsDetailsService;
import com.jointown.zy.common.service.OrganizationService;
import com.jointown.zy.common.util.GetMessageContext;
import com.jointown.zy.common.util.GsonFactory;
import com.jointown.zy.common.vo.BusiCollectionVo;
import com.jointown.zy.common.vo.BusiGoodsInfoVo;
import com.jointown.zy.common.vo.BusiGoodsOrderVo;
import com.jointown.zy.common.vo.BusiGoodsRecommenVo;
import com.jointown.zy.common.vo.BusiGoodsSellerVo;

@Service
public class BusiGoodsDetailsServiceImpl extends BaseService implements BusiGoodsDetailsService {
	
	private static Logger logger = LoggerFactory.getLogger(BusiGoodsDetailsServiceImpl.class);
	
	@Autowired
	private BusiListingDao busiListingDao;
	
	@Autowired
	private BusiListingDetailDao busiListingDetailDao;
	
	@Autowired
	private BusiOrderDao busiOrderDao;
	
	@Autowired
	private BusiCollectionDao busiCollectionDao;
	
	@Autowired
	private BusiOrderDetailDao busiOrderDetailDao;
	
	/**
	 * 摘牌操作日志数据dao
	 * @date 2015.3.16
	 */
	@Autowired
	private BusiOrderLogDao busiOrderLogDao;
	
	//add by fanyuna 订单买家、卖家业务员Dao，前台用户Dao，后台用户Dao，组织机构service
	@Autowired
	private BusiOrderSalesmanDao busiOrderSalesmanDao;
	@Autowired
	private UcUserDao ucUserDao;
	@Autowired
	private BossUserDao bossUserDao;
	@Autowired
	private OrganizationService organizationService;
	
	@Autowired
	public ThreadPoolTaskExecutor taskExecutor;	
	
	@Autowired
	public MessageConfigManage messageConfigManage;

	@Autowired
	public BreedDao breedDao;
	


	@Override
	public BusiGoodsSellerVo selectGoodsSellerInfo(String listingid) {
		logger.info("BusiGoodsDetailsServiceImpl.selectGoodsSellerInfo method");
		BusiGoodsSellerVo bsv = busiListingDao.selectGoodsSellerInfo(listingid);
		if(null != bsv){
			int ordersCount = 0;//默认是0
			ordersCount = busiOrderDao.selectOrdersCountByUserId(Long.parseLong(bsv.getSellerId()));
			bsv.setOrderSCount(String.valueOf(ordersCount));
		}
		return bsv;
	}

	@Override
	public BusiGoodsInfoVo selectGoodsInfo(String listingid) {
		logger.info("BusiGoodsDetailsServiceImpl.selectGoodsInfo method");
		BusiGoodsInfoVo vo = busiListingDao.selectGoodsInfo(listingid);
		if(vo != null){
			//根据类型分类质检项信息
			vo.setItemMap(getGroupItem(vo));
			BusiListingDetail detail = busiListingDetailDao.selectByListId(listingid);
			if(detail != null){
				vo.setContent(detail.getContent());
			}
		}
		return vo;
	}
	
	/**
	 * 根据类型分类质检项信息
	 * @param vo
	 * @return
	 */
	public Map<String, List<BusiQualityItem>>  getGroupItem(BusiGoodsInfoVo vo){
		Map<String, List<BusiQualityItem>> map = new LinkedHashMap<String, List<BusiQualityItem>>();
		if(CollectionUtils.isNotEmpty(vo.getBusiQualityItem())){
			List<BusiQualityItem> items = vo.getBusiQualityItem();
			for(int i=0;i<items.size();i++){
				BusiQualityItem bqi = items.get(i);
				String itemType = bqi.getQualityItemType();
				if(map.containsKey(itemType)){
					map.get(itemType).add(bqi);
				}else{
					List<BusiQualityItem> temp = new ArrayList<BusiQualityItem>();
					temp.add(bqi);
					map.put(itemType, temp);
				}
			}
		}
		return map;
	}

	@Override
	public List<BusiGoodsOrderVo> selectGoodsOrderList(Page<BusiGoodsOrderVo> page) {
		logger.info("BusiGoodsDetailsServiceImpl.selectGoodsOrderList method");
		return busiOrderDao.selectGoodsOrderList(page);
	}

	@Override
	public List<BusiGoodsRecommenVo> selectGoodsRecommenList(Integer count) {
		logger.info("BusiGoodsDetailsServiceImpl.selectGoodsRecommenList method");
		return busiListingDao.selectGoodsRecommenList(count);
	}

	@Override
	public String buyGoodsOrder(final BusiBuyGoodsDto goodsOrder,HttpServletRequest request) {
		logger.info("BusiGoodsDetailsServiceImpl.buyGoodsOrder method");
		String rs = getTransactionTemplate().execute(new TransactionCallback<String>() {

			@Override
			public String doInTransaction(TransactionStatus status) {
				try {
					//挂牌是否有效
					BusiListing listing = busiListingDao.selectSingleListing(goodsOrder.getListingId());
					if(!BusiListingFlagEnum.LISTING.getCode().equals(String.valueOf(listing.getListingflag()))){
						return "挂牌商品已无效！";
					}
					
					//判定订购数量是否超过可购买数量
					/*BusiListingSurplus surplus = busiListingDao.selectListingSurplus(goodsOrder.getListingId());
					if(surplus!=null && surplus.getSurplus().compareTo(new BigDecimal(goodsOrder.getAmount())) < 0){
						return "购买数量超过了可购买数量！";
					}*/
					//此视图中没数据，换种方式 update by fanyuna
					BusiGoodsInfoVo busiGoodsInfo = busiListingDao.selectGoodsInfo(goodsOrder.getListingId());
					
					//update by Mr.song 2015.2.6 14:33
					if(Double.parseDouble(goodsOrder.getAmount()) > busiGoodsInfo.getMaxsalesAmount().doubleValue()){
						return "购买数量超过了可购买数量！";
					}
					
					//如果没有超过，就插入购买记录
					BusiOrder order = new BusiOrder();
					order.setUserid(goodsOrder.getListingerId());
					order.setBuyer(goodsOrder.getBuyerId());
					order.setWlid(goodsOrder.getWlid());
					order.setListingid(goodsOrder.getListingId());
					order.setUnitprice(new BigDecimal(goodsOrder.getPrice()));
					order.setAmount(new BigDecimal(goodsOrder.getAmount()));
					order.setTotalprice(new BigDecimal(goodsOrder.getTotalPrice()));
					order.setDiscountprice(new BigDecimal(goodsOrder.getTotalPrice()));
					//add by guoyb 2015.4.7 16:55 添加order
					order.setDeposit(new BigDecimal(goodsOrder.getDeposit()));
					//add by fanyuna 保证金
					//order.setDeposit(deposit); 
					
//					order.setOrderstate(Integer.valueOf(BusiOrderStateEnum.CHATING.getCode()));洽谈中
					order.setOrderstate(Integer.parseInt(BusiOrderStateEnum.PlACED_ORDER.getCode()));
					Date date = new Date();
					order.setCreatetime(date);
					order.setUpdatetime(date);
					String isneedBill = goodsOrder.getIsneedBill();
					if(isneedBill!=null&&!isneedBill.isEmpty()){
						order.setHasbill(Short.decode(goodsOrder.getIsneedBill()));
					}else{
						order.setHasbill((short)0);
					}
					int count = busiOrderDao.insertOrder(order);
					if(count > 0){
						//添加修改挂牌信息日志记录 add 2015.3.16 Mr.song
						busiOrderLogDao.insertBusiOrderLog(order, "买家成功下单", order.getBuyer(), BusinessLogEnum.ORDER_CREATED.getCode());
					}else{
						return "下单失败！";
					}
					
					//判断挂牌数量是否全部摘完 （包括洽谈中、交易完成的），即查可购买数量是否为0，若为0，则更改挂牌表BUSI_LISTING.LISTINGFLAG为3，即已卖完
					//挂牌数量全部售完，且其所有订单状态为已完成，才更新挂牌的状态为已完成。因此这个逻辑在后台对订单状态修改为已完成时再根据情况更改订单状态
					/*HashMap<String, Object> map = new HashMap<String,Object>();
					map.put("userId", goodsOrder.getListingerId());
					map.put("listingid", goodsOrder.getListingId());
					BusiListingSurplus surplus1 = busiListingDao.selectListingSurplus(map);
					if(surplus1.getSurplus().intValue()==0){
						BusiListing busiList = new BusiListing();
							busiList.setListingid(goodsOrder.getListingId());
							busiList.setListingflag(Short.valueOf(BusiListingFlagEnum.LISTING_SOLDOUT.getCode()));
						busiListingDao.updateByIdSelective(busiList);
					}*/
				} catch (Exception e) {
					status.setRollbackOnly();
					return "下单失败！";
				}
				return null;
			}
		});
		
		if(rs != null){
			return rs;
		}
		
		RabbitmqProducerManager.getInstance().pushMsgForSolr(SolrOperationTypeEnum.UPDATE, SolrContentTypeEnum.LISTING, goodsOrder.getListingId());
		return "y";
	}
	
	@Override
	public BusiOrder buyGoodsOrderReturnOrder(final BusiBuyGoodsDto goodsOrder,HttpServletRequest request) {
		logger.info("BusiGoodsDetailsServiceImpl.buyGoodsOrderReturnOrder method");
		BusiOrder busiOrder = getTransactionTemplate().execute(new TransactionCallback<BusiOrder>() {

			@Override
			public BusiOrder doInTransaction(TransactionStatus status) {
				BusiOrder order= null;
				try {
					//挂牌是否有效
					BusiListing listing = busiListingDao.selectSingleListing(goodsOrder.getListingId());
					if(!BusiListingFlagEnum.LISTING.getCode().equals(String.valueOf(listing.getListingflag()))){
						return null;
					}
					
					//判定订购数量是否超过可购买数量
					/*BusiListingSurplus surplus = busiListingDao.selectListingSurplus(goodsOrder.getListingId());
					if(surplus!=null && surplus.getSurplus().compareTo(new BigDecimal(goodsOrder.getAmount())) < 0){
						return "购买数量超过了可购买数量！";
					}*/
					//此视图中没数据，换种方式 update by fanyuna
					BusiGoodsInfoVo busiGoodsInfo = busiListingDao.selectGoodsInfo(goodsOrder.getListingId());
					
					//update by Mr.song 2015.2.6 14:33
					if(Double.parseDouble(goodsOrder.getAmount()) > busiGoodsInfo.getSurpluses().doubleValue()){
						return null;
					}
					
					//判断保证金金额必须大于0
					if (goodsOrder.getDeposit()==null&&"".equals(goodsOrder.getDeposit())) {
						return null;
					}
					if(new BigDecimal(goodsOrder.getDeposit()).compareTo(new BigDecimal("0")) <= 0){
						return null;
					}
					
					//如果没有超过，就插入购买记录
					order = new BusiOrder();
					order.setUserid(goodsOrder.getListingerId());
					order.setBuyer(goodsOrder.getBuyerId());
					order.setWlid(goodsOrder.getWlid());
					order.setListingid(goodsOrder.getListingId());
					order.setUnitprice(new BigDecimal(goodsOrder.getPrice()));
					order.setAmount(new BigDecimal(goodsOrder.getAmount()));
					order.setTotalprice(new BigDecimal(goodsOrder.getTotalPrice()));
					order.setDiscountprice(new BigDecimal(goodsOrder.getTotalPrice()));
					//add by guoyb 2015.4.7 16:55 添加order
					order.setDeposit(new BigDecimal(goodsOrder.getDeposit()));
					
//					order.setOrderstate(Integer.valueOf(BusiOrderStateEnum.CHATING.getCode()));洽谈中
					order.setOrderstate(Integer.parseInt(BusiOrderStateEnum.PlACED_ORDER.getCode()));
					//add by fanyuna 2015.09.11 订单类型此时为普通订单,默认未分割 
					order.setOrderType(Short.valueOf(BusiOrderTypeEnum.ORDINARY_ORDER.getCode()));
					order.setSplitFlag(Short.valueOf(BusiOrderSplitEnum.ORDER_NOT_SPLIT.getCode()));
					Date date = new Date();
					order.setCreatetime(date);
					order.setUpdatetime(date);
					String isneedBill = goodsOrder.getIsneedBill();
					if(isneedBill!=null&&!isneedBill.isEmpty()){
						order.setHasbill(Short.decode(goodsOrder.getIsneedBill()));
					}else{
						order.setHasbill((short)0);
					}
					int count = busiOrderDao.insertOrder(order);
					if(count > 0){
						//添加修改挂牌信息日志记录 add zhaohang
						busiOrderLogDao.insertBusiOrderLog(order, "买家成功下单", order.getBuyer(), BusinessLogEnum.ORDER_CREATED.getCode());
						// 添加挂牌快照
						BusiOrderDetail busiOrderDetail = new BusiOrderDetail();
						busiOrderDetail.setOrderId(order.getOrderid());
						busiOrderDetail.setState("0");
						Gson gson = GsonFactory.createGson("yyyy-MM-dd HH:mm:ss");
						String listingSnapshot = gson.toJson(listing);
						busiOrderDetail.setCreateTime(date);
						busiOrderDetail.setUpdateTime(date);
						busiOrderDetail.setListingSnapshot(listingSnapshot);
						busiOrderDetailDao.insertOrderDetail(busiOrderDetail);
						
						//add by fanyuna 2015.07.28 添加业务员相关信息  start
						UcUser sellers = ucUserDao.findUcUserById(new Integer(goodsOrder.getListingerId().intValue()));//卖家信息
						UcUser buyer = ucUserDao.findUcUserById(new Integer(goodsOrder.getBuyerId().intValue())); //买家信息
						BossUser sellerInfo = (sellers != null && sellers.getSalesmanId()!=null)?bossUserDao.getBossUserByUserId(sellers.getSalesmanId()):null;//卖家关联的业务员信息
						BossUser buyerInfo = (buyer != null && buyer.getSalesmanId() !=null)?bossUserDao.getBossUserByUserId(buyer.getSalesmanId()):null;//买家关联的业务员信息
						//当订单关联的买家或卖家业务员不为空时往订单业务员表插入记录
						if(sellerInfo !=null || buyerInfo != null){
						BusiOrderSalesman salesMan = new BusiOrderSalesman();
							salesMan.setOrderid(order.getOrderid());
							salesMan.setBuyerSalesmanid(buyerInfo!=null?new Long(buyerInfo.getUserId().longValue()):null);
							salesMan.setBuyerOrgid((buyerInfo!=null && buyerInfo.getOrgId() !=null)?new Long(buyerInfo.getOrgId().longValue()):null);
							salesMan.setBuyerOrgs((buyerInfo!=null && buyerInfo.getOrgId()!=null)?organizationService.getOrgParentName(buyerInfo.getOrgId()):null);
							salesMan.setSellerSalesmanid(sellerInfo!=null?new Long(sellerInfo.getUserId().longValue()):null);
							salesMan.setSellerOrgid((sellerInfo!=null&&sellerInfo.getOrgId()!=null)?new Long(sellerInfo.getOrgId().longValue()):null);
							salesMan.setSellerOrgs((sellerInfo!=null && sellerInfo.getOrgId()!=null)?organizationService.getOrgParentName(sellerInfo.getOrgId()):null);
							busiOrderSalesmanDao.insert(salesMan);
						}
						//add by fanyuna 2015.07.28 添加业务员相关信息  end 
						/*------added by biran 20151020 下单时为卖家，买家业务员发送短信-----------*/
						if(sellers!=null && sellerInfo !=null){
							if(sellerInfo.getMobile()!=null && !sellerInfo.getMobile().equals("")){//业务员有电话
								Breed breed=breedDao.selectByPrimaryKey(Long.valueOf(busiGoodsInfo.getBreedId()));//品种信息
								String realName=ucUserDao.getCertifyNameByUserId(sellers.getUserId());//卖家认证名称
								String buyerSalesManName="";//卖家业务员名称
								if(buyer!=null && buyerInfo!=null ){
									buyerSalesManName= buyerInfo.getUserName();
								}
								taskExecutor.execute(messageConfigManage.getMessageChannelTask(sellerInfo.getMobile(),
								GetMessageContext.getCreateOrderMsg4SellerSalesMan(order.getOrderid(),realName+"("+sellers.getUserName()+")",breed.getBreedName(),buyerSalesManName),
										"创建订单[" + order.getOrderid() + "]给卖家业务员发送短信通知出错，错误信息是："));
							}
							
						}
						
						if(buyer!=null && buyerInfo !=null){
							if(buyerInfo.getMobile()!=null && !buyerInfo.getMobile().equals("")){//业务员有电话
								Breed breed=breedDao.selectByPrimaryKey(Long.valueOf(busiGoodsInfo.getBreedId()));//品种信息
								String realName=ucUserDao.getCertifyNameByUserId(buyer.getUserId());//卖家认证名称
								String sellerSalesManName="";//卖家业务员名称
								if(sellers!=null && sellerInfo!=null ){
									sellerSalesManName= sellerInfo.getUserName();
								}
								taskExecutor.execute(messageConfigManage.getMessageChannelTask(buyerInfo.getMobile(),
								GetMessageContext.getCreateOrderMsg4BuyerSalesMan(order.getOrderid(),realName+"("+buyer.getUserName()+")",breed.getBreedName(),sellerSalesManName),
										"创建订单[" + order.getOrderid() + "]给卖家业务员发送短信通知出错，错误信息是："));
							}
							
						}
						/*------added by biran end--------*/
					}else{
						return null;
					}
					
					//判断挂牌数量是否全部摘完 （包括洽谈中、交易完成的），即查可购买数量是否为0，若为0，则更改挂牌表BUSI_LISTING.LISTINGFLAG为3，即已卖完
					//挂牌数量全部售完，且其所有订单状态为已完成，才更新挂牌的状态为已完成。因此这个逻辑在后台对订单状态修改为已完成时再根据情况更改订单状态
					/*HashMap<String, Object> map = new HashMap<String,Object>();
					map.put("userId", goodsOrder.getListingerId());
					map.put("listingid", goodsOrder.getListingId());
					BusiListingSurplus surplus1 = busiListingDao.selectListingSurplus(map);
					if(surplus1.getSurplus().intValue()==0){
						BusiListing busiList = new BusiListing();
							busiList.setListingid(goodsOrder.getListingId());
							busiList.setListingflag(Short.valueOf(BusiListingFlagEnum.LISTING_SOLDOUT.getCode()));
						busiListingDao.updateByIdSelective(busiList);
					}*/
				} catch (Exception e) {
					logger.error("BusiGoodsDetailsServiceImpl.buyGoodsOrderReturnOrder error is "+e.toString());
					status.setRollbackOnly();
					return null;
				}
				return order;
			}
		});
		
		if(busiOrder != null){
			//交易二期中，下单后挂牌不发生变化，这里不需要向solr中打消息
			//RabbitmqProducerManager.getInstance().pushMsgForSolr(SolrOperationTypeEnum.UPDATE, SolrContentTypeEnum.LISTING, goodsOrder.getListingId());
			RabbitmqProducerManager.getInstance().pushMsgForSolr(SolrOperationTypeEnum.UPDATE, SolrContentTypeEnum.LISTING, goodsOrder.getListingId());
		}
		return busiOrder;
	}

	@Override
	public String saveGoodsCollection(BusiBuyGoodsDto goodsOrder) {
		logger.info("BusiGoodsDetailsServiceImpl.saveGoodsCollection method");
		//判断是否已存在收藏记录
		BusiCollection bcn = busiCollectionDao.selectCollention(goodsOrder.getBuyerId(), goodsOrder.getListingId());
		if(bcn != null){
			//存在且已被收藏
			if(BusiCollectionStateEnum.COLLECTION.getCode().equals(String.valueOf(bcn.getCstate()))){
				return "该商品已被收藏";
			} else {
				//存在但已取消收藏，就更新记录为已收藏
				int count = busiCollectionDao.updateCollention(goodsOrder.getBuyerId(), goodsOrder.getListingId(),
						BusiCollectionStateEnum.COLLECTION.getCode());
				if(count <= 0){
					return "收藏失败！";
				}
			}
		} else {
			//不存在就插入记录
			BusiCollection collection = new BusiCollection();
			collection.setUserid(goodsOrder.getBuyerId());
			collection.setWlid(goodsOrder.getWlid());
			if (goodsOrder.getBreedcode() != null) {
				collection.setBreedcode(goodsOrder.getBreedcode());
			}
			collection.setListingid(goodsOrder.getListingId());
			collection.setCstate(Short.valueOf(BusiCollectionStateEnum.COLLECTION.getCode()));
			Date date = new Date();
			collection.setCreatetime(date);
			collection.setUpdatetime(date);
			int count = busiCollectionDao.insertBusiCollention(collection);
			if(count <= 0){
				return "收藏失败！";
			}
		}
		
		return "y";
	}

	@Override
	public List<BusiCollectionVo> selectCollentionsByUserId(Page<Map<String, Object>> page) {
		logger.info("BusiGoodsDetailsServiceImpl.selectCollentionsByUserId method");
		return busiCollectionDao.selectCollentionsByUserId(page);
	}
	
	@Override
	public int cancelCollect(Long cindex){
		BusiCollection collect = new BusiCollection();
			collect.setCindex(cindex);
			collect.setUpdatetime(new Date());
			collect.setCstate(Short.valueOf(BusiCollectionStateEnum.COLLECTION_CANCEL.getCode()));
		return busiCollectionDao.updateCollectionBy(collect);
	}

	@Override
	public List<HashMap<String, String>> selectCollectionBread(Long userid) {
		logger.info("BusiGoodsDetailsServiceImpl.selectCollectionBread method");
		return busiCollectionDao.selectCollectionBread(userid);
	}
	
	/**
	 * 查询热门药材 5条记录
	 * 查交易数量最多的前五个，如果没有一个交易的商品就按挂牌时间倒序；如果交易的商品不够五个，就要查出所有的交易商品再查剩余个数按挂牌时间倒序的商品
	 * @return
	 */
	@Override
	public List<HashMap<String, Object>> selectHotBusiListing() {
		logger.info("BusiGoodsDetailsServiceImpl.selectHotBusiListing method");
		List<HashMap<String,Object>> listingInfoList = new ArrayList<HashMap<String,Object>>();
		List<HashMap<String,Object>> listingSalesList = busiCollectionDao.getListingSaleCountBy();
		List<String> listingIdList = new ArrayList<String>();
		//如果正在进行中的挂牌的有交易的，记录数大于5，则查询前五个的挂牌信息
		if(listingSalesList!=null && listingSalesList.size()>=5){
			for(HashMap<String,Object> map:listingSalesList){
				if(map.get("LISTINGID")!=null){
					listingIdList.add(map.get("LISTINGID")+"");
				}
			}
		}
		else if(listingSalesList.size()>0 && listingSalesList.size()<5){
			//有交易的 但不足5条
			for(HashMap<String,Object> map:listingSalesList){
				if(map.get("LISTINGID")!=null){
					listingIdList.add(map.get("LISTINGID")+"");
				}
			}
			HashMap<String,Object> idMap = new HashMap<String,Object>();
				idMap.put("listingIds", listingIdList);
				idMap.put("num", 5-listingSalesList.size());
			for(HashMap<String,Object> map:busiCollectionDao.getListingSaleIds(idMap)){
				if(map.get("LISTINGID")!=null){
					listingIdList.add(map.get("LISTINGID")+"");
				}
			}
		}
		else if(listingSalesList.size()==0){
			HashMap<String,Object> idMap = new HashMap<String,Object>();
			 idMap.put("num", 5);
		 for(HashMap<String,Object> map:busiCollectionDao.getListingSaleIds(idMap)){
			if(map.get("LISTINGID")!=null){
				listingIdList.add(map.get("LISTINGID")+"");
			}
		   }
		}
			listingInfoList = busiCollectionDao.getListingSaleInfo(listingIdList);
		
		return listingInfoList;
	}

	@Override
	public Map<String, Object> getSellerInfo(String wlid) {
		return busiListingDetailDao.getSellerInfo(wlid);
	}
}
