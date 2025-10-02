package com.NobleScan.NobleServer.REST;

public class MeasurementSeriesNotFoundException extends RuntimeException {
	 MeasurementSeriesNotFoundException(Integer id) {
		super("Could not find measurementSeries: " + id);
	}
}
