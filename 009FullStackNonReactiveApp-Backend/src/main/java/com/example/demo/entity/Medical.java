package com.example.demo.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(setterPrefix = "set")
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor


@Entity
@Table

public class Medical {
	
	@Id
	@SequenceGenerator(name = "medical_sequence", sequenceName = "sq_medical", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medical_sequence")
	
	@ToString.Include
	@EqualsAndHashCode.Include
	private Long id;
	
	@Valid
	@OneToMany(fetch = FetchType.LAZY ,cascade = CascadeType.ALL
	//,mappedBy = "medical"
	)
	@JoinColumn(name = "medical_id")
	@ToString.Include(name = "id")
	private Set<Alergie> alergies;
	
	@Valid
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL
	)
	@ToString.Include(name = "id")
	@JoinColumn(name = "medical_id")
	private Set<Condition> conditions;

	@Valid
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL
	)
	@JoinColumn(name = "medical_id")
	@ToString.Include(name = "id")
	private Set<Medication> medications;
	
	
	
}
