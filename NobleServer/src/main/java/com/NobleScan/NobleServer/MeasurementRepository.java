package com.NobleScan.NobleServer;

import com.NobleScan.NobleServer.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
	// You can define custom query methods here if needed
}
