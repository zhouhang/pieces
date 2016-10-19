package com.ms.service.utils;

import com.ms.service.dto.Password;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.xerces.impl.dv.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

/**
 *
 *
 */
public class EncryptUtil
{
	private static final String DEFAULT_ENCODE = "UTF-8";
	
	/**注册时调用，此方法用于通过原生密码生成随机盐和加密密码
	 * 
	 * Used to create salted password and salt
	 * @param plain password
	 * @return Password class which contains salted password and salt.
	 */
	public static Password PiecesEncode(String plainPassword){
		String random=RandomStringUtils.randomAlphanumeric(32);
		return PiecesEncode(plainPassword,random);
	}
	
	/**登录时调用，此方法用于通过原生密码和盐生成加密密码
	 * 
	 * @param plainPassword
	 * @param salt
	 * @return Password class which contains salted password and salt.
	 */
    public static Password PiecesEncode(String plainPassword,String salt,String... encodes){
		String finalPass = plainPassword;
		String encode = ArrayUtils.isEmpty(encodes)?DEFAULT_ENCODE:encodes[0];
		finalPass = salt+plainPassword;
		Password p = new Password();
		p.setPlainPassword(plainPassword);
		p.setSalt(String.valueOf(salt));
		p.setPassword(EncryptUtil.getSHA1(finalPass, encode));
		return p;
	}
    
	public static String getMD5(String str,String encode)
	{
		return encode(str, "MD5",encode);
	}

	public static String getSHA1(String str,String encode)
	{
		return encode(str, "SHA-1",encode);
	}

	private static String encode(String str, String type,String encode)
	{
		try
		{
			MessageDigest alga = MessageDigest.getInstance(type);
			alga.update(str.getBytes(encode));
			byte[] digesta = alga.digest();
			return byte2hex(digesta);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}

	public static String byte2hex(byte[] b)
	{
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++)
		{
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}
		return hs.toUpperCase();
	}

	public static byte[] hex2byte(byte[] b)
	{
		if ((b.length % 2) != 0)
			throw new IllegalArgumentException("长度不是偶数");
		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2)
		{
			String item = new String(b, n, 2);
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}
		return b2;
	}
	
	 // 将 s 进行 BASE64 编码
    public static String base64Encode(String s,String encode) throws UnsupportedEncodingException {
        if (s == null)
            return null;
        return Base64.encode(s.getBytes(encode));
    }

    // 将 BASE64 编码的字符串 s 进行解码
    public static String base64Decode(String s,String encode) {
        if (s == null)
            return null;
        try {
        	byte[] b =  Base64.decode(s);
			return new String(b,encode);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
    }
    
}
