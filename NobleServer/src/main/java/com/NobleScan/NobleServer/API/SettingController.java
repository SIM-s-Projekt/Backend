package com.NobleScan.NobleServer.API;

import com.NobleScan.NobleServer.Entities.Setting;
import com.NobleScan.NobleServer.Entities.SettingRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.http.ResponseEntity.created;

@RestController
public class SettingController {
	private final SettingRepository settingRepository;
	private final SettingModelAssembler assembler;

	SettingController(SettingRepository settingRepository, SettingModelAssembler assembler) {
		this.settingRepository = settingRepository;
		this.assembler = assembler;
	}

	//Aggregate root
	// tag ::get-aggregate-root[]
	@GetMapping("/settings")
	CollectionModel<EntityModel<Setting>> all(){
		List<EntityModel<Setting>> settings = settingRepository.findAll().stream()
				.map(assembler::toModel).collect(Collectors.toList());

		return CollectionModel.of(settings,
				linkTo(methodOn(SettingController.class).all()).withSelfRel());
	}

	@GetMapping("/settings/{key}")
	EntityModel<Setting> one(@PathVariable String key){
		Setting setting = settingRepository.findById(key).orElseThrow(() -> new SettingNotFoundException(key));

		return assembler.toModel(setting);
	}

	@PutMapping("/settings/{key}")
	public ResponseEntity<EntityModel<Setting>> updateSetting(@PathVariable String key, @RequestBody Setting setting) {
		 Setting updatedSetting = settingRepository.findById(key).orElseThrow(() -> new SettingNotFoundException(key));
		 updatedSetting.setValue(setting.getValue());
		 settingRepository.save(updatedSetting);

		EntityModel<Setting> entityModel = assembler.toModel(updatedSetting);
		return created(entityModel.getRequiredLink("self").toUri()).body(entityModel);
	}


}
