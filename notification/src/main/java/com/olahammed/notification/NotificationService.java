package com.olahammed.notification;

import com.olahammed.clients.notification.NotificationPayloadDTO;
import com.olahammed.clients.notification.NotificationResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

public NotificationResponseDTO notification(NotificationPayloadDTO payload){
    String message = "Fraud check has been completed for customer";


    message += payload.getCustomerId();
    message += "with name ";
    message += payload.getFirstName() + " ";
    message += payload.getLastName();

    Notification  notify = Notification.builder()
            .customerId(payload.getCustomerId())
            .fraudCheckId(payload.getFraudCheckId())
            .message(message)
            .firstName(payload.getFirstName())
            .lastName(payload.getLastName())
            .sentAt(LocalDateTime.now())
            .toCustomerEmail(payload.getCustomerEmail())
            .sender("OLAHAMMED")
            .status("UNREAD")
            .build();

    notificationRepository.save(
            notify
    );

    NotificationResponseDTO notification = new NotificationResponseDTO(
            notify.getCustomerId(),
            notify.getFraudCheckId(),
            message,notify.getStatus().toString(),
            notify.fullName()
    );
    return notification;
}
}
