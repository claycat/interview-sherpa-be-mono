package com.sherpa.interviewsherpa.auth.application.service.oauth;

public enum OAuthProvider {
    GOOGLE("google"),
    GITHUB("github");

    private final String providerId;

    OAuthProvider(String providerId) {
        this.providerId = providerId;
    }

    public String getProviderId() {
        return providerId;
    }

    public static OAuthProvider fromValue(String value) {
        for (OAuthProvider provider : values()) {
            if (provider.getProviderId().equalsIgnoreCase(value)) {
                return provider;
            }
        }
        throw new IllegalArgumentException("No OAuthProvider available for value: " + value);
    }
}