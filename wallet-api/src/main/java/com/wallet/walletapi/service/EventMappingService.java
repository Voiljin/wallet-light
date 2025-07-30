package com.wallet.walletapi.service;

import com.wallet.common.kafka.message.WalletCreatedMessage;
import com.wallet.walletapi.domain.event.WalletCreatedEvent;
import com.wallet.walletapi.entity.Wallet;
import org.springframework.stereotype.Service;

@Service
public class EventMappingService {
    
    public WalletCreatedMessage mapToWalletCreatedKafkaMessage(WalletCreatedEvent event) {
        Wallet wallet = event.getWallet();
        
        return new WalletCreatedMessage(
            wallet.getId(),
            wallet.getCustomerId(),
            wallet.getCashAmount(),
            wallet.getEmail(),
            wallet.getGsmNumber(),
            wallet.getIsActive(),
            event.getOccurredOn()
        );
    }
}