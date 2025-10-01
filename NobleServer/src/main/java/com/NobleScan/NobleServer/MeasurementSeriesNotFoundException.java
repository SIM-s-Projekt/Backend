package com.NobleScan.NobleServer;

public class MeasurementSeriesNotFoundException extends RuntimeException {
	 MeasurementSeriesNotFoundException(Integer id) {
		super("Could not find measurementSeries: " + id);
	}
}
