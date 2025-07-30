package com.wallet.paymentapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.wallet.paymentapi", "com.wallet.common"})
public class PaymentApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(PaymentApiApplication.class, args);
    }
}