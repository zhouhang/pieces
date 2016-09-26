package com.pieces.tools.log.internal;


import com.pieces.tools.log.annotation.BizLog;
import com.pieces.tools.log.api.LogConfig;
import com.pieces.tools.log.api.LogUser;
import com.pieces.tools.log.pojo.LogInfo;
import com.pieces.tools.log.util.CommonsUtils;
import com.pieces.tools.log.util.JSONUtils;
import com.pieces.tools.log.util.SystemUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by kevin1 on 7/11/16.
 * 日志信息处理内部类
 */
public class LogInternalFacade implements ILogInternalFacade{

    private static final Integer SUCCESSED_FAIL = -1;
    private static final Integer SUCCESSED_SUCCESS = 0;

    private static Logger logger = LoggerFactory.getLogger(LogInternalFacade.class);


    public static ILogInternalFacade getInstance(){
        return new LogInternalFacade();
    }

    public boolean isEnabled() {
        return true;
    }

    public LogInfo handleForMethodCall(ProceedingJoinPoint joinPoint, Object returnValueOfMethodCall, Throwable throwable, long costTime) {
        LogInfo logInfo = null;
        long startHandleTime = System.currentTimeMillis();

        try {
            logInfo = getLogInfoForMethodCall(joinPoint, returnValueOfMethodCall, throwable, costTime);

            // 设置方法注解信息
            logInfo.setBizDesc(logInfo.getBiz().desc());
            logInfo.setBizType(logInfo.getBiz().type());
            LogUser user = LogConfig.user.getLogUser();

            // 设置登入用户信息
            if (user != null) {
                logInfo.setUserId(user.getUserId());
                logInfo.setUserName(user.getUserName());
            }
            // 将日志信息保存
            LogHandle.handle(logInfo);
        } catch (Exception err) {
            logger.error("Error occurs when handle for method call.", err);
        } finally {
            if(logger.isDebugEnabled()) {
                long handleCostTime = System.currentTimeMillis() - startHandleTime;
                logger.debug("It takes " + handleCostTime + " milliseconds for handleForMethodCall. caughtThrowable=" + (throwable == null?"null":throwable.getMessage()) + ",costTime=" + costTime + " milliseconds.");
            }

        }

        return logInfo;
    }

    public synchronized void init() {
        //TODO:
    }

    public synchronized void destroy() {
        //TODO:
    }


    private LogInfo getLogInfoForMethodCall(ProceedingJoinPoint joinPoint, Object returnValueOfMethodCall, Throwable throwable, long costTme) {
        LogInfo logInfo = new LogInfo();


        Date now = new Date();
        logInfo.setLogTime(now);
        String localhostIp = SystemUtil.getLocalhostIp();
        logInfo.setAppHost(localhostIp);

        String globalId;
        if (throwable != null) {
            globalId = CommonsUtils.getRawClassName(throwable);
            logInfo.setExceptionClassname(globalId);
            String exceptionDesc = CommonsUtils.getStackTrace(throwable);
            logInfo.setExceptionDesc(exceptionDesc);
            logInfo.setSuccessed(SUCCESSED_FAIL);
        } else {
            logInfo.setSuccessed(SUCCESSED_SUCCESS);
        }

        logInfo.setCostTime(Integer.valueOf(Long.valueOf(costTme).intValue()));

        String className = CommonsUtils.getRawClassName(joinPoint.getTarget());
        logInfo.setActionClassname(className);
        String methodSignature = this.getSignatureSimpleDesc(logInfo, className, joinPoint);
        logInfo.setActionMethod(methodSignature);
        Object[] args = joinPoint.getArgs();

        if (args != null && args.length > 0) {
            String argsAsString = this.getInParamAsString(args);
            logInfo.setInParam(argsAsString);
        }

        if (returnValueOfMethodCall != null) {
            String returnValueOfMethodCallString = this.getOutParamAsString(returnValueOfMethodCall);
        logInfo.setOutParam(returnValueOfMethodCallString);
        }


        return logInfo;
    }

    private String getSignatureSimpleDesc(LogInfo logInfo, String className, ProceedingJoinPoint pjp) {
        StringBuilder sb = new StringBuilder();
        Signature signature = pjp.getSignature();
        if(signature instanceof MethodSignature) {
            MethodSignature methodSignature = (MethodSignature)signature;
            Method method = methodSignature.getMethod();
            BizLog blog = (BizLog)method.getAnnotation(BizLog.class);
            if(blog != null) {
                logInfo.setBiz(blog);
            }

            String methodNameWithClassName = CommonsUtils.getMethodNameWithClassName(className, method.getName());
            sb.append(methodNameWithClassName);
        } else {
            sb.append(signature.toLongString());
        }

        return sb.toString();
    }


    /**
     * 获取参数信息
     * @param inParam
     * @return
     */
    private String getInParamAsString(Object[] inParam) {
        String returnValue = null;
        if(ArrayUtils.isNotEmpty(inParam)) {
            StringBuilder sb = new StringBuilder();
            HashMap paramMap = new HashMap();
            int argsLength = inParam.length;

            for(int argsAsString = 0; argsAsString < argsLength; ++argsAsString) {
                if(inParam[argsAsString] != null) {
                    String name = inParam[argsAsString].getClass().toString();
                    if (!(name.contains("Servlet") || name.contains("ModelMap"))) {
                        paramMap.put("param[" + argsAsString + "]", JSONUtils.toJson(inParam[argsAsString]));
                    }

                } else {
                    paramMap.put("param[" + argsAsString + "]", "null");
                }
            }

            String params = JSONUtils.toJson(paramMap);
            sb.append(params);
            returnValue = sb.toString();
        }

        return returnValue;
    }

    /**
     * 获取输出参数信息
     * @param outParam
     * @return
     */
    private String getOutParamAsString(Object outParam) {
        String returnValue = null;
        if(outParam != null) {
            returnValue = JSONUtils.toJson(outParam);
        }

        return returnValue;
    }
}
