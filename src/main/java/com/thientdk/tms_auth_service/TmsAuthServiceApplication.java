package com.thientdk.tms_auth_service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TmsAuthServiceApplication {

	private static final Logger log = LogManager.getLogger(TmsAuthServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TmsAuthServiceApplication.class, args);

		log.info("========================================================");
		log.info("|              TMS - TINY MICROSERVICE                 |");
		log.info("|              TMS AUTH SERVICE                        |");
		log.info("========================================================");
	}

}
