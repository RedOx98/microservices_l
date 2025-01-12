package com.olahammed.clients.notification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class NotificationResponseDTO {
    private Integer customerId;

    private Integer fraudCheckId;

    private String message;

    private String status;

    private String fullName;
}
