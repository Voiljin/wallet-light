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

            LocalDateTime now = LocalDateTime.now();
            
            if (entity.isNew()) {
                entity.setCreatedDate(now);
                entity.setModifiedDate(null);
                entity.setNew(false);
            } else {
                entity.setModifiedDate(now);
            }
            
            return Mono.just(entity);
        };
    }
}
