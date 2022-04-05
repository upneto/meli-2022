package com.meli.challenge.morse.resource.exception;

import lombok.*;

/**
 * @author Ulisses Pereira da Silva Neto
 * @since 04/2022
 */
@Getter
@AllArgsConstructor
public class AppException extends Exception {

	/** Código do erro */
	private int code;

	/**
	 * CONSTRUTOR
	 * @param message
	 */
	public AppException(int code, String message) {
		super(message);
		this.code = code;
	}

	/**
	 * CONSTRUTOR
	 * @param message
	 * @param throwable
	 */
	public AppException(int code, String message, Throwable throwable) {
		super(message, throwable);
		this.code = code;
	}

}
