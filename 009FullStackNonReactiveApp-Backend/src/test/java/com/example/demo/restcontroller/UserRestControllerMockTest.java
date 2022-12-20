package com.example.demo.restcontroller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.entity.Alergie;
import com.example.demo.entity.Condition;
import com.example.demo.entity.Medical;
import com.example.demo.entity.Medication;
import com.example.demo.entity.NextOfKin;
import com.example.demo.entity.User;
import com.example.demo.entity.embeddable.UserProperties;
import com.example.demo.util.converter.Convert;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class UserRestControllerMockTest {

	@Value("${spring.data.rest.base-path}/user")
	private String API;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	public void beforeEach() {
		assertEquals(API, "/api/user");
	}

	@Order(Integer.MIN_VALUE)
	@Test
	public void ex() throws Exception {
		assertNotNull(mockMvc);
	}

	@Order(1)
	@Test
	public void endpointAdd() throws Exception {

		var algeria1 = Alergie.builder().setName("setName1").build();
		var algeria2 = Alergie.builder().setName("setName2").build();

		var c1 = Condition.builder().setName("setName1").build();
		var c2 = Condition.builder().setName("setName2").build();

		var m1 = Medication.builder().setName("setName1").build();
		var m2 = Medication.builder().setName("setName2").build();
		var medical = Medical.builder().setMedications(Set.of(m1, m2)).setConditions(Set.of(c1, c2))
				.setAlergies(Set.of(algeria1, algeria2)).build();

		var userProperties = UserProperties.builder().setIdCard("12345").build();
		var userProperties2 = UserProperties.builder().setIdCard("123456").build();

		var nextOfKins1 = NextOfKin.builder().setUserProperties(userProperties).build();
		var nextOfKins2 = NextOfKin.builder().setUserProperties(userProperties2).build();

		var user = User.builder().setPostcode("xx").setDateOfBirth(new Date()).setPostcode(API)
				.setUserProperties(userProperties).setMedical(medical).setAdress("1234556")
				.setNextOfKins(Set.<NextOfKin>of(nextOfKins1, nextOfKins2)).build();

		log.info(Convert.asJsonString(user));
		mockMvc.perform(post(API + "").content(Convert.asJsonString(user)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		
		// TODO  org.springframework.web.servlet.NoHandlerFoundException
	}

	@Order(2)

	public void endpointAll() throws Exception {

		this.mockMvc.perform(get(API + "/all").contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
				.andDo(print());
	}

	@Order(3)

	public void endpointDelete() throws Exception {
		mockMvc.perform(delete(API + "/1").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

}