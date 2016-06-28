package com.jointown.zy.common.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 供应信息图片
 *
 * @author aizhengdong
 *
 * @data 2015年3月24日
 */
public class WxSupplyPic implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
     * WEIXIN.WX_SUPPLY_PIC.SUPPLY_PIC_ID (供应信息图片表ID，主键)
     * @ibatorgenerated 2015-04-12 15:25:47
     */
    private Long supplyPicId;

    /**
     * WEIXIN.WX_SUPPLY_PIC.SUPPLY_ID (供应信息ID)
     * @ibatorgenerated 2015-04-12 15:25:47
     */
    private Long supplyId;

    /**
     * WEIXIN.WX_SUPPLY_PIC.TYPE (排序号(1个信息最多6张图片，每张图片缩略后进行存储，存储一张缩略大图，一张缩略小图))
     * @ibatorgenerated 2015-04-12 15:25:47
     */
    private Short type;

    /**
     * WEIXIN.WX_SUPPLY_PIC.STATUS (状态：0-正常 1-删除)
     * @ibatorgenerated 2015-04-12 15:25:47
     */
    private Short status;

    /**
     * WEIXIN.WX_SUPPLY_PIC.PICPATH (图片路径)
     * @ibatorgenerated 2015-04-12 15:25:47
     */
    private String picPath;

    /**
     * WEIXIN.WX_SUPPLY_PIC.CREATE_TIME (创建时间)
     * @ibatorgenerated 2015-04-12 15:25:47
     */
    private Date createTime;

    /**
     * WEIXIN.WX_SUPPLY_PIC.UPDATE_TIME (最后更新时间)
     * @ibatorgenerated 2015-04-12 15:25:47
     */
    private Date updateTime;

    public Long getSupplyPicId() {
        return supplyPicId;
    }

    public void setSupplyPicId(Long supplyPicId) {
        this.supplyPicId = supplyPicId;
    }

    public Long getSupplyId() {
        return supplyId;
    }

    public void setSupplyId(Long supplyId) {
        this.supplyId = supplyId;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
