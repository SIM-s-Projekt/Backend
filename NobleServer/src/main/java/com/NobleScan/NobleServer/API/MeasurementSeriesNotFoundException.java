package com.NobleScan.NobleServer.API;

public class MeasurementSeriesNotFoundException extends RuntimeException {
	 MeasurementSeriesNotFoundException(Integer id) {
		super("Could not find measurementSeries: " + id);
	}
}
