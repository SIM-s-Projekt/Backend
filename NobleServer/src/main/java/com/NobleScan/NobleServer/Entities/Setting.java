package com.NobleScan.NobleServer.Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "SETTING")
public class Setting {
	@Id
	@Column(name = "SETTING_KEY", length = 100, nullable = false)
	private String key;

	@Column(name = "SETTING_VALUE", length = 100, nullable = false)
	private String value;

	@Column(name = "LAST_UPDATED", nullable = false)
	private LocalDateTime lastUpdated;

	//Constructor
	public Setting(){};

	public Setting(String key, String value) {
		this.key = key;
		this.value = value;
		this.lastUpdated = LocalDateTime.now();
	}
	// Getters and Setters
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public LocalDateTime getLastUpdated() {
		return lastUpdated;
	}

}
