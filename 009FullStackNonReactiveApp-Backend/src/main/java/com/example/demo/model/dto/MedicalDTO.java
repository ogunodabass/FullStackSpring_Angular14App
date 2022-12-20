package com.example.demo.model.dto;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class MedicalDTO extends MedicalBaseDTO {
	private Long id;

	private Set<AlergieDTO> alergies;

	public MedicalDTO(Long id) {
		this.id=id;
		this.alergies = new HashSet<AlergieDTO>(0);
	}
}
