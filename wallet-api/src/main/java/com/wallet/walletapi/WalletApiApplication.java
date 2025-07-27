package com.wallet.walletapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.wallet.walletapi", "com.wallet.common"})
public class WalletApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(WalletApiApplication.class, args);
    }
}