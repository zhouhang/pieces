package com.pieces.dao.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 生成map
 * Created by wangbin on 2016/7/8.
 */
public class Params  implements Serializable{

    private static final long serialVersionUID = 1L;

    private Map<String,Object> map ;

    public Params(String key, Object value) {
        map = new HashMap<>();
        map.put(key,value);
    }

    public Params add(String key, Object value){
        this.map.put(key,value);
        return this;
    }
}
