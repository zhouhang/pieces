package com.jointown.zy.common.pay.ucs.util;

import java.io.File;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ucs.creditpay.security.CertificateVerifier;
import ucs.creditpay.security.PfxSigner;
import ucs.creditpay.security.SignatureFactory;
import ucs.creditpay.utils.UcsConfig;

import com.jointown.zy.common.util.PropertiesUtils;
/**
 * 招行接口初始化配置基类
 * @ClassName:UcsBase
 * @author:Calvin.Wangh
 * @Description:
 * @date:2015-4-24下午4:16:57
 * @version V1.0
 */
public class UcsBase {
	
	private static final Logger logger = LoggerFactory.getLogger(UcsBase.class);
	
	public UcsBase() {
		super();
	}
	
	/**
	 * 签名私钥 验证公钥 初始化
	 * @throws Exception
	 */
	public static void initialize() throws Exception {
		try {
			Map<String,Object> properties = PropertiesUtils.getProperties(UcsConfig.FILE_NAME);
			String certificatePath = (String) properties.get("certificate.path");
			//私钥
			String keystoreFilename = (String) properties.get("my.keystore.filename");
			//私钥密码
			String keystorePassword = (String) properties.get("my.keystore.password");
			if (keystoreFilename == null) {
				throw new Exception("Missing the property: my.keystore.filename");
			}
			String fullKeystoreFilename = certificatePath + File.separatorChar+ keystoreFilename;
			PfxSigner signer = new PfxSigner(fullKeystoreFilename, keystorePassword);
			SignatureFactory.addSigner("signer", signer);
			logger.info("[UCS]私钥签名完成  :"+signer);
			String certificateFilename = (String) properties.get("payment.certificate.filename");
			if (certificateFilename == null) {
				throw new Exception("Missing the property: payment.certificate.filename");
			}
			String fullCertificateFilename = certificatePath + File.separatorChar + certificateFilename;

			CertificateVerifier verifier = new CertificateVerifier(fullCertificateFilename);
			SignatureFactory.addVerifier("verifier", verifier);
			logger.info("[UCS]公钥验签完成 :"+verifier);
		} catch (Exception e) {
			logger.error("UseBase initialize() 签名验签初始化异常。。。" + e);
			throw e;
		}
	}
}
