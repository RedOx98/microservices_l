package com.olahammed.clients.notification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class NotificationPayloadDTO {

    private Integer customerId;

    private Integer fraudCheckId;

    private String firstName;

    private String lastName;

    private String customerEmail;
}