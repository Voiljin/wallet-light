package com.wallet.notificationapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.wallet.notificationapi", "com.wallet.common"})
public class NotificationApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationApiApplication.class, args);
    }
}