package com.example.demo.model.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserDTO1 extends UserBaseDTO {

	private Long id;

	@JsonUnwrapped
	private UserPropertiesBaseDTO userProperties;

	private Date dateOfBirth;

	private String adress;

	private String postcode;

	private MedicalDTO medical;

	private Set<NextOfKinDTO> nextOfKins;

	public UserDTO1(Long id, Date dateOfBirth, String adress, String postcode, String userProperties_idCard,
			String userProperties_name, String userProperties_surName, String userProperties_contactNumber1,
			String userProperties_contactNumber2, Long medical_id) {
		this.id = id;
		userProperties = new UserPropertiesDTO(userProperties_idCard, userProperties_name, userProperties_surName,
				userProperties_contactNumber1, userProperties_contactNumber2);
		this.dateOfBirth = dateOfBirth;
		this.adress = adress;
		this.postcode = postcode;
		this.nextOfKins = new HashSet<>(0);
		this.medical = new MedicalDTO(medical_id);
				
		this.medical.setAlergies(new HashSet<AlergieDTO>());

	}

}
