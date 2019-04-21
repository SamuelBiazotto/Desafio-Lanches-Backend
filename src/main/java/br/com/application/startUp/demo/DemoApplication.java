package br.com.application.startUp.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

/**
 * A class used to start the application.
 *
 * @author Samuel Biazotto de Oliveira.
 * */

@SpringBootApplication
@RestController
public class DemoApplication {


	/**
	 * Fist method called.
	 *
	 * @author Samuel Biazotto de Oliveira.
	 * */
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
