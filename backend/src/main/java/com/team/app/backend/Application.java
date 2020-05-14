package com.team.app.backend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


}
