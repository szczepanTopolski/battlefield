package com.sztop.battlefield;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BattlefieldApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(BattlefieldApplication.class)
				.profiles(args)
				.run(args);
	}

}
