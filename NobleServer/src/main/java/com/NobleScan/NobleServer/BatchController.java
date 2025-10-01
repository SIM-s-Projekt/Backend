package com.NobleScan.NobleServer;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.http.ResponseEntity.created;

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

	@PutMapping("/batches/{id}")
	public ResponseEntity<EntityModel<Batch>> createOrUpdateBatch(@RequestBody Batch newBatch, @PathVariable Integer id) {
		Batch updatedBatch = batchRepository.findById(id).
				map(batch -> { // Map and replace values
					batch.setStartDate(newBatch.getStartDate());
					batch.setEndDate(newBatch.getEndDate());
					batch.setPlateCount(newBatch.getPlateCount());
					batch.setCoatingRequirement(newBatch.getCoatingRequirement());
					batch.setCoatingType(newBatch.getCoatingType());
					batch.setPlateSurfaceType(newBatch.getPlateSurfaceType());
					batch.setMaterial(newBatch.getMaterial());
					batch.setWidth(newBatch.getWidth());
					batch.setHeight(newBatch.getHeight());
					batch.setThickness(newBatch.getThickness());
					return batchRepository.save(batch);
				}).orElseGet(() -> { // Saves as new if does not exist.
					return batchRepository.save(newBatch);
				});
		EntityModel<Batch> entityModel = assembler.toModel(updatedBatch);
		return created(entityModel.getRequiredLink("self").toUri()).body(entityModel);
	}

	@PostMapping("/batches")
	ResponseEntity<EntityModel<Batch>> newOrder(@RequestBody Batch batch) {
		EntityModel<Batch> entityModel = assembler.toModel(batchRepository.save(batch));

		return ResponseEntity
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(entityModel);
	}

}


