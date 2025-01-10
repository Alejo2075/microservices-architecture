package org.alejo2075.paymentauhorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PaymentAuhorizationApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentAuhorizationApplication.class, args);
    }

}
