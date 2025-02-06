package com.olahammed.kafka.config;

//import com.olahammed.customer.CustomerRegistrationRequest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {
    @KafkaListener(
            topics = "olaidescode",
            groupId="foo",
            containerFactory = "messageFactory" //dont use this part when you're configuring for a String value
    )
    void listener(NotificationPayloadDTO data){
        System.out.println("Listener received: " + data + " !!!");
    }
}
