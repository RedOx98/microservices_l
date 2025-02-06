package com.olahammed.kafka;


import com.olahammed.kafka.config.NotificationPayloadDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
//        (
//        scanBasePackages = {
//                "com.olahammed.customer"
//        }
//)
public class KafkaApplication {
    public static void main(String[] args) {
        SpringApplication.run(KafkaApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(KafkaTemplate<String, NotificationPayloadDTO> kafkaTemplate){ //Change this from STring to Message Object too
        return args -> {
            for (int i = 0; i<100; i++){
//			kafkaTemplate.send("olaidescode", "hello kafka :)");
                kafkaTemplate.send("olaidescode", new NotificationPayloadDTO(1, 2, "olaide", "hammed", "olaskeet@gmail.com"));
            }
//                kafkaTemplate.send("olaidescode", new Message("Olaide triggers kafka :) " + i, LocalDateTime.now()));
            };
        };
}
