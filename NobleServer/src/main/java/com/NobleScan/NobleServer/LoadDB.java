package com.NobleScan.NobleServer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Configuration
public class LoadDB {
	private static final Logger log = LoggerFactory.getLogger(LoadDB.class);

	@Bean
	CommandLineRunner initDatabase(BatchRepository batchRepository,
									MeasurementRepository measurementRepository,
									MeasurementSeriesRepository measurementSeriesRepository) {

		return args -> {
			log.info("Creating example data...");

			//-- -- Inserting example batches -- --
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
			log.info("- Batches created!");

			//-- -- Inserting example measurement series -- --
			MeasurementSeries exampleMeasurementSeries1 = new MeasurementSeries(), exampleMeasurementSeries2 = new MeasurementSeries();
			for (int i = 0; i < 4; i++) {
				exampleMeasurementSeries1 = new MeasurementSeries(exampleBatch0, i);
				measurementSeriesRepository.save(exampleMeasurementSeries1);

				exampleMeasurementSeries2 = new MeasurementSeries(exampleBatch1, i);
				measurementSeriesRepository.save(exampleMeasurementSeries2);
				//-- -- Inserting example measurements -- --
				for (int j = 0; j < 5; j++) {
					Measurement exampleMeasurement1 = new Measurement(exampleMeasurementSeries1, 0, 10,
							new BigDecimal("9.23"), LocalDateTime.now());
					measurementRepository.save(exampleMeasurement1);
					Measurement exampleMeasurement2 = new Measurement(exampleMeasurementSeries2, 23, 19,
							new BigDecimal("11.13"), LocalDateTime.now());
					measurementRepository.save(exampleMeasurement2);
				}
			}
			log.info("- MeasurementSeries created!");
			log.info("- Measurements created!");
			log.info("Example data created!");
		};
	}
}
