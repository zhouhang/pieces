package com.pieces.tools.log.internal;

import com.pieces.tools.log.pojo.LogInfo;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Created by kevin1 on 7/11/16.
 */
public interface ILogInternalFacade {

    boolean isEnabled();

    /**
     *根据传进来的信息构造Log 并保存.
     * @param joinPoint
     * @param returnValueOfMethodCall
     * @param throwable
     * @param costTme
     * @return
     */
    LogInfo handleForMethodCall(ProceedingJoinPoint joinPoint, Object returnValueOfMethodCall,
                                Throwable throwable, long costTme);

    /**
     *初始化方法
     */
    void init();

    /**
     * 析构函数
     */
    void destroy();
}
