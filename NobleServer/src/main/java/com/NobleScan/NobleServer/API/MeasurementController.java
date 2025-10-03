package com.NobleScan.NobleServer.API;

import com.NobleScan.NobleServer.Entities.Measurement;
import com.NobleScan.NobleServer.Entities.MeasurementRepository;
import com.NobleScan.NobleServer.Entities.MeasurementSeries;
import com.NobleScan.NobleServer.Entities.MeasurementSeriesRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class MeasurementController {
	private final MeasurementRepository measurementRepository;
	private final MeasurementAssembler assembler;
	//Also needs connected measurementSeries
	private final MeasurementSeriesRepository measurementSeriesRepository;

	public MeasurementController(MeasurementRepository measurementRepository, MeasurementAssembler assembler,
			MeasurementSeriesRepository measurementSeriesRepository) {
		this.measurementRepository = measurementRepository;
		this.assembler = assembler;
		this.measurementSeriesRepository = measurementSeriesRepository;
	}

	// Aggregate root
	// tag::get-aggregate-root[]
	@GetMapping("/measurements")
	CollectionModel<EntityModel<Measurement>> all(){
		List<EntityModel<Measurement>> measurements = measurementRepository.findAll().stream()
				.map(assembler::toModel).collect(Collectors.toList());

		return CollectionModel.of(measurements,
				linkTo(methodOn(MeasurementController.class).all()).withSelfRel());

	}
	// end::get-aggregate-root[]

	@GetMapping("/measurements/{id}")
	EntityModel<Measurement> one(@PathVariable Integer id){
		Measurement measurement = measurementRepository.findById(id).orElseThrow(() -> new MeasurementNotFoundException(id));

		return assembler.toModel(measurement);
	}

	@PostMapping("/measurements/{seriesId}")
	ResponseEntity<EntityModel<Measurement>> newMeasurement(@RequestBody Measurement newMeasurement, @PathVariable Integer seriesId) {
		MeasurementSeries series = measurementSeriesRepository.findById(seriesId).orElseThrow(() -> new MeasurementSeriesNotFoundException(seriesId));
		newMeasurement.setMeasurementSeries(series);

		EntityModel<Measurement> entityModel = assembler.toModel(measurementRepository.save(newMeasurement));

		return ResponseEntity
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(entityModel);

	}
}
