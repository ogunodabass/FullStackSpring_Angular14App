package com.example.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder(setterPrefix = "set")

@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class UserPropertiesDTO extends UserPropertiesBaseDTO {

	private String idCard;
	private String name;
	private String surName;
	private String contactNumber1;
	private String contactNumber2;
}
