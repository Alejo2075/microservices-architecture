package org.alejo2075.paymentdispute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PaymentDisputeApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentDisputeApplication.class, args);
    }

}
