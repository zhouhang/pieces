package com.jointown.zy.common.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.jointown.zy.common.enums.BusinessLogEnum;
import com.jointown.zy.common.util.GsonFactory;

/**
 * 仓单操作日志表
 * 
 * @author Mr.song
 *
 * @date 2015-3-13
 */
@SuppressWarnings("serial")
public class BusiWhlistLog implements Serializable {
    /**
     * ZYC.BUSI_WHLIST_LOG.ID (仓单日志编号)
     * @ibatorgenerated 2015-03-13 17:07:51
     */
    private Long id;

    /**
     * ZYC.BUSI_WHLIST_LOG.USERID (当前操作者ID)
     * @ibatorgenerated 2015-03-13 17:07:51
     */
    private Long userid;

    /**
     * ZYC.BUSI_WHLIST_LOG.IP (操作者所在IP)
     * @ibatorgenerated 2015-03-13 17:07:51
     */
    private String ip;

    /**
     * ZYC.BUSI_WHLIST_LOG.BREEDCODE (品种编码)
     * @ibatorgenerated 2015-03-13 17:07:51
     */
    private Long breedcode;

    /**
     * ZYC.BUSI_WHLIST_LOG.WLID (仓单ID)
     * @ibatorgenerated 2015-03-13 17:07:51
     */
    private String wlid;

    /**
     * ZYC.BUSI_WHLIST_LOG.OPTYPE (操作类型(添加，修改，异常))
     * @ibatorgenerated 2015-03-13 17:07:51
     */
    private Short optype;

    /**
     * ZYC.BUSI_WHLIST_LOG.CREATETIME (创建日期)
     * @ibatorgenerated 2015-03-13 17:07:51
     */
    private Date createtime;

    /**
     * ZYC.BUSI_WHLIST_LOG.UPDATETIME (修改日期)
     * @ibatorgenerated 2015-03-13 17:07:51
     */
    private Date updatetime;

    /**
     * ZYC.BUSI_WHLIST_LOG.WLTOTAL (操作数量)
     * @ibatorgenerated 2015-03-13 17:07:51
     */
    private BigDecimal wltotal;

    /**
     * ZYC.BUSI_WHLIST_LOG.REMARKS (日志描述)
     * @ibatorgenerated 2015-03-13 17:07:51
     */
    private String remarks;
    
    /**
     * 仓单快照(仓单修改前)
     */
    private String whListSnapshot;
    
    
    /**
     * 
     * @Description: 设值
     * @Author: robin.liu
     * @Date: 2015年8月25日
     * @param busiWhlist
     * @param logType
     * @param userId
     * @param recordSnapshot
     * @return
     */
    public BusiWhlistLog setValue(BusiWhlist busiWhlist,BusinessLogEnum logType,Long userId,Object...data){
		this.setWlid(busiWhlist.getWlId());
		this.setUserid(userId);
		this.setBreedcode(busiWhlist.getBreedCode());
		this.setOptype(Short.valueOf(logType.getCode()));
		this.setRemarks(logType.getMessage(data));
		Date date = new Date();
		this.setCreatetime(date);
		this.setUpdatetime(date);
		this.setWltotal(new BigDecimal(busiWhlist.getWlTotal()));
		this.setWhListSnapshot(busiWhlist!=null?GsonFactory.createGson("yyyy-MM-dd HH:mm:ss.SSS").toJson(busiWhlist):"");
		return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Long getBreedcode() {
        return breedcode;
    }

    public void setBreedcode(Long breedcode) {
        this.breedcode = breedcode;
    }

    public String getWlid() {
        return wlid;
    }

    public void setWlid(String wlid) {
        this.wlid = wlid;
    }

    public Short getOptype() {
        return optype;
    }

    public void setOptype(Short optype) {
        this.optype = optype;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public BigDecimal getWltotal() {
        return wltotal;
    }

    public void setWltotal(BigDecimal wltotal) {
        this.wltotal = wltotal;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

	public String getWhListSnapshot() {
		return whListSnapshot;
	}

	public void setWhListSnapshot(String whListSnapshot) {
		this.whListSnapshot = whListSnapshot;
	}
}