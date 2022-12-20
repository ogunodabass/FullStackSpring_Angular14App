package com.example.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.demo.entity.embeddable.DateStartEnd;
import com.example.demo.entity.inheritance.MedicalFields;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(setterPrefix = "set")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "Medication")
public class Medication extends MedicalFields {
	
	@Embedded
	private DateStartEnd dateStartEnd;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	@ToString.Include(name = "id")
	private Medical medical;
	
}
