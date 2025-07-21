package com.wallet.common.mediator;

public interface Mediator {
    <C, R> R send(C commandOrQuery);
}
