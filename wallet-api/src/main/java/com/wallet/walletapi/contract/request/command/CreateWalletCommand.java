package com.wallet.walletapi.contract.request.command;

import java.util.UUID;

import lombok.Data;

@Data
public class CreateWalletCommand {

    private UUID customerId;
    private String email;
    private String gsmNumber;
}
