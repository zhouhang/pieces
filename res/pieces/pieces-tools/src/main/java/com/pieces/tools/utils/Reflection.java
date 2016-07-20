package com.pieces.tools.utils;

import java.lang.reflect.Field;

/**
 * Author: koabs
 * 7/19/16.
 */
public class Reflection {

    /**
     * 将对象序列化url 的参数形式. a=wq&b=as
     * @param object
     * @return
     */
    public static String serialize(Object object) {

        Field[] fields = object.getClass().getFields();
        StringBuffer sb = new StringBuffer();
        try {
            for(Field field : fields){
                if(field.get(object)!=null){
                    sb.append("&").append(field.getName()).append("=").append(field.get(object).toString());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return sb.toString();
    }
}
