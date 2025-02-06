package com.olahammed.kafka.config;

public record NotificationPayloadDTO(
//        String firstName,
//        String lastName,
//        String email
        Integer customerId,

        Integer fraudCheckId,

        String firstName,

        String lastName,

        String customerEmail
) {

}
