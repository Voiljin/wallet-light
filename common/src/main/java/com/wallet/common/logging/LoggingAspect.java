package com.wallet.common.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(* *..handler..*.handle(..))")
    public void handlerHandleMethods(){}
 
    @Before("handlerHandleMethods()")
    public void writeHandlerEntry(JoinPoint joinPoint){
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());

        logger.info("[{}] {}.{} - Request: {}",
            LogType.METHOD_ENTRY,
            joinPoint.getTarget().getClass().getSimpleName(),
            joinPoint.getSignature().getName(),
            joinPoint.getArgs()
        );
    }

    @AfterReturning(pointcut = "handlerHandleMethods()", returning = "result")
    public void writeHandlerExit(JoinPoint joinPoint, Object result) {
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        logger.info("[{}] {}.{} - Response: {}", 
            LogType.METHOD_EXIT,
            joinPoint.getTarget().getClass().getSimpleName(),
            joinPoint.getSignature().getName(),
            result);
    }

    @AfterThrowing(pointcut = "handlerHandleMethods()", throwing = "ex")
    public void writeHandlerException(JoinPoint joinPoint, Throwable ex) {
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        logger.error("[{}] {}.{} - Exception: {}", 
            LogType.EXCEPTION,
            joinPoint.getTarget().getClass().getSimpleName(),
            joinPoint.getSignature().getName(),
            ex.getMessage(), ex);
    }



    @Pointcut("execution(* com.wallet.common.http.CommunicatorBase+.*(..)) && (execution(* *.get(..)) || execution(* *.post(..)) || execution(* *.put(..)) || execution(* *.delete(..)))")
    public void communicatorHttpMethods(){}

    @Before("communicatorHttpMethods()")
    public void writeCommunicatorEntry(JoinPoint joinPoint) {
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        logger.info("[HTTP-REQUEST] {}.{} - Request: {}", 
            joinPoint.getTarget().getClass().getSimpleName(),
            joinPoint.getSignature().getName(),
            joinPoint.getArgs());
    }

    @AfterReturning(pointcut = "communicatorHttpMethods()", returning = "result")
    public void writeCommunicatorExit(JoinPoint joinPoint, Object result) {
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        logger.info("[HTTP-RESPONSE] {}.{} - Response: {}", 
            joinPoint.getTarget().getClass().getSimpleName(),
            joinPoint.getSignature().getName(),
            result);
    }

    @AfterThrowing(pointcut = "communicatorHttpMethods()", throwing = "ex")
    public void writeCommunicatorException(JoinPoint joinPoint, Throwable ex) {
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        logger.error("[HTTP-EXCEPTION] {}.{} - Exception: {}", 
            joinPoint.getTarget().getClass().getSimpleName(),
            joinPoint.getSignature().getName(),
            ex.getMessage(), ex);

    }
}

