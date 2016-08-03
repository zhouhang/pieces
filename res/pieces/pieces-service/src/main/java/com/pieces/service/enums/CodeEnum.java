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
    SPEC_YINYANGSLICE(Type.SPEC, "阴阳片", 39),
    SPEC_WALNUTSLICE(Type.SPEC, "佛手片", 40),
    SPEC_ROUNDSLICE(Type.SPEC, "圆厚片", 41),
    SPEC_DIGPACHYSLICE(Type.SPEC, "刨厚片", 42),
    SPEC_BIASSLICE(Type.SPEC, "斜薄片", 43),
    SPEC_LONGSTAGE(Type.SPEC, "长段", 44),
    SPEC_TWOOPEN(Type.SPEC, "两开", 45),
    SPEC_DIGSSLICE(Type.SPEC, "刨片", 46),
    SPEC_PACHYSLICE(Type.SPEC, "厚片", 47),
    SPEC_THINSLICE(Type.SPEC, "薄片", 48),
    SPEC_SILK(Type.SPEC, "丝", 49),
    SPEC_TINCA(Type.SPEC, "丁", 50),
    SPEC_PIECE(Type.SPEC, "块", 51),
    SPEC_GRAIN(Type.SPEC, "粒", 52),
    SPEC_POWDER(Type.SPEC, "粉", 53),
    SPEC_ROLL(Type.SPEC, "轧", 54),
    SPEC_SMASH(Type.SPEC, "碎", 55),
    SPEC_STAGE(Type.SPEC, "段", 56),
    SPEC_STRIP(Type.SPEC, "条", 57),
    SPEC_BRANCH(Type.SPEC, "支", 58),
    SPEC_SINGLE(Type.SPEC, "个", 59),
    SPEC_SLICE(Type.SPEC, "片", 60),


    //产地
    ORIGIN_HUBEI(Type.ORIGIN, "湖北", 100),
    ORIGIN_YUNNAN(Type.ORIGIN, "云南", 101),
    ORIGIN_GUIZOU(Type.ORIGIN, "贵州", 102),
    ORIGIN_ANHUI(Type.ORIGIN, "安徽", 103),

    //等级
    LEVEL_ONE(Type.LEVEL, "1", 200),
    LEVEL_TWO(Type.LEVEL, "2", 201),
    LEVEL_THREE(Type.LEVEL, "3", 202),


    //广告类型
    AD_SEARCH(Type.AD,"搜索关键字",301),
    AD_BANNER(Type.AD,"首页Banner",302);


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
                throw new RuntimeException("找不到对应的属性,ID:"+id);
            }
            map.put(id,value);
        }
        return map;
    }

    public String getTitle(){
        return name;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    //分类
    public enum Type{
        SPEC,ORIGIN,LEVEL,AD
    }


}

