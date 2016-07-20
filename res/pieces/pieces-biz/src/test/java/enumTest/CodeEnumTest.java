package enumTest;


import com.pieces.service.enums.CodeEnum;

import java.util.List;
import java.util.Map;

/**
 * Created by wangbin on 2016/7/20.
 */
public class CodeEnumTest {


    public static void main(String[] args) {
        //查询所有规格
        List<CodeEnum> codeEnumList =  CodeEnum.findByType(CodeEnum.Type.SPEC);
        for(CodeEnum codeEnum : codeEnumList){
            System.out.println(codeEnum.getName());
        }
        //查询ID为102的属性名称
        String name =  CodeEnum.findNameById(102);
        System.out.println(name);

        //批量查询属性的名称
        Map<Integer,String> map =    CodeEnum.findNamesByIds(60,59,58);
        System.out.println(map);
    }
}
