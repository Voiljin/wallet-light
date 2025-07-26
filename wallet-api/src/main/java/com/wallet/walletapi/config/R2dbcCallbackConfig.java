package com.wallet.walletapi.config;

import java.time.LocalDateTime;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.mapping.event.BeforeConvertCallback;

import com.wallet.walletapi.entity.BaseEntity;

import reactor.core.publisher.Mono;

@Configuration
public class R2dbcCallbackConfig {
    
    @Bean
    public BeforeConvertCallback<BaseEntity> beforeConvertCallback() {
        return (entity, table) -> {
            System.out.println("=== R2dbcCallbackConfig: BeforeConvertCallback called for " + entity.getClass().getSimpleName() + " ===");
            
            LocalDateTime now = LocalDateTime.now();
            
            if (entity.isNew()) {
                System.out.println("=== Setting audit fields for NEW entity ===");
                entity.setCreatedDate(now);
                entity.setModifiedDate(null);
                entity.setNew(false);
            } else {
                System.out.println("=== Setting audit fields for EXISTING entity ===");
                entity.setModifiedDate(now);
            }
            
            return Mono.just(entity);
        };
    }
}
