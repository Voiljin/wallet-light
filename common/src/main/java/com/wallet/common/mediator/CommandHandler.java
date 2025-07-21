package com.wallet.common.mediator;

public interface CommandHandler<C, R> {
    R handle(C command);
}
