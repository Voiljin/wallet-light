package com.wallet.common.contract;

import lombok.Data;

@Data
public class ResponseBase {
    private String message;
    private String messageCode;
    private boolean success;
}
