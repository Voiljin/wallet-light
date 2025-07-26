package com.wallet.walletapi.handler.command;

import com.wallet.common.contract.ResponseUtil;
import com.wallet.common.exception.CustomException;
import com.wallet.common.mediator.CommandHandler;
import com.wallet.walletapi.contract.request.command.CreateWalletCommand;
import com.wallet.walletapi.contract.response.command.CreateWalletCommandResult;
import com.wallet.walletapi.entity.Wallet;
import com.wallet.walletapi.repository.WalletRepository;
import com.wallet.walletapi.util.ReturnMessages;

import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateWalletCommandHandler implements CommandHandler<CreateWalletCommand, Mono<CreateWalletCommandResult>> {

    @Autowired
    private WalletRepository walletRepository;

    @Override
    public Mono<CreateWalletCommandResult> handle(CreateWalletCommand command) {
        
        return walletRepository.findOneByEmailOrGsmNumber(command.getEmail(), command.getGsmNumber())
            .flatMap(existingWallet ->
                Mono.<CreateWalletCommandResult> error(new CustomException(ReturnMessages.WALLET_IS_EXIST.getCode(), ReturnMessages.WALLET_IS_EXIST.getMessage()))
            )
            .switchIfEmpty(
                Mono.defer(() -> {
                    // Yeni wallet oluştur
                    Wallet wallet = new Wallet(
                        command.getCustomerId(), 
                        BigDecimal.ZERO, 
                        command.getEmail(), 
                        command.getGsmNumber(), 
                        true
                    );
                    
                    // EntityListener otomatik olarak:
                    // - ID = gen_random_uuid() (DB'de)
                    // - CreatedDate = now
                    // - ModifiedDate = null
                    return walletRepository.save(wallet)
                        .map(savedWallet -> ResponseUtil.returnOk(new CreateWalletCommandResult()));
                })
            );
    }
}
