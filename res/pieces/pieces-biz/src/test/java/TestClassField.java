import com.pieces.dao.vo.AdVo;
import com.pieces.tools.utils.Reflection;

import java.util.Date;

/**
 * Created by wangbin on 2016/8/3.
 */
public class TestClassField {

    public static void main(String[] args) {

        AdVo adVo = new AdVo();
        adVo.setTypeName("typename");
        adVo.setId(1);
        adVo.setLink("xxx");

        String result =  Reflection.serialize2Str(adVo);

        System.out.println(result);
    }
}
