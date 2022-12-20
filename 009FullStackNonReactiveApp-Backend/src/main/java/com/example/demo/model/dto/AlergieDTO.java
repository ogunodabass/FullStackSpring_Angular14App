package com.example.demo.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AlergieDTO extends AlergieBaseDTO{

	private Long id;
	private String name;
	private String notes;
	
	public AlergieDTO(Long id,String name,String notes) {
		this.id=id;
		this.name=name;
		this.notes=notes;
	}
}
