package com.wallet.walletapi.contract.request.command;

import lombok.Data;

@Data
public class CreateWalletCommand {

    private String userId;
    private String currency;
}
