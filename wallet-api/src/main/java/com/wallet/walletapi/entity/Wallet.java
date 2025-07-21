package com.wallet.walletapi.entity;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.AccessLevel;

@Getter
@Entity
@Table(name = "Wallet")
public class Wallet extends AggregateRoot {

    @Setter(AccessLevel.PROTECTED)
    @Column(name = "CustomerId", nullable = false)
    private UUID customerId;

    @Setter(AccessLevel.PROTECTED)
    @Column(name = "CashAmount", precision = 18, scale = 2, nullable = false)
    private BigDecimal cashAmount;

    @Setter(AccessLevel.PROTECTED)
    @Column(name = "Email", length = 100, nullable = false)
    private String email;

    @Setter(AccessLevel.PROTECTED)
    @Column(name = "GsmNumber", length = 12, nullable = false)
    private String gsmNumber;

    @Setter(AccessLevel.PROTECTED)
    @Column(name = "IsActive", nullable = false)
    private Boolean isActive;

    public Wallet(UUID customerId, BigDecimal cashAmount, String email, String gsmNumber, Boolean isActive){
        this.customerId = customerId;
        this.cashAmount = cashAmount;
        this.email = email;
        this.gsmNumber = gsmNumber;
        this.isActive = isActive;
    }
}
