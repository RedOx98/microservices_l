package com.olahammed.notification.rabbitmq;

import com.olahammed.clients.notification.NotificationPayloadDTO;
import com.olahammed.notification.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class NotificationConsumer {
    private final NotificationService notificationService;

    @RabbitListener(queues = "${rabbitmq.queues.notification}")
    public void consumer(NotificationPayloadDTO payload){
        log.info("Consumed {} from queue", payload);
        notificationService.notification(payload);
    }
}
