package com.pieces.tools.log.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;

/**
 * Created by kevin1 on 7/11/16.
 */
public class CommonsUtils {

    public static String getRawClassName(Object object) {
        if(object != null) {
            if(!AopUtils.isAopProxy(object)) {
                if(AopUtils.isCglibProxyClass(object.getClass())) {
                    return AopUtils.getTargetClass(object).getSimpleName();
                }

                return object.getClass().getSimpleName();
            }

            String v = AopUtils.getTargetClass(object).getSimpleName();
            if(!v.contains("$Proxy") && !v.contains("$$")) {
                return v;
            }

            try {
                Object o = ((Advised)object).getTargetSource().getTarget();
                return getRawClassName(o);
            } catch (Throwable var3) {
                ;
            }
        }

        return "UnknowClass";
    }

    public static String getMethodNameWithClassName(String className, String methodName) {
        StringBuilder sb = new StringBuilder();
        if(StringUtils.isNotBlank(className)) {
            sb.append(className);
            sb.append(".");
        }

        if(StringUtils.isNotBlank(methodName)) {
            sb.append(methodName);
        }

        return sb.toString();
    }

    public static String getStackTrace(Throwable throwable) {
        return  throwable.getMessage();
    }
}
