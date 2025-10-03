package com.NobleScan.NobleServer.API;

import com.NobleScan.NobleServer.Entities.Setting;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class SettingModelAssembler implements RepresentationModelAssembler<Setting, EntityModel<Setting>> {
	@Override
	public EntityModel<Setting> toModel(Setting setting){
		return EntityModel.of(setting, //
				linkTo(methodOn(SettingController.class).one(setting.getKey())).withSelfRel(),
				linkTo(methodOn(SettingController.class).all()).withRel("settings"));
	}


}
