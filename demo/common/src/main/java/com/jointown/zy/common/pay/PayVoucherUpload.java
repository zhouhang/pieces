package com.jointown.zy.common.pay;

import java.io.InputStream;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;
import com.jointown.zy.common.util.SFTPUtil;
import com.jointown.zy.common.util.SftpConfigInfo;
import com.jointown.zy.common.util.TimeUtil;
import com.jointown.zy.common.util.image.UploadUtils;

/**
 * @ClassName: PayVoucherUpload
 * @Description: 银行汇款,支付凭证上传
 * @Author: ldp
 * @Date: 2015年5月14日
 * @Version: 1.0
 */
@Component("payVoucherUpload")
public class PayVoucherUpload {
	
	private static final Logger log = LoggerFactory.getLogger(PayVoucherUpload.class);
	@Autowired
	private SftpConfigInfo sftpConfigInfo;

	/**
	 * 银行汇款支付凭证上传
	 * @Author: ldp
	 * @Date: 2015年5月14日
	 * @param ins
	 * @param type
	 * @return
	 */
	public String uploadPic(InputStream ins){
		SFTPUtil sftp = SFTPUtil.getSingleton();  //sftp初始化
		ChannelSftp channel = null;               //sftp管道声明
		Session session = null;  //session声明
		try {
			String fileName = UploadUtils.generateFilename("jpg");
			String dateDir = TimeUtil.getTimeShowByTimePartten(new Date(),"yyyyMM");
			session = sftp.openSession(sftpConfigInfo.getSftpIp(), sftpConfigInfo.getSftpUserName(), sftpConfigInfo.getSftpPassword(),Integer.parseInt(sftpConfigInfo.getSftpPort()));
			channel = sftp.getChannelSftp(session,"sftp");  //获取管道符
			
			//上传原图片到资源服务器目录下
			sftp.upload(channel, fileName, ins, dateDir,sftpConfigInfo.getSftpDataDir(),sftpConfigInfo.getSftpImagesDir(),sftpConfigInfo.getSftpProjectDir());
			return sftpConfigInfo.getSftpImagesDir()+"/"+sftpConfigInfo.getSftpProjectDir()+"/"+dateDir+"/"+fileName;
		} catch (Exception e) {
			log.debug("pay voucher upload pic error is:",e);
			return null;
		}finally{
			//关闭管道连接
	        try {
				sftp.closeChannel(channel,session);
			} catch (Exception e) {
				log.debug("pay voucher upload pic error is:",e);
				return null;
			}
		}
		
	}
}
