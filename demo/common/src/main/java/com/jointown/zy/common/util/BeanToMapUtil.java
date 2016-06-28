package com.jointown.zy.common.util;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.springframework.util.Assert;

public class BeanToMapUtil {
	
	public static Map<String, String> getParameters(Object obj) {
        return getParameters(obj, "yyyy-MM-dd");
    }
	
	public static Map<String, String> getParameters(Object obj, String dateFormat) {
        Assert.notNull(obj);
        Class clazz = obj.getClass();
        HashMap resMap = new HashMap();
        try {
            HashMap map = new HashMap();
            getClass(clazz, map, obj);
            resMap = convertHashMap(map, dateFormat);
        } catch (Exception e) {
        }
        return resMap;
    }
	
	public static Map<String, Object> getOriginalParameters(Object obj) {
        Assert.notNull(obj);
        Class clazz = obj.getClass();
        HashMap map = new HashMap();
        try {
            getClass(clazz, map, obj);
        } catch (Exception e) {
        }
        return map;
    }

    private static void getClass(Class clazz, HashMap map, Object obj)
        throws Exception {
        if (clazz.getSimpleName().equals("Object")) {
            return;
        }
        Field[] fields = clazz.getDeclaredFields();
        if ((fields != null) && (fields.length > 0)) {
            for (int i = 0; i < fields.length; ++i) {
                fields[i].setAccessible(true);
                String name = fields[i].getName();
                if ("serialVersionUID".equals(name)) {
                    continue;
                }
                Object value = fields[i].get(obj);
                map.put(name, value);
            }
        }

        Class superClzz = clazz.getSuperclass();
        getClass(superClzz, map, obj);
    }

    private static HashMap<String, String> convertHashMap(HashMap map, String dateFormat)
        throws Exception {
        HashMap newMap = new HashMap();
        Set keys = map.keySet();
        Iterator it = keys.iterator();
        while (it.hasNext()) {
            Object key = it.next();
            convertToString(map.get(key), newMap, key, dateFormat);
        }

        return newMap;
    }

    private static void convertToString(Object value, HashMap newMap, Object key, String dateFormat) {
        if (value != null) {
            Class clazz = value.getClass();
            if (isBaseType(clazz)) {
                newMap.put(key, value.toString());
            } else if (clazz == String.class) {
                newMap.put(key, value.toString());
            } else if (clazz == java.util.Date.class) {
                java.util.Date date = (java.util.Date) value;
                SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
                newMap.put(key, sdf.format(date));
            } else if (clazz == Timestamp.class) {
                Timestamp timestamp = (Timestamp) value;
                long times = timestamp.getTime();
                java.util.Date date = new java.util.Date(times);
                SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

                newMap.put(key, sdf.format(date));
            } else if (clazz == java.sql.Date.class) {
                java.sql.Date sqlDate = (java.sql.Date) value;
                SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
                newMap.put(key, sdf.format(sqlDate));
            } else {
                newMap.put(key, value);
            }
        } else {
        	newMap.put(key, value);
        }
    }

    private static boolean isBaseType(Class clazz) {
        boolean isBaseType = false;

        if (clazz == Integer.class) {
            isBaseType = true;
        }
        if (clazz == Long.class) {
            isBaseType = true;
        }
        if (clazz == Double.class) {
            isBaseType = true;
        }
        if (clazz == Byte.class) {
            isBaseType = true;
        }
        if (clazz == Float.class) {
            isBaseType = true;
        }
        if (clazz == Short.class) {
            isBaseType = true;
        }
        if (clazz == Boolean.class) {
            isBaseType = true;
        }
        return isBaseType;
    }
}
