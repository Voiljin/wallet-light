package com.wallet.common.domain;

import reactor.core.publisher.Mono;

public interface DomainEventPublisher {
    <T extends DomainEvent> Mono<Void> publish(T event);
}
