package tools;

import com.pieces.service.dto.Password;
import com.pieces.service.utils.EncryptUtil;

/**
 * Created by wangbin on 2016/7/8.
 */
public class PasswordTest {

    public static void main(String[] args) {
        Password pass = EncryptUtil.PiecesEncode("123456");

        System.out.println(pass.getPassword());
        System.out.println(pass.getSalt());


    }
}
