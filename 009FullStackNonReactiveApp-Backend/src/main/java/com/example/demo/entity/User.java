package com.example.demo.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.example.demo.entity.embeddable.UserProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder(setterPrefix = "set")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor

@DynamicInsert(value = true)
@DynamicUpdate(value = true)
@Entity
@Table
public class User {

	@Id
	@SequenceGenerator(name = "user_sequence", sequenceName = "sq_user", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
	private Long id;

	@Valid
	@ToString.Include(name = "idCard")
	@Embedded
	private UserProperties userProperties;

	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;

	@Size(max = 255)
	@Column
	private String adress;

	@Size(max = 10)
	@Column(length = 10)
	private String postcode;

	@Valid
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,orphanRemoval = true)
	@JoinColumn(name = "user_id")
	@ToString.Include(name = "id")
	private Set<NextOfKin> nextOfKins ;
	
	@Valid
	@NotNull
	@OneToOne(fetch = FetchType.LAZY,optional = false,cascade = CascadeType.ALL,orphanRemoval = true)
	@JoinColumn(name = "medical_id")
	private Medical medical;
	
}
