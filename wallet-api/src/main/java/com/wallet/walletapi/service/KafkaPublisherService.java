package com.wallet.walletapi.service;

import com.wallet.common.kafka.KafkaTopics;
import com.wallet.common.kafka.message.WalletCreatedMessage;
import com.wallet.walletapi.domain.event.WalletCreatedEvent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class KafkaPublisherService {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final EventMappingService eventMappingService;

    public KafkaPublisherService(KafkaTemplate<String, Object> kafkaTemplate, 
                                EventMappingService eventMappingService) {
        this.kafkaTemplate = kafkaTemplate;
        this.eventMappingService = eventMappingService;
    }

    public void publishWalletCreatedEvent(WalletCreatedEvent event) {
        WalletCreatedMessage message = eventMappingService.mapToWalletCreatedKafkaMessage(event);
        
        log.info("=== KafkaPublisherService: Publishing WalletCreatedMessage to topic '{}' ===", KafkaTopics.WALLET_CREATED);
        log.info("=== KafkaPublisherService: Message: {} ===", message);
        
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(KafkaTopics.WALLET_CREATED, message);
        
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("=== KafkaPublisherService: Message sent successfully to topic: {} ===", result.getRecordMetadata().topic());
            } else {
                log.error("=== KafkaPublisherService: Failed to send message to topic: {} ===", KafkaTopics.WALLET_CREATED, ex);
            }
        });
    }
}