package com.jointown.zy.common.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import com.jointown.zy.common.dao.BossUserDao;
import com.jointown.zy.common.dao.BreedDao;
import com.jointown.zy.common.dao.BusiListingDao;
import com.jointown.zy.common.dao.BusiListingDetailDao;
import com.jointown.zy.common.dao.BusiListingLogDao;
import com.jointown.zy.common.dao.BusiListingSalesmanDao;
import com.jointown.zy.common.dao.BusiOrderDao;
import com.jointown.zy.common.dao.BusiOrderLogDao;
import com.jointown.zy.common.dao.BusiWhlistDao;
import com.jointown.zy.common.dao.BusiWhlistLogDao;
import com.jointown.zy.common.dao.UcUserDao;
import com.jointown.zy.common.dto.BusiListingDto;
import com.jointown.zy.common.enums.BusiListingFlagEnum;
import com.jointown.zy.common.enums.BusiOrderStateEnum;
import com.jointown.zy.common.enums.BusinessLogEnum;
import com.jointown.zy.common.enums.SolrContentTypeEnum;
import com.jointown.zy.common.enums.SolrOperationTypeEnum;
import com.jointown.zy.common.messageconfig.MessageConfigManage;
import com.jointown.zy.common.model.BossUser;
import com.jointown.zy.common.model.Breed;
import com.jointown.zy.common.model.BusiListing;
import com.jointown.zy.common.model.BusiListingDetail;
import com.jointown.zy.common.model.BusiListingSalesman;
import com.jointown.zy.common.model.BusiOrder;
import com.jointown.zy.common.model.BusiWhlist;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.model.UcUser;
import com.jointown.zy.common.rabbitmq.RabbitmqProducerManager;
import com.jointown.zy.common.service.BaseService;
import com.jointown.zy.common.service.BusiListingService;
import com.jointown.zy.common.service.BusiWhlistService;
import com.jointown.zy.common.service.OrganizationService;
import com.jointown.zy.common.service.UcUserService;
import com.jointown.zy.common.util.GetBaseInfo;
import com.jointown.zy.common.util.GetMessageContext;
import com.jointown.zy.common.vo.BusiListingDetailVo;
import com.jointown.zy.common.vo.BusiListingVo;
import com.jointown.zy.common.vo.RangeVo;

/**
 * 仓单挂牌所有业务ServiceImpl
 * @author Mr.songwei
 * 2014-12-27
 */
@Service
public class BusiListingServiceImpl extends BaseService implements BusiListingService {

	@Autowired
	private BusiListingDao busiListingDao;
	@Autowired
	private BusiListingDetailDao busiListingDetailDao;
	@Autowired
	private BusiOrderDao busiOrderDao;
	@Autowired
	private BusiWhlistDao busiWhlistDao;
	/**
	 * 订单操作日志数据dao
	 */
	@Autowired
	private BusiOrderLogDao busiOrderLogDao;
	/**
	 * 挂牌操作日志数据dao
	 * @date 2015.3.16
	 */
	@Autowired
	private BusiListingLogDao busiListingLogDao;
	/**
	 * 仓单操作日志数据dao
	 * @date 2015.3.16
	 */
	@Autowired
	private BusiWhlistLogDao busiWhlistLogDao;
	
	@Autowired
	private UcUserService ucUserService;
	
	@Autowired
	private BusiWhlistService busiWhlistService;
	
	/*added by biran 20150907 插入挂牌业务员信息*/
	@Autowired
	private UcUserDao ucUserDao;
	
	@Autowired
	private BossUserDao bossUserDao;
	
	@Autowired
	private OrganizationService organizationService;
	
	@Autowired
	private BusiListingSalesmanDao busiListingSalesmanDao;
	/*added by biran end*/
	
	@Autowired
	public ThreadPoolTaskExecutor taskExecutor;	
	@Autowired
	public MessageConfigManage messageConfigManage;
	
	@Autowired
	public BreedDao breedDao;
	
	@Override
	public List<BusiListingVo> findListingsByNotExaminels(Page<BusiListingVo> page){
		return busiListingDao.selectListingsByNotExaminels(page);
	}
		
	/**
	 * 
	 * @Description: 仓单相关操作
	 * @Author: robin.liu
	 * @Date: 2015年8月26日
	 * @param listing
	 * @throws Exception
	 */
	private void updateWhlist(BusiWhlist whlist,BusiListing listing,BusinessLogEnum state) throws Exception{
		//更新仓单
		busiWhlistDao.updateBusiWhlistById(listing.getWhListEntity());
		//添加仓单日志记录
		busiWhlistService.addBusiWhlistLog(whlist, listing.getUserid(), state
				, listing.getListingid(),new RangeVo<Double>(whlist.getWlSurplus(), listing.getWhListEntity().getWlSurplus()));
	}
	
	@Override
	public void addBusiListing(BusiListingDto busiListingDto) throws Exception {
		BusiWhlist busiWhlist = busiWhlistDao.selectWhlistByWlId(busiListingDto.getBusiListing().getWlid());
		BusiWhlist oldBusiWhlist = busiWhlist.clone();
		//新增挂牌
		BusiListing busiListing = busiListingDto.getBusiListing()
										.setWhListEntity(busiWhlist)
										.add();
		busiListingDao.insertSelective(busiListing);
		/*-------added by biran 20150907 添加挂牌业务信息-------*/
		//根据会员信息拿到对应业务员
		UcUser sellers = ucUserDao.findUcUserById(busiListing.getUserid().intValue());//卖家信息
		if(sellers!=null && sellers.getSalesmanId()!=null  && !sellers.getSalesmanId().equals("")){//有对应业务员时
			BossUser sellerInfo = bossUserDao.getBossUserByUserId(sellers.getSalesmanId());//卖家关联的业务员信息
			BusiListingSalesman salesman=new BusiListingSalesman();
			salesman.setListingid(busiListing.getListingid());
			salesman.setSalesmanId(sellerInfo!=null?sellerInfo.getUserId():null);
			salesman.setOrgId((sellerInfo!=null&&sellerInfo.getOrgId()!=null)?sellerInfo.getOrgId():null);
			salesman.setOrgs((sellerInfo!=null && sellerInfo.getOrgId()!=null)?organizationService.getOrgParentName(sellerInfo.getOrgId()):null);
			busiListingSalesmanDao.insertSalesMan(salesman);
			//added by biran 20151019 给业务员发送通知短信,短信参数：挂牌ID,会员名称（会员用户名），品种
			if(sellerInfo.getMobile()!=null && !sellerInfo.getMobile().equals("")){
				Breed breed=breedDao.selectByPrimaryKey(busiListing.getBreedid());//品种信息
				String realName=ucUserDao.getCertifyNameByUserId(busiListing.getUserid());//认证名称
				taskExecutor.execute(messageConfigManage.getMessageChannelTask(sellerInfo.getMobile(),
					GetMessageContext.getAddListingMsg4SalesMan(busiListing.getListingid(),realName+"("+sellers.getUserName()+")",breed.getBreedName()),"新增挂牌[" + busiListing.getListingid() + "]发送短信通知出错，错误信息是："));
				
			}
			//added by biran end
			
		}
		/*--------added by biran  end--------------*/
		//添加挂牌日志记录
		busiListingLogDao.insertBusiListingLog(busiListing, BusinessLogEnum.LISTING.getCodeName(), busiListing.getUserid(), BusinessLogEnum.LISTING.getCode(),false);
		//添加挂牌详情
		busiListingDetailDao.insertSelective(new BusiListingDetail().build(busiListing.getListingid(), busiListingDto.getContent()));
		//更新仓单-减少仓单可挂数量
		updateWhlist(oldBusiWhlist,busiListing,BusinessLogEnum.WHLIST_UPDATE_LISTING_ADD);
		/*-------给卖家(客户)发短信----------*/
		String userMobile = sellers.getMobile();
		taskExecutor.execute(messageConfigManage.getMessageChannelTask(userMobile,
				GetMessageContext.getAddListingMsg(busiListing.getListingid()),"新增挂牌[" + busiListing.getListingid() + "]发送短信通知出错，错误信息是："));
	}

	@Override
	public void updateBusiListing(BusiListingDto busiListingDto) throws Exception {
		BusiWhlist busiWhlist = busiWhlistDao.selectWhlistByWlId(busiListingDto.getBusiListing().getWlid());
		BusiWhlist oldBusiWhlist = busiWhlist.clone();
		Short oldStatus = busiListingDto.getBusiListing().getListingflag();
		//更新挂牌
		BusiListing busiListing = busiListingDto.getBusiListing()
				.setWhListEntity(busiWhlist)
				.update();
		busiListingDao.updateByIdSelective(busiListing);
		//添加挂牌日志记录
		busiListingLogDao.insertBusiListingLog(busiListing, BusinessLogEnum.LISTINGUPDATE.getCodeName(), busiListing.getUserid(), BusinessLogEnum.LISTINGUPDATE.getCode(),false);
		//修改挂牌详情
		HashMap<String,Object> busiListingDetailMap = new HashMap<String,Object>();
		busiListingDetailMap.put("listingid", busiListing.getListingid());
		busiListingDetailMap.put("content", busiListingDto.getContent());
		busiListingDetailMap.put("updatetime", new Date());
		busiListingDetailDao.updateByListId(busiListingDetailMap);
		//验证挂牌状态，审核失败 （更新仓单）,去除原来挂牌下架的逻辑
		if(oldStatus==Short.parseShort(BusiListingFlagEnum.AUDIT_FAILURE.getCode())){
			//更新仓单-减少仓单可挂数量
			if(busiListing.surplusBiggerThan()){
				updateWhlist(oldBusiWhlist,busiListing,BusinessLogEnum.WHLIST_UPDATE_LISTING_ADD);
			}
		}
	}
	
	@Override
	public List<BusiListingVo> findListingsByCondition(Page<BusiListingVo> page) {
		return busiListingDao.selectListingsByCondition(page);
	}

	@Override
	public void updateListingFlagDisabled(BusiListing busiListing) throws Exception {
		//验证挂牌状态-挂牌中
		short listingFlag = busiListing.getListingflag();
		short listingFlagOk = Short.parseShort(BusiListingFlagEnum.LISTING.getCode());
		if(listingFlag != listingFlagOk){
			throw new Exception("挂牌状态错误："+BusiListingFlagEnum.LISTING.getCodeName()+"的挂牌才能下架！");
		}
		//更新挂牌状态-下架，可摘数变为0
		BusiWhlist busiWhlist = busiWhlistDao.selectWhlistByWlId(busiListing.getWlid());
		BusiWhlist oldBusiWhlist = busiWhlist.clone();
		busiListing.setWhListEntity(busiWhlist).cancel();
		busiListingDao.updateListingState(busiListing.getListingid());
		//添加挂牌日志记录
		Long userId = busiListing.getUserid();
		busiListingLogDao.insertBusiListingLog(busiListing, BusinessLogEnum.STOPLISTING.getCodeName(), userId, BusinessLogEnum.STOPLISTING.getCode());
		//更新仓单-增加仓单可挂数量
		updateWhlist(oldBusiWhlist,busiListing, BusinessLogEnum.WHLIST_UPDATE_LISTING_CANCEL);
		//关闭已下单的订单
		List<BusiOrder> createdOrders = busiOrderDao.selectBusiOrdersByAttributes(
												new BusiOrder().setListingid(busiListing.getListingid())
															   .setOrderstate(Integer.parseInt(BusiOrderStateEnum.PlACED_ORDER.getCode())));
		for (BusiOrder order : createdOrders) {
			BusiOrder busiOrderClosed = new BusiOrder(order.getOrderid()).closeOrder();
			busiOrderDao.updateByIdSelective(busiOrderClosed);
			busiOrderLogDao.insertBusiOrderLog(order, BusinessLogEnum.ORDER_COLOSE.getCodeName(), userId, BusinessLogEnum.ORDER_COLOSE.getCode());
		}
	}

	@Override
	public BusiListingDetailVo findSingleListingDetail(String listingid) {
		return busiListingDetailDao.selectSingleListingDetail(listingid);
	}

	@Override
	public BusiListing findByPrimaryKey(String listingid) {
		return busiListingDao.selectSingleListing(listingid);
	}
	
	@Override
	public int updateListingDetailByListId(HashMap<String, Object> map) {
		return busiListingDetailDao.updateByListId(map);
	}

	@Override
	public int updateListingRecommend(BusiListing record) {
		return busiListingDao.updateListingRecommend(record);
	}

	@Override
	public void changeListingFlag(final BusiListing record) throws Exception {
		final BusiListing busiListing = busiListingDao.selectSingleListing(record.getListingid());
		final String listingflag = String.valueOf(record.getListingflag());
		Exception exception = getTransactionTemplate().execute(new TransactionCallback<Exception>() {
			@Override
			public Exception doInTransaction(TransactionStatus status) {
				try {
					//复制老的挂牌数据
					BusiListing oldListing = new BusiListing();
					BeanUtils.copyProperties(busiListing, oldListing);
					String remarks="";
					Short type = null;
					if(BusiListingFlagEnum.AUDIT_FAILURE.getCode().equals(listingflag)){
						BusiWhlist busiWhlist = busiWhlistDao.selectWhlistByWlId(busiListing.getWlid());
						BusiWhlist oldBusiWhlist = busiWhlist.clone();
						busiListing.setWhListEntity(busiWhlist).auditFail();
						busiListingDao.updateListingFlag(record);
						//更新仓单-增加仓单可挂数量
						updateWhlist(oldBusiWhlist,busiListing, BusinessLogEnum.WHLIST_UPDATE_LISTING_AUDIT_FAIL);
						type = Short.valueOf(BusinessLogEnum.LISTINGNOTPASS.getCode());
						remarks = BusinessLogEnum.LISTINGNOTPASS.getCodeName();
					}else{
						busiListingDao.updateListingFlag(record);
						//added by biran 20151008 后台审核通过时，更新审批时间，首次审批数量
						busiListingDao.updateListingFirstExamineInfo(record);
						//added end
						type = Short.valueOf(BusinessLogEnum.LISTINGPASS.getCode());
						remarks = BusinessLogEnum.LISTINGPASS.getCodeName();
					}
					busiListingLogDao.insertBusiListingLog(oldListing, remarks, GetBaseInfo.getBossUserId(), type.toString());
				} catch (Exception e) {
					return e;
				}
				return null;
			}
		});
		if(exception!=null){
			throw exception;
		}
		//打solr消息
		RabbitmqProducerManager.getInstance().pushMsgForSolr(SolrOperationTypeEnum.UPDATE, SolrContentTypeEnum.LISTING, busiListing.getListingid());
		//发动短信通知
		ucUserService.sendMessage(String.valueOf(busiListing.getUserid()), 
				GetMessageContext.getListingAuditMdg(busiListing.getListingid(), record.getListingflag().toString()),
				"审核挂牌[" + busiListing.getListingid() + "]发送短信通知出错，错误信息是：");
	}
	
	@Override
	public List<BusiListing> selectNotExpiredListings(Integer...beforeDays) {
		return busiListingDao.selectNotExpiredListings(beforeDays);
	}
	
	@Override
	public int findGoodsOrderState(String listingid){
		return busiListingDao.selectGoodsOrderState(listingid);
	}
	
	/**
	 * 微信/我的挂牌/挂牌剩余量、我的挂牌被摘总量 ，单位：吨
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public HashMap getSurplusesAndVolume(HashMap map) {
		return busiListingDao.getSurplusesAndVolume(map);
	}
	
	/**
	 * 微信查询历史挂牌
	 */
	@Override
	public List<BusiListingVo> findHistoryListing(Page<BusiListingVo> page) {
		return busiListingDao.findHistoryListing(page);
	}


	@Override
	public List<Map<String, Object>> selectListingByBreed(
			Map<String, Object> conMap) {
		return busiListingDao.selectListingByBreed(conMap);
	}
	
	/**
	 * 
	 * @Description: 调用存储过程，更新历史挂牌,订单业务员信息
	 * @Author: biran
	 * @Date: 2015年10月14日
	 * @param none
	 * @throws Exception
	 */
	@Override
	public void updateSalesManInfoByPRO() throws Exception{
		busiListingSalesmanDao.updateSalesManInfoByPRO();
		return;
	}
}
