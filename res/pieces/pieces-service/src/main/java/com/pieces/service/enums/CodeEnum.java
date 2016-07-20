package com.pieces.service.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 总属性枚举
 * Created by wangbin on 2016/7/20.
 */
public enum CodeEnum {


    //规格
    SPEC_STAGE(Type.SPEC, "段", 56),
    SPEC_STRIP(Type.SPEC, "条", 57),
    SPEC_BRANCH(Type.SPEC, "支", 58),
    SPEC_SINGLE(Type.SPEC, "个", 59),
    SPEC_SLICE(Type.SPEC, "片", 60),


    //产地
    ORIGIN_HUBEI(Type.ORIGIN, "湖北", 100),
    ORIGIN_YUNNAN(Type.ORIGIN, "云南", 101),
    ORIGIN_GUIZOU(Type.ORIGIN, "贵州", 102),

    //等级
    LEVEL_ONE(Type.LEVEL, "1", 200),
    LEVEL_TWO(Type.LEVEL, "2", 201),
    LEVEL_THREE(Type.LEVEL, "3", 202);

    private Type code;
    private String name;
    private Integer id;


    CodeEnum(Type code, String name, Integer id) {
        this.code=code;
        this.name=name;
        this.id=id;
    }

    /**
     * 根据属性类型查询所有属性
     * @param type
     * @return
     */
    public static List<CodeEnum> findByType(Type type){
        List<CodeEnum> codeEnumList = new ArrayList<>();
        for(CodeEnum codeEnum :CodeEnum.values() ){
            if(codeEnum.code.equals(type)){
                codeEnumList.add(codeEnum);
            }
        }
        return codeEnumList;
    }

    /**
     * 通过ID来查询属性名称
     * @param id
     * @return
     */
    public static String findNameById(Integer id){
        for(CodeEnum codeEnum :CodeEnum.values() ){
            if(codeEnum.getId().equals(id)){
                return codeEnum.getName();
            }
        }
        return null;
    }


    /**
     * 批量查询属性ID对应的属性名
     * @param ids
     * @return
     */
    public static Map<Integer,String> findNamesByIds(int... ids){
        if(ids==null||ids.length==0){
            return null;
        }
        Map<Integer,String> map = new HashMap<>();
        for(int id : ids){
            String value =  findNameById(id);
            if(value==null){
                throw new RuntimeException("找不到对应的ID:"+id);
            }
            map.put(id,value);
        }
        return map;
    }


    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    //分类
    public  enum Type{
        SPEC,ORIGIN,LEVEL
    }


}

