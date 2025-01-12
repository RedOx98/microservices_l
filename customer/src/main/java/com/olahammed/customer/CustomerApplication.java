package com.olahammed.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(
        scanBasePackages = {
                "com.olahammed.customer",
                "com.olahammed.amqp"
        }
)
@EnableFeignClients(
        basePackages = "com.olahammed.clients"
)
@EnableEurekaClient
public class CustomerApplication {
    public static void main(String args[]){
        SpringApplication.run(CustomerApplication.class, args);
    }
}
