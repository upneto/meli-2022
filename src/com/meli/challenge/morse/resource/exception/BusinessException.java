package com.meli.challenge.morse.resource.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Ulisses Pereira da Silva Neto
 * @since 04/2022
 */
@Getter
@AllArgsConstructor
public class BusinessException extends RuntimeException {

	/** Código do erro */
	private int code;

	/**
	 * CONSTRUTOR
	 * @param message
	 */
	public BusinessException(int code, String message) {
		super(message);
		this.code = code;
	}

	/**
	 * CONSTRUTOR
	 * @param message
	 * @param throwable
	 */
	public BusinessException(int code, String message, Throwable throwable) {
		super(message, throwable);
		this.code = code;
	}

}
