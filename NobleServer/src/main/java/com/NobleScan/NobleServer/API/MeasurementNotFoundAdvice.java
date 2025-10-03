package com.NobleScan.NobleServer.API;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MeasurementNotFoundAdvice {
	@ExceptionHandler(MeasurementNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String measurementNotFoundHandler(MeasurementNotFoundException ex) {
		return ex.getMessage();
	}
}
