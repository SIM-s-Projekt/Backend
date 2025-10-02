package com.NobleScan.NobleServer.REST;

public class MeasurementNotFoundException extends RuntimeException {
	public MeasurementNotFoundException(Integer id) {
		super("Could not find measurement " + id);
	}
}
