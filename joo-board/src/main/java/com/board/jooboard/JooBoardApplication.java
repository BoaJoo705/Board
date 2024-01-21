package com.board.jooboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;


@SpringBootApplication(exclude = {MultipartAutoConfiguration.class})
public class JooBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(JooBoardApplication.class, args);
	}

}
