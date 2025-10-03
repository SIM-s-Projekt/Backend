package com.NobleScan.NobleServer.API;

public class MeasurementNotFoundException extends RuntimeException {
	public MeasurementNotFoundException(Integer id) {
		super("Could not find measurement " + id);
	}
}
