package com.ms.dao.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiao on 2016/10/26.
 *
 */
public enum CodeEnum {

    CODE_UNIT(0, "UNIT");

    private Integer id;

    private String value;

    CodeEnum(Integer id, String value) {
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
