package com.wallet.walletapi.domain.event;

import com.wallet.common.domain.DomainEvent;
import com.wallet.walletapi.entity.Wallet;

import java.math.BigDecimal;
import java.util.UUID;

public class WalletCreatedEvent extends DomainEvent {
    private final UUID walletId;
    private final UUID customerId;
    private final String email;
    private final String gsmNumber;
    private final BigDecimal cashAmount;

    public WalletCreatedEvent(Wallet wallet){
        this.walletId = wallet.getId();
        this.customerId = wallet.getCustomerId();
        this.email = wallet.getEmail();
        this.gsmNumber = wallet.getGsmNumber();
        this.cashAmount = wallet.getCashAmount();
    }

    public UUID getWalletId() {
        return walletId;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public String getEmail() {
        return email;
    }

    public String getGsmNumber() {
        return gsmNumber;
    }

    public BigDecimal getCashAmount() {
        return cashAmount;
    }
}
