package com.wallet.walletapi.domain.consumer;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import com.wallet.walletapi.domain.event.WalletCreatedEvent;
import com.wallet.walletapi.service.KafkaPublisherService;
import reactor.core.publisher.Mono;

@Component
public class WalletCreatedEventConsumer {

    private final KafkaPublisherService kafkaPublisherService;

    public WalletCreatedEventConsumer(KafkaPublisherService kafkaPublisherService) {
        this.kafkaPublisherService = kafkaPublisherService;
    }

    @EventListener
    public Mono<Void> handleWalletCreated(WalletCreatedEvent event) {
        // Domain event'i Kafka message'ına çevir ve gönder
        kafkaPublisherService.publishWalletCreatedEvent(event);
        return Mono.empty();
    }
}
