package com.example.demo.model.dto;

import com.example.demo.entity.embeddable.UserProperties;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class NextOfKinDTO extends NextOfKinBaseDTO {

	private Long id;
	
	@JsonUnwrapped
	private UserPropertiesBaseDTO userPropertiesDTO;
	
	public NextOfKinDTO(long id,UserProperties userProperties) {
		this.id=id;
		this.userPropertiesDTO = UserPropertiesDTO.builder()
				.setIdCard(userProperties.getIdCard())
				.setName(userProperties.getName())
				.setSurName(userProperties.getSurName())
				.setContactNumber1(userProperties.getContactNumber1())
				.setContactNumber2(userProperties.getContactNumber2())
				.build();
	}
}
