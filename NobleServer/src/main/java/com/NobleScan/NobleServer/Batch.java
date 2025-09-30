package com.NobleScan.NobleServer;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "BATCH")
public class Batch {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BATCH_ID")
	private Integer batchId;

	@Column(name = "START_DATE")
	@Temporal(TemporalType.DATE)
	private Date startDate;

	@Column(name = "END_DATE")
	@Temporal(TemporalType.DATE)
	private Date endDate;

	@Column(name = "PLATE_COUNT")
	private Integer plateCount;

	@Column(name = "COATING_REQUIREMENT", precision = 10, scale = 4)
	private BigDecimal coatingRequirement;

	@Column(name = "COATING_TYPE", length = 100)
	private String coatingType;

	@Column(name = "PLATE_SURFACE_TYPE", length = 100)
	private String plateSurfaceType;

	@Column(name = "MATERIAL", length = 100)
	private String material;

	@Column(name = "WIDTH")
	private Integer width;

	@Column(name = "HEIGHT")
	private Integer height;

	@Column(name = "THICKNESS", precision = 10, scale = 4)
	private BigDecimal thickness;

	@OneToMany(mappedBy = "batch", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<MeasurementSeries> measurementSeries;

	public Batch() {}

	public Batch(String coatingType, String material, BigDecimal coatingRequirement, Integer height,
			Integer width, BigDecimal thickness, Integer plateCount, String plateSurfaceType) {
		this.coatingType = coatingType;
		this.material = material;
		this.coatingRequirement = coatingRequirement;
		this.height = height;
		this.width = width;
		this.thickness = thickness;
		this.plateCount = plateCount;
		this.plateSurfaceType = plateSurfaceType;
	}

	// Getters and Setters

	public Integer getBatchId() {
		return batchId;
	}

	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getPlateCount() {
		return plateCount;
	}

	public void setPlateCount(Integer plateCount) {
		this.plateCount = plateCount;
	}

	public BigDecimal getCoatingRequirement() {
		return coatingRequirement;
	}

	public void setCoatingRequirement(BigDecimal coatingRequirement) {
		this.coatingRequirement = coatingRequirement;
	}

	public String getCoatingType() {
		return coatingType;
	}

	public void setCoatingType(String coatingType) {
		this.coatingType = coatingType;
	}

	public String getPlateSurfaceType() {
		return plateSurfaceType;
	}

	public void setPlateSurfaceType(String plateSurfaceType) {
		this.plateSurfaceType = plateSurfaceType;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public BigDecimal getThickness() {
		return thickness;
	}

	public void setThickness(BigDecimal thickness) {
		this.thickness = thickness;
	}

	public List<MeasurementSeries> getMeasurementSeries() {
		return measurementSeries;
	}

	public void setMeasurementSeries(List<MeasurementSeries> measurementSeries) {
		this.measurementSeries = measurementSeries;
	}
}
