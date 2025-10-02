package com.NobleScan.NobleServer.REST;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class MeasurementSeriesAssembler implements RepresentationModelAssembler<MeasurementSeries, EntityModel<MeasurementSeries>> {
	public EntityModel<MeasurementSeries> toModel(MeasurementSeries series) {

		return EntityModel.of(series, //
				linkTo(methodOn(MeasurementSeriesController.class).one(series.getMeasurementSeriesId())).withSelfRel(),
				linkTo(methodOn(MeasurementSeriesController.class).all()).withRel("measurementSeries"));
	}

}
