package com.NobleScan.NobleServer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BatchController {
	private final BatchRepository batchRepository;

	BatchController(BatchRepository batchRepository) {
		this.batchRepository = batchRepository;
	}

	@GetMapping("/batches")
	List<Batch> all(){
		return batchRepository.findAll();
	}

	@GetMapping("/batch/{id}")
	Batch one(@PathVariable Integer id){
		return batchRepository.findById(id).orElse(null);
	}

}


