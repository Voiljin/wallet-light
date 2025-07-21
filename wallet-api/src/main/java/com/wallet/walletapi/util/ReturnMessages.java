package com.wallet.walletapi.util;

public enum ReturnMessages {
    SUCCESS("0000", "İşlem Başarılı!");












    private final String code;
    private final String message;

    ReturnMessages(String code, String message){
        this.code = code;
        this.message = message;
    }

    public String getCode(){ return code; }
    public String getMessage(){ return message; }
}
