package com.meli.challenge.morse.resource.exception.handler;

import com.meli.challenge.morse.resource.exception.AppException;
import com.meli.challenge.morse.resource.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

/**
 * @author Ulisses Pereira da Silva Neto
 * @since 04/2022
 */
@RestController
@RestControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {
	
	/**
	 * Manipulador de exceção para erros do tipo 'AppException'
	 * @param exception
	 * @param request
	 * @return
	 */
	@ExceptionHandler(AppException.class)
	public final ResponseEntity<ExceptionResponse> handleAppExceptions(AppException exception, WebRequest request){
		ExceptionResponse exResponse = new ExceptionResponse(exception.getCode(), request.getDescription(Boolean.FALSE));
		return new ResponseEntity<ExceptionResponse>(exResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * Manipulador de exceção para erros do tipo 'BusinessException'
	 * @param exception
	 * @param request
	 * @return
	 */
	@ExceptionHandler(BusinessException.class)
	public final ResponseEntity<ExceptionResponse> handleBusinessExceptions(BusinessException exception, WebRequest request){
		ExceptionResponse exResponse = new ExceptionResponse(exception.getCode(), request.getDescription(Boolean.FALSE));
		return new ResponseEntity<ExceptionResponse>(exResponse, HttpStatus.BAD_REQUEST);
	}

}
