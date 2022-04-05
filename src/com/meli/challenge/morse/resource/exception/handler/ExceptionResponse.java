package com.meli.challenge.morse.resource.exception.handler;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Ulisses Pereira da Silva Neto
 * @since 04/2022
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {

	/** Codigo do erro */
	private int code;

	/** Detalhamento do erro */
	private String text;

}
