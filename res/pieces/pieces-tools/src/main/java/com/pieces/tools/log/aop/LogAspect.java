package com.pieces.tools.log.aop;

import com.pieces.tools.log.annotation.BizLog;
import com.pieces.tools.log.internal.ILogInternalFacade;
import com.pieces.tools.log.internal.LogInternalFacade;
import com.pieces.tools.log.pojo.LogInfo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * Created by kevin1 on 7/11/16.
 * 业务日志AOP
 */
public class LogAspect {

    private static Logger logger = LoggerFactory.getLogger(LogAspect.class);

    public LogAspect() {

    }

    public Object doAroundMethodCall(ProceedingJoinPoint ponint) throws Throwable {
        Boolean cut = false;

        Signature signature = ponint.getSignature();
        // HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 判断方法有没有BizLog注解
        if(signature instanceof MethodSignature) {
            MethodSignature returnValue = (MethodSignature)signature;
            Method aopMethod = returnValue.getMethod();
            BizLog bizLog = (BizLog)aopMethod.getAnnotation(BizLog.class);
            if(bizLog != null) {
                cut = true;
            }
        }

        if (!cut) {
            // 如果方法没有标注BizLog 注解不记录相关日志信息.
            return ponint.proceed();
        } else {
            LogInfo logInfo = null;
            Throwable caughtThrowable = null;
            Object returnValueOfMethodCall = null;
            long startTime = System.currentTimeMillis();

            // 执行切面方法捕获方法抛出的异常并抛出.
            try {
                returnValueOfMethodCall = ponint.proceed();
            } catch (Throwable throwable) {
                caughtThrowable = throwable;
                throw throwable;
            } finally {
                long costTime= System.currentTimeMillis() - startTime;
                try {
                    ILogInternalFacade logInternalFacade = LogInternalFacade.getInstance();
                    if (logInternalFacade.isEnabled()){
                        logInfo = logInternalFacade.handleForMethodCall(ponint, returnValueOfMethodCall,
                                caughtThrowable, costTime);
                    }

                    if(caughtThrowable != null) {
                        logger.error("LogAspect catched Throwable. Details: {}", caughtThrowable);
                    }
                } catch (Throwable throwable) {
                    logger.error("Error occurs when doAroundMethodCall method of LogAspect called.", throwable);
                }

            }
            return returnValueOfMethodCall;
        }

    }


    public void init() {
        LogInternalFacade.getInstance().init();
    }

    public void destroy() {
        LogInternalFacade.getInstance().destroy();
    }
}
