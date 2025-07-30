package com.wallet.walletapi.domain.event;

import com.wallet.common.domain.DomainEvent;
import com.wallet.walletapi.entity.Wallet;
import lombok.Getter;

@Getter
public class WalletCreatedEvent extends DomainEvent {
    
    private final Wallet wallet;
    
    public WalletCreatedEvent(Wallet wallet) {
        this.wallet = wallet;
    }
}