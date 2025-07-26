package com.wallet.common.mediator;

import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

@Component
public class SimpleMediator implements Mediator {

    private final ApplicationContext context;

    @Autowired
    public SimpleMediator(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public <C, R> Mono<R> send(C commandOrQuery) {

        // Önce CommandHandler'ları tara
        Map<String, CommandHandler> commandHandlers = context.getBeansOfType(CommandHandler.class);
        for (CommandHandler handler : commandHandlers.values()) {

            Class<?> handledType = getHandlerGenericType(handler, CommandHandler.class);

            if (handledType != null && handledType.isAssignableFrom(commandOrQuery.getClass())) {
                
                Object result = handler.handle(commandOrQuery);
                
                // Eğer result zaten Mono ise, direkt döndür
                if (result instanceof Mono) {
                    return (Mono<R>) result;
                }
                
                // Eğer result Mono değilse, Mono.just ile sar
                return Mono.just((R) result);
            }
        }

        // Sonra QueryHandler'ları tara
        Map<String, QueryHandler> queryHandlers = context.getBeansOfType(QueryHandler.class);
        for (QueryHandler handler : queryHandlers.values()) {

            Class<?> handledType = getHandlerGenericType(handler, QueryHandler.class);

            if (handledType != null && handledType.isAssignableFrom(commandOrQuery.getClass())) {

                Object result = handler.handle(commandOrQuery);
                
                // Eğer result zaten Mono ise, direkt döndür
                if (result instanceof Mono) {
                    return (Mono<R>) result;
                }
                
                // Eğer result Mono değilse, Mono.just ile sar
                return Mono.just((R) result);
            }
        }

        return Mono.error(new IllegalArgumentException("No suitable handler found for " + commandOrQuery.getClass().getName()));
    }

    // Handler'ın generic parametresini bulur
    private Class<?> getHandlerGenericType(Object handler, Class<?> handlerInterface) {
        Class<?> targetClass = AopProxyUtils.ultimateTargetClass(handler);
    
        // 1. Interface'leri tara
        for (Type iface : targetClass.getGenericInterfaces()) {
            if (iface instanceof ParameterizedType) {
                ParameterizedType pt = (ParameterizedType) iface;
                if (pt.getRawType().equals(handlerInterface)) {
                    Type typeArg = pt.getActualTypeArguments()[0];
                    if (typeArg instanceof Class) {
                        return (Class<?>) typeArg;
                    }
                }
            }
        }
    
        // 2. Superclass'ı tara
        Type superClass = targetClass.getGenericSuperclass();
        if (superClass instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) superClass;
            if (pt.getRawType().equals(handlerInterface)) {
                Type typeArg = pt.getActualTypeArguments()[0];
                if (typeArg instanceof Class) {
                    return (Class<?>) typeArg;
                }
            }
        }
        return null;
    }
}
