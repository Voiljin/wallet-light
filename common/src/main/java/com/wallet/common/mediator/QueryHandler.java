package com.wallet.common.mediator;

public interface QueryHandler<Q, R> {
    R handle(Q query);
}
