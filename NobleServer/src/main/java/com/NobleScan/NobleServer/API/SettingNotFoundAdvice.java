package com.NobleScan.NobleServer.API;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SettingNotFoundAdvice {
	@ExceptionHandler(SettingNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String settingNotFoundHandler(SettingNotFoundException ex) {return ex.getMessage();}
}
