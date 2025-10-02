package com.NobleScan.NobleServer.REST;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "MEASUREMENT_SERIES")
public class MeasurementSeries {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MEASUREMENT_SERIES_ID")
	private Integer measurementSeriesId;

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "BATCH_ID", nullable = false)
	private Batch batch;

	@Column(name = "COATING_ROUND")
	private Integer coatingRound;

	@OneToMany(mappedBy = "measurementSeries", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<Measurement> measurements;

	//Constructors
	public MeasurementSeries(){};

	public MeasurementSeries(Batch batch, Integer coatingRound) {
		this.batch = batch;
		this.coatingRound = coatingRound;
	}

	// Getters and Setters

	public Integer getMeasurementSeriesId() {
		return measurementSeriesId;
	}

	public void setMeasurementSeriesId(Integer measurementSeriesId) {
		this.measurementSeriesId = measurementSeriesId;
	}

	public Batch getBatch() {
		return batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

	public Integer getCoatingRound() {
		return coatingRound;
	}

	public void setCoatingRound(Integer coatingRound) {
		this.coatingRound = coatingRound;
	}

	public List<Measurement> getMeasurements() {
		return measurements;
	}

	public void setMeasurements(List<Measurement> measurements) {
		this.measurements = measurements;
	}
}
