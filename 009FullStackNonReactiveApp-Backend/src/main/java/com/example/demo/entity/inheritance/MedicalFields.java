package com.example.demo.entity.inheritance;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(setterPrefix = "set",builderMethodName = "builderMedicalFields")
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor

@MappedSuperclass
public class MedicalFields {

	@ToString.Include
	@EqualsAndHashCode.Include
	@Id
	@SequenceGenerator(name = "medical_fields_sequence", sequenceName = "sq_medical_fields", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medical_fields_sequence")
	private Long id;

	@Size(max = 30)
	@EqualsAndHashCode.Include
	@Column(length = 30,unique = true)
	private String name;

	@Size(max = 255)
	@Column(length = 255)
	private String notes;
}
