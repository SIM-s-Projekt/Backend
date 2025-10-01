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
public class BatchController {
	private final BatchRepository batchRepository;
	private final BatchModelAssembler assembler;

	BatchController(BatchRepository batchRepository, BatchModelAssembler batchAssembler) {
		this.batchRepository = batchRepository;
		this.assembler = batchAssembler;
	}

	// Aggregate root
	// tag::get-aggregate-root[]
	@GetMapping("/batches")
	CollectionModel<EntityModel<Batch>> all(){
		List<EntityModel<Batch>> batches = batchRepository.findAll().stream()
				.map(assembler::toModel).collect(Collectors.toList());

		return CollectionModel.of(batches,
				linkTo(methodOn(BatchController.class).all()).withSelfRel());

	}
	// end::get-aggregate-root[]

	@GetMapping("/batches/{id}")
	EntityModel<Batch> one(@PathVariable Integer id){
		Batch batch = batchRepository.findById(id).orElseThrow(() -> new BatchNotFoundException(id));

		return assembler.toModel(batch);
	}

}


