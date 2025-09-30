package com.NobleScan.NobleServer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class LoadDB {
	private static final Logger log = LoggerFactory.getLogger(LoadDB.class);

	@Bean
	CommandLineRunner initDatabase(BatchRepository batchRepository,
									MeasurementRepository measurementRepository,
									MeasurementSeriesRepository measurementSeriesRepository) {

		return args -> {
			log.info("Creating example data...");
			Batch exampleBatch0 = new Batch(
					"Platinum/Gold",
					"Titanium",
					new BigDecimal("13.569"),
					1000,
					1500,
					new BigDecimal("15"),
					40,
					"Smooth"
			);
			Batch exampleBatch1 = new Batch(
					"Iridium/Rhutenium",
					"Zinc",
					new BigDecimal("24.44"),
					120,
					145,
					new BigDecimal("22"),
					15,
					"Smooth"
			);
			batchRepository.save(exampleBatch0);
			batchRepository.save(exampleBatch1);
			log.info("Example data created!");
		};
	}
}
