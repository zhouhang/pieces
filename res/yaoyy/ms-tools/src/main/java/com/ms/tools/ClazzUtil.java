package com.ms.tools;

import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: koabs
 * 10/12/16.
 * 实体类属性复制工具类
 */
public class ClazzUtil {
    /**
     * The constant beanCopierMap.
     */
    public static final Map<String, BeanCopier> beanCopierMap = new HashMap<>();

    /**
     * Copy.
     *
     * @param source
     *            the source
     * @param target
     *            the target
     */
    public static void copy(Object source, Object target) {
        String beanKey = generateKey(source.getClass(), target.getClass());
        BeanCopier copier;
        if (!beanCopierMap.containsKey(beanKey)) {
            copier = BeanCopier.create(source.getClass(), target.getClass(), false);
            beanCopierMap.put(beanKey, copier);
        } else {
            copier = beanCopierMap.get(beanKey);
        }
        copier.copy(source, target, null);
    }

    /**
     *
     * copyForSameFieldName:(按照相同的域名 copy对象). <br/>
     *
     * @author zhichao.zhu
     * @param source
     * @param target
     * @since JDK 1.7
     */
    @SuppressWarnings("unused")
    public static <S, T> boolean copyForSameFieldName(Object source, Object target) {
        if(source == null || target == null){
            return false;
        }

/*		Class<S> sClass = (Class<S>)source.getClass();
		Class<T> tClass = (Class<T>)target.getClass();*/
        if(source.getClass().toString().equals(target.getClass().toString()) ){
            return true;
        }
//		sFields.
        return true;
    }

    /**
     * Copy list list.
     *
     * @param <S>
     *            the type parameter
     * @param <T>
     *            the type parameter
     * @param source
     *            the source
     * @param cls
     *            the cls
     * @return the list
     * @throws InstantiationException
     *             the instantiation exception
     * @throws IllegalAccessException
     *             the illegal access exception
     */
    public static <S, T> List<T> copyList(List<S> source, Class<T> cls)
            throws InstantiationException, IllegalAccessException {
        List<T> target = new ArrayList<>();
        if (null != source && source.size() > 0) {
            for (S s : source) {
                T t = cls.newInstance();
                copy(s, t);
                target.add(t);
            }
        }
        return target;
    }

    /**
     * Generate key string.
     *
     * @param classSource
     *            the class source
     * @param classTarget
     *            the class target
     * @return the string
     */
    private static String generateKey(Class<?> classSource, Class<?> classTarget) {
        return classSource.toString() + classTarget.toString();
    }
}
