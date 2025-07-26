package com.wallet.walletapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.wallet.common.contract.ResponseBase;
import com.wallet.common.exception.CustomException;

import reactor.core.publisher.Mono;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(CustomException.class)
    public Mono<ResponseEntity<ResponseBase>> handleCustomException(CustomException ex){
        ResponseBase response = new ResponseBase();
        response.setSuccess(false);
        response.setMessageCode(ex.getCode());
        response.setMessage(ex.getMessage());
        return Mono.just(new ResponseEntity<>(response, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<ResponseBase>> handleGeneralException(Exception ex) {
        ResponseBase response = new ResponseBase();
        response.setSuccess(false);
        response.setMessageCode("-1");
        response.setMessage("Bilinmeyen bir hata oluştu: " + ex.getMessage());
        return Mono.just(new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR));
    }
}
