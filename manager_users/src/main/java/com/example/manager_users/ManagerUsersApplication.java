package com.example.manager_users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ManagerUsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagerUsersApplication.class, args);
	}

}
