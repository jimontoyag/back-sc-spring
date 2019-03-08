package com.jimg.segurocanguro.back_spring.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(LoginException.class)
	public final ResponseEntity<Object> handleException(LoginException ex, WebRequest request) throws Exception {
		return new ResponseEntity<Object>(ex.getResp(), new HttpHeaders(), HttpStatus.UNAUTHORIZED);
	}

}
