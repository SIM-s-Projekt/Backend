package com.NobleScan.NobleServer.REST;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class BatchModelAssembler implements RepresentationModelAssembler<Batch, EntityModel<Batch>>{
	@Override
	public EntityModel<Batch> toModel(Batch batch) {

		return EntityModel.of(batch, //
				linkTo(methodOn(BatchController.class).one(batch.getBatchId())).withSelfRel(),
				linkTo(methodOn(BatchController.class).all()).withRel("batches"));
	}
}
