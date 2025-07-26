package com.wallet.common.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(* *..handler..*.handle(..))")
    public void handlerHandleMethods(){}
 
    // Handler'lar için reactive logging
    @Around("handlerHandleMethods()")
    public Object logHandlerAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        
        // Entry log
        logger.info("[{}] {}.{} - Request: {}",
            LogType.METHOD_ENTRY,
            joinPoint.getTarget().getClass().getSimpleName(),
            joinPoint.getSignature().getName(),
            joinPoint.getArgs()
        );

        // Method execution
        Object result = joinPoint.proceed();

        // Sadece reactive response handling
        if (result instanceof Mono) {
            return ((Mono<?>) result)
                .doOnSuccess(response -> 
                    logger.info("[{}] {}.{} - Response: {}",
                        LogType.METHOD_EXIT,
                        joinPoint.getTarget().getClass().getSimpleName(),
                        joinPoint.getSignature().getName(),
                        response
                    )
                )
                .doOnError(error -> 
                    logger.error("[{}] {}.{} - Exception: {}",
                        LogType.EXCEPTION,
                        joinPoint.getTarget().getClass().getSimpleName(),
                        joinPoint.getSignature().getName(),
                        error.getMessage(), error
                    )
                );
        }

        // Senkron response varsa hata fırlat
        throw new IllegalStateException("Handler must return Mono<T>, but returned: " + result.getClass().getSimpleName());
    }


    @Pointcut("execution(* com.wallet.common.http.CommunicatorBase+.*(..)) && (execution(* *.get(..)) || execution(* *.post(..)) || execution(* *.put(..)) || execution(* *.delete(..)))")
    public void communicatorHttpMethods(){}

    // Communicator'lar için reactive logging
    @Around("communicatorHttpMethods()")
    public Object logCommunicatorAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        
        // Entry log
        logger.info("[HTTP-REQUEST] {}.{} - Request: {}", 
            joinPoint.getTarget().getClass().getSimpleName(),
            joinPoint.getSignature().getName(),
            joinPoint.getArgs());

        // Method execution
        Object result = joinPoint.proceed();

        // Sadece reactive response handling
        if (result instanceof Mono) {
            return ((Mono<?>) result)
                .doOnSuccess(response -> 
                    logger.info("[HTTP-RESPONSE] {}.{} - Response: {}", 
                        joinPoint.getTarget().getClass().getSimpleName(),
                        joinPoint.getSignature().getName(),
                        response)
                )
                .doOnError(error -> 
                    logger.error("[HTTP-EXCEPTION] {}.{} - Exception: {}", 
                        joinPoint.getTarget().getClass().getSimpleName(),
                        joinPoint.getSignature().getName(),
                        error.getMessage(), error)
                );
        }

        // Senkron response varsa hata fırlat
        throw new IllegalStateException("Communicator must return Mono<T>, but returned: " + result.getClass().getSimpleName());
    }
}

