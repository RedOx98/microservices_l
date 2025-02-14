package com.olahammed.notification;

import com.olahammed.amqp.RabbitMQMessageProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication(
        scanBasePackages = {
                "com.olahammed.notification",
                "com.olahammed.amqp",
                "com.olaidescode.kafka"
        }
)
@EnableFeignClients(
        basePackages = "com.olahammed.clients"
)
@EnableEurekaClient
@PropertySources(
        {@PropertySource("classpath:clients-${spring.profiles.active}.properties")}
)
public class NotificationApplication {
    public static void main(String args[]){
        SpringApplication.run(NotificationApplication.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(
//            RabbitMQMessageProducer producer,
//            NotificationConfig notificationConfig
//            ){
//        return args -> {
//            producer.publish(new Person("Olahammed", 28), notificationConfig.getInternalExchange(),
//                    notificationConfig.getInternalNotificationRoutingKey());
//        };
//    };
//
//    record Person(
//            String name, Integer age
//    ){}
}
