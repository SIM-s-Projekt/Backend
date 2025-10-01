package com.NobleScan.NobleServer;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class MeasurementSeriesController {
	private final MeasurementSeriesRepository measurementSeriesRepository;
	private final MeasurementSeriesAssembler assembler;

	MeasurementSeriesController(MeasurementSeriesRepository measurementSeriesRepository, MeasurementSeriesAssembler assembler) {
		this.measurementSeriesRepository = measurementSeriesRepository;
		this.assembler = assembler;
	}

	// Aggregate root
	// tag::get-aggregate-root[]
	@GetMapping("/measurementSeries")
	CollectionModel<EntityModel<MeasurementSeries>> all() {
		List<EntityModel<MeasurementSeries>> series = measurementSeriesRepository.findAll().stream()
				.map(assembler::toModel).collect(Collectors.toList());

		return CollectionModel.of(series,
				linkTo(methodOn(MeasurementSeriesController.class).all()).withSelfRel());
	}
	// end::get-aggregate-root[]

	@GetMapping("/measurementSeries/{id}")
	EntityModel<MeasurementSeries> one(@PathVariable Integer id){
		MeasurementSeries series = measurementSeriesRepository.findById(id).orElseThrow(() -> new MeasurementSeriesNotFoundException(id));

		return assembler.toModel(series);
	}



}
