package com.wallet.walletapi.domain.consumer;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.wallet.walletapi.domain.event.WalletCreatedEvent;

import reactor.core.publisher.Mono;

@Component
public class WalletCreatedEventConsumer {

    //TODOSK: Burayı da logging aspecte sokabiliriz dursun şimdilik

    @EventListener
    public Mono<Void> handleWalletCreated(WalletCreatedEvent event){
        
        //TODOSK: Notification API için kafka publish gelecek

        return Mono.empty();
    }
    
}
