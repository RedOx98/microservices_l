package com.olahammed.notification.kafka;

import com.olahammed.clients.notification.NotificationPayloadDTO;
import com.olahammed.notification.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class KafkaConsumer {
    private final NotificationService notificationService;
    @KafkaListener(
            topics = "olaidescode",
            groupId = "foo"
            ,containerFactory = "messageFactory"
    )
    void listener(NotificationPayloadDTO payload) { //Change this String to the Message Object
        System.out.println("Listener received: " + payload + " !!!");
        log.info("Consumed {} from queue", payload);
        notificationService.notification(payload);
    }
}
