package com.wallet.walletapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.common.mediator.Mediator;
import com.wallet.walletapi.contract.request.command.CreateWalletCommand;
import com.wallet.walletapi.contract.response.command.CreateWalletCommandResult;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    private final Mediator mediator;

    @Autowired
    public WalletController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    public CreateWalletCommandResult createWallet(@RequestBody CreateWalletCommand command) {
        return mediator.send(command);
    }

    
}
