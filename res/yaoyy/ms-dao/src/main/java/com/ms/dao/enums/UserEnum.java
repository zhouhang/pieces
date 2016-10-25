package com.ms.dao.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: koabs
 * 10/24/16.
 * 用户状态
 */
public enum  UserEnum {
    disable(-1,"禁用"),
    enable(0,"启用"),
    auto(1,"自动生成");

    private Integer type;
    private String value;

    UserEnum(Integer type, String value){
        this.type = type;
        this.value = value;
    }

    private static Map<Integer, String> map;

    static {
        map = new HashMap<>();
        for (UserEnum type : UserEnum.values()) {
            map.put(type.getType(),type.getValue());
        }
    }

    /**
     * 通过ID来查询属性名称
     *
     * @param type
     * @return
     */
    public static String get(Integer type) {
        return map.get(type);
    }


    public Integer getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
}
