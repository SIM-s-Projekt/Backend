package com.NobleScan.NobleServer.API;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MeasurementSeriesNotFoundAdvice {
	@ExceptionHandler(MeasurementSeriesNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String batchNotFoundHandler(MeasurementSeriesNotFoundException ex) {
		return ex.getMessage();
	}
}
