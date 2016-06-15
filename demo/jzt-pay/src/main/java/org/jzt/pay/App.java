package org.jzt.pay;

import com.google.gson.JsonObject;
import com.jointown.zy.common.pay.PayUtil;
import com.jointown.zy.common.wms.WmsRestHttpUtil;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //System.out.println( "Hello World!" );
        
        String url = "http://127.0.0.1/queryPayResult";
        String url2 = "http://127.0.0.1/donePayResult";
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("sourceSys", "0");
        jsonObject.addProperty("num", "3");
        String data = null;
        try {
        	data = PayUtil.getDesEncryptData(jsonObject.toString());
        	System.out.println("data is :" + data);
			String ss = WmsRestHttpUtil.wmsRestPost(url, data);
			System.out.println("ss is :" + ss);
			System.out.println("ss 解密之后的数据是：" + PayUtil.getDesDecryptData(ss));
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
