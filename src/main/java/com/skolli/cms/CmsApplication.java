package com.skolli.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class CmsApplication {

	public static void main(String[] args) {
		// set timezone to GMT
		TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
		SpringApplication.run(CmsApplication.class, args);
	}

}
