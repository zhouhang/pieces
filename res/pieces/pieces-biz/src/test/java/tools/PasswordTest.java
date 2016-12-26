package tools;

import com.pieces.service.dto.Password;
import com.pieces.service.utils.EncryptUtil;
import com.pieces.tools.utils.MD5Util;

/**
 * Created by wangbin on 2016/7/8.
 */
public class PasswordTest {

    public static void main(String[] args) {

        String text =  MD5Util.sign("0","fe68a821107c34bf9863348831e26755","utf-8");
        System.out.println(System.currentTimeMillis());
        System.out.println((System.currentTimeMillis()+"").length());
    }
}
