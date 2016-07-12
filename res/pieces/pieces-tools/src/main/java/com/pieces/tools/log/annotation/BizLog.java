package com.pieces.tools.log.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by kevin1 on 7/11/16.
 * 业务日志注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface BizLog {
    /**
     * 注解是否生效
     * @return
     */
    boolean record() default true;

    /**
     * 业务类型
     * @return
     */
    String type();

    /**
     * 业务描述
     * @return
     */
    String desc() default "";

}
