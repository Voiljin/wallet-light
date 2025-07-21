package com.wallet.walletapi.handler.command;

import com.wallet.common.contract.ResponseUtil;
import com.wallet.common.exception.CustomException;
import com.wallet.common.mediator.CommandHandler;
import com.wallet.walletapi.contract.request.command.CreateWalletCommand;
import com.wallet.walletapi.contract.response.command.CreateWalletCommandResult;
import com.wallet.walletapi.entity.Wallet;
import com.wallet.walletapi.repository.WalletRepository;
import com.wallet.walletapi.util.ReturnMessages;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class CreateWalletCommandHandler implements CommandHandler<CreateWalletCommand, CreateWalletCommandResult> {

    @Autowired
    private WalletRepository walletRepository;

    @Override
    public CreateWalletCommandResult handle(CreateWalletCommand command) {
        Specification<Wallet> spec = (root, query, cb) -> cb.or(
            cb.equal(root.get("email"), command.getEmail()),
            cb.equal(root.get("gsmNumber"), command.getGsmNumber())
        );

        Optional<Wallet> existingWallet = walletRepository.findOne(spec);

        if(existingWallet.isPresent()){
            throw new CustomException(ReturnMessages.WALLET_IS_EXIST.getCode(), ReturnMessages.WALLET_IS_EXIST.getMessage());
        }

        Wallet wallet = new Wallet(command.getCustomerId(), BigDecimal.ZERO, command.getEmail(), command.getGsmNumber(), true);

        walletRepository.save(wallet);
        return ResponseUtil.returnOk(new CreateWalletCommandResult());
    }
}
