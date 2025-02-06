package com.olahammed.apigw.security;

import org.springframework.context.annotation.Bean;

public interface ApiKeyAuthorizationChecker {

    boolean isAuthorized(String apiKey, String application);
}
