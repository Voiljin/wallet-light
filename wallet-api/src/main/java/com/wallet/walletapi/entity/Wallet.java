package com.wallet.walletapi.entity;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.AccessLevel;

@Getter
@Table("wallet")
public class Wallet extends AggregateRoot {

    @Setter(AccessLevel.PROTECTED)
    @Column("customer_id")
    private UUID customerId;

    @Setter(AccessLevel.PROTECTED)
    @Column("cash_amount")
    private BigDecimal cashAmount;

    @Setter(AccessLevel.PROTECTED)
    @Column("email")
    private String email;

    @Setter(AccessLevel.PROTECTED)
    @Column("gsm_number")
    private String gsmNumber;

    @Setter(AccessLevel.PROTECTED)
    @Column("is_active")
    private Boolean isActive;

    public Wallet() {

    }

    public Wallet(UUID customerId, BigDecimal cashAmount, String email, String gsmNumber, Boolean isActive){
        this.customerId = customerId;
        this.cashAmount = cashAmount;
        this.email = email;
        this.gsmNumber = gsmNumber;
        this.isActive = isActive;
    }
}
