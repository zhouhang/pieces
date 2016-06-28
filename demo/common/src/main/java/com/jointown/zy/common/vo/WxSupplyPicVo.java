package com.jointown.zy.common.vo;

/**
 *
 *
 * @author aizhengdong
 *
 * @data 2015年3月18日
 */
public class WxSupplyPicVo {
	
	/** 缩略图ID*/
	private Long  thumbnailPicId;
	
	/** 缩略图*/
	private String thumbnailPath;
	
	/** 原图ID*/
	private Long  originalPicId;
	
	/** 原图  */
	private String originalPath;

	public Long getThumbnailPicId() {
		return thumbnailPicId;
	}

	public void setThumbnailPicId(Long thumbnailPicId) {
		this.thumbnailPicId = thumbnailPicId;
	}

	public String getThumbnailPath() {
		return thumbnailPath;
	}

	public void setThumbnailPath(String thumbnailPath) {
		this.thumbnailPath = thumbnailPath;
	}

	public Long getOriginalPicId() {
		return originalPicId;
	}

	public void setOriginalPicId(Long originalPicId) {
		this.originalPicId = originalPicId;
	}

	public String getOriginalPath() {
		return originalPath;
	}

	public void setOriginalPath(String originalPath) {
		this.originalPath = originalPath;
	}
}
