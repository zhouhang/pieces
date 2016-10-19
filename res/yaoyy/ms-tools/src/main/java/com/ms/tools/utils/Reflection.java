package com.ms.tools.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        List<Field> fieldList = new ArrayList<>();
        StringBuffer sb = new StringBuffer();
        try {
            Class<?> clazz = object.getClass();
            //获取所有父类
            for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
                Field[] field = clazz.getDeclaredFields();
                fieldList.addAll(Arrays.asList(field));
            }
            //获取所有字段
            for(Field field : fieldList){
                field.setAccessible(true);
                if(field.get(object)!=null&&(!field.getName().equals("serialVersionUID"))){
                    sb.append("&").append(field.getName()).append("=").append(field.get(object).toString());
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return sb.toString();
    }




}
