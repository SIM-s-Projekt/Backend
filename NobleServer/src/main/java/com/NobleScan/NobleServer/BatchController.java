package com.NobleScan.NobleServer;

import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class BatchController {
	private final BatchRepository batchRepository;

	BatchController(BatchRepository batchRepository) {
		this.batchRepository = batchRepository;
	}

	// Aggregate root
	// tag::get-aggregate-root[]
	@GetMapping("/batches")
	List<Batch> all(){
		return batchRepository.findAll();
	}
	// end::get-aggregate-root[]

	@GetMapping("/batches/{id}")
	EntityModel<Batch> one(@PathVariable Integer id){
		Batch batch = batchRepository.findById(id).orElseThrow(() -> new BatchNotFoundException(id));

		return EntityModel.of(batch, //
				linkTo(methodOn(BatchController.class).one(id)).withSelfRel(),
				linkTo(methodOn(BatchController.class).all()).withRel("batch"));
	}

}


