package com.jointown.zy.common.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
*   
* 项目名称：common  
* 类名称：SftpConfigInfo  
* 类描述：  初始化上传图片的属性文件
* 创建人：Mr.songwei  
* 创建时间：2014-12-4 下午1:50:19  
* 修改人：  
* 修改时间：2014-12-4 下午1:50:19  
* 修改备注：  
* @version   
*
 */
@Component("sftpConfigInfo")
public class SftpConfigInfo {		
	//width=80
	@Value("${sftp.pic.small.width}")   
	private int sftpSmallWidth;
	
	//width=120
	@Value("${sftp.pic.middle.width}")   
	private int sftpMiddleWidth;
	
	//width=175
	@Value("${sftp.pic.big.width}")   
	private int sftpBigWidth;
	
	//width=320
	@Value("${sftp.pic.xbig.width}")   
	private int sftpXBigWidth;
	
	//width=640
	@Value("${sftp.pic.xxbig.width}")   
	private int sftpXXBigWidth;
	
	//width=970
	@Value("${sftp.pic.xxxbig.width}")   
	private int sftpXXXBigWidth;
	
	@Value("${sftp.pic.path}")
	private String sftpPath;
	
	@Value("${sftp.temppic.path}")
	private String sftpTempPath;
	
	//width=320
	public int getSftpXBigWidth() {
		return sftpXBigWidth;
	}

	public void setSftpXBigWidth(int sftpXBigWidth) {
		this.sftpXBigWidth = sftpXBigWidth;
	}

	//width=640
	public int getSftpXXBigWidth() {
		return sftpXXBigWidth;
	}

	public void setSftpXXBigWidth(int sftpXXBigWidth) {
		this.sftpXXBigWidth = sftpXXBigWidth;
	}

	//width=970
	public int getSftpXXXBigWidth() {
		return sftpXXXBigWidth;
	}

	public void setSftpXXXBigWidth(int sftpXXXBigWidth) {
		this.sftpXXXBigWidth = sftpXXXBigWidth;
	}

	public String getSftpTempPath() {
		return sftpTempPath;
	}

	public void setSftpTempPath(String sftpTempPath) {
		this.sftpTempPath = sftpTempPath;
	}

	public String getSftpTempProjectDir() {
		return sftpTempProjectDir;
	}

	public void setSftpTempProjectDir(String sftpTempProjectDir) {
		this.sftpTempProjectDir = sftpTempProjectDir;
	}

	@Value("${sftp.userName}")
	private String sftpUserName;
	
	@Value("${sftp.password}")
	private String sftpPassword;
	
	@Value("${sftp.ip}")
	private String sftpIp;
	
	@Value("${sftp.port}")
	private String sftpPort;
	
	@Value("${sftp.data.dir}")
	private String sftpDataDir;
	
	@Value("${sftp.images.dir}")
	private String sftpImagesDir;
	
	@Value("${sftp.project.dir}")
	private String sftpProjectDir;
	
	@Value("${sftp.tempproject.dir}")
	private String sftpTempProjectDir;
	
	//width=80
	public int getSftpSmallWidth() {
		return sftpSmallWidth;
	}

	public void setSftpSmallWidth(int sftpSmallWidth) {
		this.sftpSmallWidth = sftpSmallWidth;
	}

	//width=120
	public int getSftpMiddleWidth() {
		return sftpMiddleWidth;
	}

	public void setSftpMiddleWidth(int sftpMiddleWidth) {
		this.sftpMiddleWidth = sftpMiddleWidth;
	}

	//width=175
	public int getSftpBigWidth() {
		return sftpBigWidth;
	}

	public void setSftpBigWidth(int sftpBigWidth) {
		this.sftpBigWidth = sftpBigWidth;
	}

	/**
	 * @return the sftpPath
	 */
	public String getSftpPath() {
		return sftpPath;
	}

	/**
	 * @param sftpPath the sftpPath to set
	 */
	public void setSftpPath(String sftpPath) {
		this.sftpPath = sftpPath;
	}

	public String getSftpUserName() {
		return sftpUserName;
	}

	public void setSftpUserName(String sftpUserName) {
		this.sftpUserName = sftpUserName;
	}

	public String getSftpPassword() {
		return sftpPassword;
	}

	public void setSftpPassword(String sftpPassword) {
		this.sftpPassword = sftpPassword;
	}

	public String getSftpIp() {
		return sftpIp;
	}

	public void setSftpIp(String sftpIp) {
		this.sftpIp = sftpIp;
	}

	public String getSftpPort() {
		return sftpPort;
	}

	public void setSftpPort(String sftpPort) {
		this.sftpPort = sftpPort;
	}

	public String getSftpDataDir() {
		return sftpDataDir;
	}

	public void setSftpDataDir(String sftpDataDir) {
		this.sftpDataDir = sftpDataDir;
	}

	public String getSftpImagesDir() {
		return sftpImagesDir;
	}

	public void setSftpImagesDir(String sftpImagesDir) {
		this.sftpImagesDir = sftpImagesDir;
	}

	public String getSftpProjectDir() {
		return sftpProjectDir;
	}

	public void setSftpProjectDir(String sftpProjectDir) {
		this.sftpProjectDir = sftpProjectDir;
	}
	
	//添加微信相关配置
	@Value("${sftp.pic.path.wx}")
	private String sftpPathWx;
	
	@Value("${sftp.temppic.path.wx}")
	private String sftpTempPathWx;
	
	@Value("${sftp.ip.wx}")
	private String sftpIpWx;
	
	@Value("${sftp.project.dir.wx}")
	private String sftpProjectDirWx;
	
	@Value("${sftp.tempproject.dir.wx}")
	private String sftpTempProjectDirWx;
	
	@Value("${sftp.data.dir.wx}")
	private String sftpDataDirWx;

	public String getSftpPathWx() {
		return sftpPathWx;
	}

	public void setSftpPathWx(String sftpPathWx) {
		this.sftpPathWx = sftpPathWx;
	}

	public String getSftpTempPathWx() {
		return sftpTempPathWx;
	}

	public void setSftpTempPathWx(String sftpTempPathWx) {
		this.sftpTempPathWx = sftpTempPathWx;
	}

	public String getSftpIpWx() {
		return sftpIpWx;
	}

	public void setSftpIpWx(String sftpIpWx) {
		this.sftpIpWx = sftpIpWx;
	}

	public String getSftpProjectDirWx() {
		return sftpProjectDirWx;
	}

	public void setSftpProjectDirWx(String sftpProjectDirWx) {
		this.sftpProjectDirWx = sftpProjectDirWx;
	}

	public String getSftpTempProjectDirWx() {
		return sftpTempProjectDirWx;
	}

	public void setSftpTempProjectDirWx(String sftpTempProjectDirWx) {
		this.sftpTempProjectDirWx = sftpTempProjectDirWx;
	}

	public String getSftpDataDirWx() {
		return sftpDataDirWx;
	}

	public void setSftpDataDirWx(String sftpDataDirWx) {
		this.sftpDataDirWx = sftpDataDirWx;
	}
}
