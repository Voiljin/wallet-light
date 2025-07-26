package com.wallet.walletapi.repository;

import java.util.UUID;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.wallet.walletapi.entity.Wallet;

import reactor.core.publisher.Mono;

public interface WalletRepository extends ReactiveCrudRepository<Wallet, UUID> {
    
    @Query("SELECT * FROM wallet WHERE email = :email OR gsm_number = :gsmNumber LIMIT 1")
    Mono<Wallet> findOneByEmailOrGsmNumber(String email, String gsmNumber);


}
