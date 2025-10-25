package com.senac.alunoB;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AlunoBApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlunoBApplication.class, args);
	}

}
