package com.NobleScan.NobleServer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BatchNotFoundAdvice{
	@ExceptionHandler(BatchNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String batchNotFoundHandler(BatchNotFoundException ex) {
		return ex.getMessage();
	}
}
