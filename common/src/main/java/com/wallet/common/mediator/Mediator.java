package com.wallet.common.mediator;

import reactor.core.publisher.Mono;

public interface Mediator {
    <C, R> Mono<R> send(C commandOrQuery);
}
