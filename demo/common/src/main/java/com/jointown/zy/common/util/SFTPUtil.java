package com.jointown.zy.common.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

/**
* 项目名称：common  
* 类名称：SFTPUtil  
* 类描述：  sftp文件上传工具，主要包括上传、下载、删除
* 创建人：Mr.songwei  
* 创建时间：2015-1-22 下午2:54:01  
* 修改人：  
* 修改时间：2015-1-22 下午2:54:01  
* 修改备注：  
* @version   v1.0
 */
public class SFTPUtil {
	private static final Logger log = LoggerFactory.getLogger(SFTPUtil.class);
	private volatile static SFTPUtil singleton;
	private static final String CHARSET = "UTF-8";  
	
	//私有构造方法
	private SFTPUtil() {
	}
	
	//单例模式
	public static SFTPUtil getSingleton() {
		if (singleton == null) {
			synchronized (SFTPUtil.class) {
				if (singleton == null) {
					singleton = new SFTPUtil();
				}
			}
		}
		return singleton;
	}
	
	/**
	 * 获取SFTP管道(sftp)
	 * @param ip
	 * @param user
	 * @param psw
	 * @param port
	 * @param type  连接类型：TYPE：exec 、 sftp
	 * @return <T> T 
	 */
	public ChannelSftp  getChannelSftp(Session session,String type){
		ChannelSftp channel = null;
		try {
			// 创建sftp通信通道
			channel = (ChannelSftp) session.openChannel("sftp");
			channel.connect();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return channel;
	}
	
	/**
	 * 打开session通道
	 * @param ip  主机IP
	 * @param user主机登陆用户名
	 * @param psw 主机登陆密码
	 * @param port主机ssh2登陆端口，如果取默认值，传-1
	 * @return 
	 */
	public Session openSession(String ip, String user, String psw,int port) throws Exception {
		JSch jsch = new JSch();
		Session session = null;
		if (port <= 0) {
			// 连接服务器，采用默认端口
			session = jsch.getSession(user, ip);
		} else {
			// 采用指定的端口连接服务器
			session = jsch.getSession(user, ip, port);
		}
		// 如果服务器连接不上，则抛出异常
		if (session == null) {
			throw new Exception("session is null");
		}
		// 设置登陆主机的密码
		session.setPassword(psw);// 设置密码
		
		// 设置第一次登陆的时候提示，可选值：(ask | yes | no)
		Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config); // 为Session对象设置properties
		
		// 设置登陆超时时间
		session.setTimeout(30000);
		session.connect();
		return session;
	}

	/**
	 * 关闭session,关闭SFTP连接
	 * @param sftp
	 * @throws Exception
	 */
	public void closeChannel(Channel sftp,Session session) throws Exception {
		if (sftp != null) {
			if (sftp.isConnected()) {
				sftp.disconnect();
			} else if (sftp.isClosed()) {
				log.info("sftp is closed already");
			}
		}
		if (session != null) {
			if (session.isConnected()) {
				session.disconnect();
			} else{
				log.info("session is closed already");
			}
		}
	}

	/**
	 * SFTP上传图片方法 上传文件存放目录:根目录/data/images/工程名/yyyyMMdd/
	 * 备注：为了提高连接可用性，统一在执行批量操作后调用closeChannel关闭连接 
	 * @param sftp
	 * @param fileName  目标文件名
	 * @param localFile 本地文件流
	 * @param dateDir   日期目录
	 * @throws Exception
	 */
	public void upload(ChannelSftp sftp, String fileName,
			InputStream instream, String dateDir, String dataDir,
			String imagesDir, String projectDir) throws Exception {
		try {
			sftp.cd("/"); // 切换到根目录
			// 判断data目录是否存在
			handleDir(sftp, dataDir);
			// 判断一级目录是否存在，存在则切换到此目录
			handleDir(sftp, imagesDir);
			// 二级目录
			handleDir(sftp, projectDir);
			// 日期目录
			handleDir(sftp, dateDir);
			sftp.put(instream, fileName);  //sftp自带的上传方法 alter by Mr.songwei 2015.1.22

			log.info("upload pic success!");
		} catch (Exception e) {
			log.info("upload pic fail!>>>"+e.getMessage());
		}
	}

	// 判断目录是否存在
	@SuppressWarnings("static-access")
	private  void handleDir(ChannelSftp sftp, String dir) {
		try {
			sftp.cd(dir);
		} catch (SftpException sException) {
			if (sftp.SSH_FX_NO_SUCH_FILE == sException.id) { // 指定上传路径不存在
				try {
					sftp.mkdir(dir);
					sftp.cd(dir);
				} catch (SftpException e) {
					log.error("the dir:" + dir+ " is not exists,mk or cd error", e);
				}
			}
		}
	}

	/**
	 * 删除文件 参看父类中的注释 @see
	 * cn.mr.mohurd.service.sftp.FileTransferService#delete(java.lang.String,
	 * java.lang.String)
	 */
	public void delete(ChannelSftp sftp, String deleteFile,
			String dateDir, String dataDir, String imagesDir, String projectDir)
			throws Exception {
		try {
			sftp.cd("/");
			sftp.cd(dataDir);
			sftp.cd(imagesDir);
			sftp.cd(projectDir);
			sftp.cd(dateDir);
			sftp.rm(deleteFile);
		} catch (Exception e) {
			log.error("delete file fail:"+e.getMessage());
		}
	}

	/**
	 * SFTP从一个目录备份一个文件到另外一个目录下
	 * @param session
	 * @param srcDir   如: '/data/images/temp/201501/'
	 * @param desDir   如: '/data/images/jzt-user/201501/'
	 * @param fileName 如: abc.jpg
	 * @throws Exception
	 */
	private void download(Session session,String srcDir,String desDir,String fileName) throws Exception {
      	if(StringUtils.isBlank(srcDir) ||StringUtils.isBlank(desDir)||StringUtils.isBlank(fileName)){
    	  log.error("move pic fail:path is empty.");
    	  return;
      	}
      	//-f ：强制的意思，如果目标文件已经存在，不会询问而直接覆盖;
      	//-u ：若目标文件已经存在，且 source 比较新，才会更新(update)
      	//-t  ： --target-directory=DIRECTORY move all SOURCE arguments into DIRECTORY，即指定mv的目标目录，该选项适用于移动多个源文件到一个目录的情况，此时目标目录在前，源文件在后。
      	 ChannelExec channel = (ChannelExec)session.openChannel("exec");  
      	 channel.setCommand("mv -f -u  "+srcDir+fileName+"  "+desDir+fileName);  
      	 channel.setInputStream(null);  
      	 channel.setErrStream(System.err);  
         channel.connect();  
	        
	      InputStream in = channel.getInputStream();  
	      BufferedReader reader = new BufferedReader(new InputStreamReader(in, Charset.forName(CHARSET)));  
	      String buf = null;  
	      StringBuffer b = new StringBuffer();
	      while ((buf = reader.readLine()) != null)  
	      {  
	          b.append(buf);  
	      }  
	      log.info("pic move final_path:::"+b.toString());
	      reader.close(); 
	      channel.disconnect();
	}

	/**
	 * 移动图片到正式目录下
	 * @param imgpath
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	public void moveImg(Session session,HashMap<String,Object> map) throws NumberFormatException,Exception {
		String imgpath = map.get("imgpath").toString();                 //图片路径
		String dataDir = map.get("dataDir").toString();                 //data
		String imagesDir = map.get("imagesDir").toString();             //images
		String projectDir = map.get("projectDir").toString();           //实际项目目录
		String tempProjectDir = map.get("tempProjectDir").toString();   //临时项目目录
		if(StringUtils.isBlank(imgpath)) {
			log.warn("moveImg error: imgpath is null.");
			return;
		}
		try {
			String fileName = imgpath.substring(imgpath.lastIndexOf("/") + 1);
			String b = imgpath.substring(0, imgpath.lastIndexOf("/"));
			String dateDir = b.substring(b.lastIndexOf("/") + 1);
			// 创建sftp通信通道
			ChannelSftp channel = (ChannelSftp) session.openChannel("sftp");
			channel.connect();
						
			channel.cd("/"); // 切换到根目录
			// 判断data目录是否存在
			handleDir(channel, dataDir);
			// 判断一级目录是否存在，存在则切换到此目录
			handleDir(channel, imagesDir);
			// 二级目录
			handleDir(channel, projectDir);
			// 日期目录
			handleDir(channel, dateDir);
			
			//移动图片
			download(session,"/"+dataDir+"/"+imagesDir+"/"+tempProjectDir+"/"+dateDir+"/", "/"+dataDir+"/"+imagesDir+"/"+projectDir+"/"+dateDir+"/",fileName);
			
			//关闭检索文件夹的通道
			if(channel!=null && channel.isConnected()) channel.disconnect();  
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("moveImg error:" + e.getMessage());
		} 
	}
	
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		String charset = "UTF-8";  
        String user = "root";  
        String passwd = "password!";  
        String host = "10.3.1.151";  
        String command = "mv -f -u  /data/images/temp/201501/abc.png  /data/images/jzt-user/201501/abc.png";  
          
        JSch jsch = new JSch();  
        Session session = jsch.getSession(user, host, 22);  
        session.setPassword(passwd);  
        java.util.Properties config = new java.util.Properties();  
        config.put("StrictHostKeyChecking", "no");  
        session.setConfig(config);  
        session.connect();  
          
        Channel channel = session.openChannel("exec");  
        ((ChannelExec) channel).setCommand(command);  
        channel.setInputStream(null);  
        ((ChannelExec) channel).setErrStream(System.err);  
          
        channel.connect();  
        InputStream in = channel.getInputStream();  
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, Charset.forName(charset)));  
        String buf = null;  
        while ((buf = reader.readLine()) != null)  
        {  
            System.out.println(buf);  
        }  
        reader.close();  
        channel.disconnect();  
        session.disconnect();  
		
	}
}