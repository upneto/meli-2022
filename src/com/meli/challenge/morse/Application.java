package com.meli.challenge.morse;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * Classe bootstrap da aplicação
 * @author Ulisses Pereira da Silva Neto
 * @since 04/2022
 */
@SpringBootApplication
public class Application {

	/**
	 * Inicializadora da aplicação
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);
	}
	
}
