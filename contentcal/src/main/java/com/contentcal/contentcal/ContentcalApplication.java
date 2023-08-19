 package com.contentcal.contentcal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.contentcal.contentcal.config.contentCalendarconfig;

@EnableConfigurationProperties(contentCalendarconfig.class)
@SpringBootApplication
public class ContentcalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContentcalApplication.class, args);
	}

}
