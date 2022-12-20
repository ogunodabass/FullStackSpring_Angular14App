package com.example.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.example.demo.entity.embeddable.UserProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "fieldHandler" })

@Data
@Builder(setterPrefix = "set")
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor

@DynamicInsert
@DynamicUpdate
@Entity
@Table

public class NextOfKin {

	@ToString.Include
	@EqualsAndHashCode.Include
	@Id
	@SequenceGenerator(name = "nextOfKin_sequence", sequenceName = "sq_nextOfKin", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nextOfKin_sequence")
	private Long id;

	@Valid
	@Embedded
	@EqualsAndHashCode.Include
	private UserProperties userProperties;

	
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private User user;

}
