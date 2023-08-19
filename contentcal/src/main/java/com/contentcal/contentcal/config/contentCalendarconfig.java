package com.contentcal.contentcal.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(value = "cc")
public record contentCalendarconfig(String welcomeMessage, String About) {
    
}
