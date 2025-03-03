package com.kata.restapi;

import com.kata.restapi.controller.Connection;
import com.kata.restapi.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class RestApiApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(RestApiApplication.class, args);
		Connection connection = context.getBean(Connection.class);
		User user = new User(3L,"James","Brown",(byte) 22);
		System.out.println(connection.saveUser(user));
		user.setName("Thomas");
		user.setLastName("Shelby");
		System.out.println(connection.updateUser(user));
		System.out.println(connection.deleteUser(3L));
	}
}