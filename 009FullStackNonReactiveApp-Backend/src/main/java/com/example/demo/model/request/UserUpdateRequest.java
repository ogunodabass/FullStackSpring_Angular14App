package com.example.demo.model.request;

import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserUpdateRequest {

	private Long userId;

	@Size(max = 30)
	private String name;
	@Size(max = 30)
	private String surName;

	public UserUpdateRequest(Long userId) {
		this.userId=userId;
	}

	public boolean hasPropertyNull() {
		return this.hashCode()==new UserUpdateRequest(userId).hashCode();
	}
}
