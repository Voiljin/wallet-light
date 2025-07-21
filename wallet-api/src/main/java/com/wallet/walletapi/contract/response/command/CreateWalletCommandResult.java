package com.wallet.walletapi.contract.response.command;

import com.wallet.common.contract.ResponseBase;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class CreateWalletCommandResult extends ResponseBase {
    private String walletId;
    private String userId;
    private String currency;
    private double balance;
}
