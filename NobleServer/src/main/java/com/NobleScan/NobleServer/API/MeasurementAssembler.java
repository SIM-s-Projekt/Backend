package com.NobleScan.NobleServer.API;

import com.NobleScan.NobleServer.Entities.Measurement;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class MeasurementAssembler implements RepresentationModelAssembler<Measurement, EntityModel<Measurement>>{
	public EntityModel<Measurement> toModel(Measurement measurement) {
		return EntityModel.of(measurement,
				linkTo(methodOn(MeasurementController.class).one(measurement.getMeasurementId())).withSelfRel(),
				linkTo(methodOn(MeasurementController.class).all()).withRel("measurement"));
	}

}
