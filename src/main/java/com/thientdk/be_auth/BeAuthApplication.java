package com.thientdk.be_auth;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class BeAuthApplication {

	private static final Logger log = LogManager.getLogger(BeAuthApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BeAuthApplication.class, args);

		log.info("========================================================");
		log.info("|              BE AUTH CODE BASE                       |");
		log.info("|              DEVELOP BY THIEN TRAN                   |");
		log.info("========================================================");
	}

}
