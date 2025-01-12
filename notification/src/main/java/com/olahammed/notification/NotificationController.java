package com.olahammed.notification;

import com.olahammed.clients.notification.NotificationPayloadDTO;
import com.olahammed.clients.notification.NotificationResponseDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/notification")
@AllArgsConstructor
public class NotificationController {
    private NotificationService notificationService;

    @PostMapping(value = "/send-notification")
    public NotificationResponseDTO sendNotification(@RequestBody NotificationPayloadDTO payloadDTO){
        NotificationResponseDTO notification = notificationService.notification(payloadDTO);
        log.info("notification has been sent to customer {}",
                payloadDTO.getCustomerId()," with name ", payloadDTO.getFirstName(), " ", payloadDTO.getLastName());
        return notification;
    }
}
