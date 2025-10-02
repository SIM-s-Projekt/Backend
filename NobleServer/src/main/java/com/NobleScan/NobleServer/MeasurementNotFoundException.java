package com.NobleScan.NobleServer;

public class MeasurementNotFoundException extends RuntimeException {
	public MeasurementNotFoundException(Integer id) {
		super("Could not find measurement " + id);
	}
}
