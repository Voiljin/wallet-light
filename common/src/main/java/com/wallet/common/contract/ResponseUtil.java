package com.wallet.common.contract;

public class ResponseUtil {
    public static <T extends ResponseBase> T returnOk(T response){
        response.setSuccess(true);
        response.setMessageCode("0000");
        response.setMessage("İşlem Başarılı!");

        return response;
    }
}
