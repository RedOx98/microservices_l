package com.olahammed.apigw.security;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class FakeApiAuthorizationChecker implements ApiKeyAuthorizationChecker{

    private final static Map<String, List<String>> keys = Map.of("supersecure", List.of("fraud")); //map the keys to applications

    @Override
    public boolean isAuthorized(String key, String application) {
        return keys.getOrDefault(key, List.of())
                .stream()
                .anyMatch(k -> k.contains(application));
    }
}
