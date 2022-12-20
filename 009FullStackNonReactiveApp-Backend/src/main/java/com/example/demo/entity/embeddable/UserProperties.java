package com.example.demo.entity.embeddable;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder(setterPrefix = "set")
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor

@Embeddable
public class UserProperties implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@NotNull
	@Size(max = 7)
	@EqualsAndHashCode.Include
	@Column(length = 7, unique = true)
	private String idCard;

	@Size(max = 30)
	@Column(length = 30)
	private String name;
	
	@Size(max = 30)
	@Column(length = 30)
	private String surName;

	@Size(max = 20)
	@Column(length = 20, nullable = true, unique = true)
	private String contactNumber1;

	@Size(max = 20)
	@Column(length = 20, nullable = true, unique = true)
	private String contactNumber2;
}
