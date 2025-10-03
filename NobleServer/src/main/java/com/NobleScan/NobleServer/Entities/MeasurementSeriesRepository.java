package com.NobleScan.NobleServer.Entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementSeriesRepository extends JpaRepository<MeasurementSeries, Integer> {
	// You can define custom query methods here if needed
}
