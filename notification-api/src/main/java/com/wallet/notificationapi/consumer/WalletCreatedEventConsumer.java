package com.wallet.notificationapi.consumer;

import com.wallet.common.kafka.KafkaTopics;
import com.wallet.common.kafka.message.WalletCreatedMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class WalletCreatedEventConsumer {

    @KafkaListener(topics = KafkaTopics.WALLET_CREATED, groupId = "notification-api-group")
    public void consumeWalletCreatedMessage(WalletCreatedMessage message) {
        log.info("=== WalletCreatedEventConsumer: Received WalletCreatedMessage ===");
        log.info("=== WalletCreatedEventConsumer: Processing wallet creation for customer: {} ===", 
                message.getCustomerId());
        
        sendEmailNotification(message.getEmail(), message.getCustomerId());
        sendSmsNotification(message.getGsmNumber(), message.getCustomerId());
    }
    
    private void sendEmailNotification(String email, UUID customerId) {
        log.info("=== WalletCreatedEventConsumer: Sending email notification to: {} ===", email);
        //TODOSK: Gerçek email servisi entegrasyonu
    }
    
    private void sendSmsNotification(String gsmNumber, UUID customerId) {
        log.info("=== WalletCreatedEventConsumer: Sending SMS notification to: {} ===", gsmNumber);
        //TODOSK: Gerçek SMS servisi entegrasyonu
    }
}