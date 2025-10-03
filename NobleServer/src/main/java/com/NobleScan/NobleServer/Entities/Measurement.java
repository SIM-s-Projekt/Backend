package com.NobleScan.NobleServer.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "MEASUREMENT")
public class Measurement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MEASUREMENT_ID")
	private Integer measurementId;

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "MEASUREMENT_SERIES_ID", nullable = false)
	private MeasurementSeries measurementSeries;

	@Column(name = "X_COORDINATE")
	private Integer xCoordinate;

	@Column(name = "Y_COORDINATE")
	private Integer yCoordinate;

	@Column(name = "CONCENTRATION_VALUE", precision = 10, scale = 4, nullable = false)
	private BigDecimal concentrationValue;

	@Column(name = "MEASURED_AT")
	private LocalDateTime measuredAt;

	//Constructor
	public Measurement() {};

	public Measurement(MeasurementSeries measurementSeries, Integer xCoordinate, Integer yCoordinate,
			BigDecimal concentrationValue, LocalDateTime measuredAt) {
		this.measurementSeries = measurementSeries;
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		this.concentrationValue = concentrationValue;
		this.measuredAt = measuredAt;
	}

	// Getters and Setters
	public Integer getMeasurementId() {
		return measurementId;
	}

	public void setMeasurementId(Integer measurementId) {
		this.measurementId = measurementId;
	}

	public MeasurementSeries getMeasurementSeries() {
		return measurementSeries;
	}

	public void setMeasurementSeries(MeasurementSeries measurementSeries) {
		this.measurementSeries = measurementSeries;
	}

	public Integer getXCoordinate() {
		return xCoordinate;
	}

	public void setXCoordinate(Integer xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public Integer getYCoordinate() {
		return yCoordinate;
	}

	public void setYCoordinate(Integer yCoordinate) {
		this.yCoordinate = yCoordinate;
	}

	public BigDecimal getConcentrationValue() {
		return concentrationValue;
	}

	public void setConcentrationValue(BigDecimal concentrationValue) {
		this.concentrationValue = concentrationValue;
	}

	public LocalDateTime getMeasuredAt() {
		return measuredAt;
	}

	public void setMeasuredAt(LocalDateTime measuredAt) {
		this.measuredAt = measuredAt;
	}
}
