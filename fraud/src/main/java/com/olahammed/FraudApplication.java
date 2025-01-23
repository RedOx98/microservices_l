package com.olahammed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@EnableFeignClients(
        basePackages = "com.olahammed.clients"
)
@PropertySources(
        {@PropertySource("classpath:clients-${spring.profiles.active}.properties")}
)
@EnableEurekaClient
public class FraudApplication {
    public static void main(String args[]){
        SpringApplication.run(FraudApplication.class, args);
    }
}
