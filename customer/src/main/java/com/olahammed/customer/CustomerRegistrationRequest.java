package com.olahammed.customer;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email
) {

}
