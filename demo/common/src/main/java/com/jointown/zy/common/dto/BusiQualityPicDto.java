package com.jointown.zy.common.dto;

import com.jointown.zy.common.model.BusiQualitypic;

/**
 * 质检图片Dto
 * @author wangjunhu
 * 2014-12-27
 */
public class BusiQualityPicDto {

	private String wlid;
	private Long qcid;
	private Short isfile;
	private String path;
	private Short picindex;
	private BusiQualitypic busiqualitypic;
	
	private String file1;//散货照片
	private String file2;//细节照1
	private String file3;//细节照2
	private String file4;//细节照3
	private String file5;//包装照
	private String file6;//堆垛照
	private String file7;//质检报告照

	public BusiQualityPicDto() {
		super();
		busiqualitypic = new BusiQualitypic();
	}

	public String getWlid() {
		return wlid;
	}

	public void setWlid(String wlid) {
		this.wlid = wlid;
		busiqualitypic.setWlid(wlid);
	}

	public Long getQcid() {
		return qcid;
	}

	public void setQcid(Long qcid) {
		this.qcid = qcid;
		busiqualitypic.setQcid(qcid);
	}

	public Short getIsfile() {
		return isfile;
	}

	public void setIsfile(Short isfile) {
		this.isfile = isfile;
		busiqualitypic.setIsfile(isfile);
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
		busiqualitypic.setPath(path);
	}

	public Short getPicindex() {
		return picindex;
	}

	public void setPicindex(Short picindex) {
		this.picindex = picindex;
		busiqualitypic.setPicindex(picindex);
	}

	public BusiQualitypic getBusiqualitypic() {
		return busiqualitypic;
	}

	public void setBusiqualitypic(BusiQualitypic busiqualitypic) {
		this.busiqualitypic = busiqualitypic;
	}

	public String getFile1() {
		return file1;
	}

	public void setFile1(String file1) {
		this.file1 = file1;
	}

	public String getFile2() {
		return file2;
	}

	public void setFile2(String file2) {
		this.file2 = file2;
	}

	public String getFile3() {
		return file3;
	}

	public void setFile3(String file3) {
		this.file3 = file3;
	}

	public String getFile4() {
		return file4;
	}

	public void setFile4(String file4) {
		this.file4 = file4;
	}

	public String getFile5() {
		return file5;
	}

	public void setFile5(String file5) {
		this.file5 = file5;
	}

	public String getFile6() {
		return file6;
	}

	public void setFile6(String file6) {
		this.file6 = file6;
	}

	public String getFile7() {
		return file7;
	}

	public void setFile7(String file7) {
		this.file7 = file7;
	}
	
}
