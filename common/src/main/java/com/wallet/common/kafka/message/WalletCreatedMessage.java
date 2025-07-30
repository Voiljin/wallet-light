package com.wallet.common.kafka.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalletCreatedMessage {
    private UUID walletId;
    private UUID customerId;
    private BigDecimal cashAmount;
    private String email;
    private String gsmNumber;
    private Boolean isActive;
    private LocalDateTime createdAt;
}