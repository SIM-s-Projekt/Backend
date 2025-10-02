package com.NobleScan.NobleServer.REST;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Integer> {
	// You can define custom query methods here if needed
}
