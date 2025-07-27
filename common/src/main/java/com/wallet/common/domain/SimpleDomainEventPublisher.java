package com.wallet.common.domain;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Component
public class SimpleDomainEventPublisher implements DomainEventPublisher {

    private final ApplicationEventPublisher eventPublisher;

    public SimpleDomainEventPublisher(ApplicationEventPublisher eventPublisher){
        this.eventPublisher = eventPublisher;
    }

    @Override
    public <T extends DomainEvent> Mono<Void> publish(T event){
        return Mono.fromRunnable(() -> eventPublisher.publishEvent(event)).then();
    }
}
