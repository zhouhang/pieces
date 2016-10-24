package com.ms.dao.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: koabs
 * 10/24/16.
 * 用户经营类型
 */
public enum IdentityTypeEnum {

    t1(1, "饮片厂"),
    t2(2, "药厂"),
    t3(3, "药材经营公司"),
    t4(4, "个体经营户"),
    t5(5, "合作社"),
    t6(6, "种植基地"),
    t7(7, "其他"),
    t8(8, "个人经营"),
    t9(9, "采购经理"),
    t10(10, "销售经理"),;

    private Integer id;

    private String value;

    IdentityTypeEnum(Integer id, String value) {
        this.id = id;
        this.value = value;
    }

    private static Map<Integer, String> map;

    static {
        map = new HashMap<>();
        for (IdentityTypeEnum type : IdentityTypeEnum.values()) {
            map.put(type.getId(),type.getValue());
        }
    }

    /**
     * 通过ID来查询属性名称
     *
     * @param id
     * @return
     */
    public static String get(Integer id) {
        return map.get(id);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
