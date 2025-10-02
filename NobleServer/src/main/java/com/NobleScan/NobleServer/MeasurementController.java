package com.NobleScan.NobleServer;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class MeasurementController {
	private final MeasurementRepository measurementRepository;
	private final MeasurementAssembler assembler;

	public MeasurementController(MeasurementRepository measurementRepository, MeasurementAssembler assembler) {
		this.measurementRepository = measurementRepository;
		this.assembler = assembler;
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

}
