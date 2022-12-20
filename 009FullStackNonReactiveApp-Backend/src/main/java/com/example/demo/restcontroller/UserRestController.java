package com.example.demo.restcontroller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.validation.constraints.NotNull;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.model.dto.UserDTO1;
import com.example.demo.model.request.UserUpdateRequest;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor

public class UserRestController {

	private final UserService userService;

	@GetMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserDTO1>> getAll() throws InterruptedException, ExecutionException {
		return ResponseEntity.ok(userService.getAll());
	}


	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable(value = "id") Long userId) {
		userService.deleteById(userId).join();
		return ResponseEntity.ok(String.format("id= %s deleted...", userId));

	}

	@PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> add(@RequestBody @Validated User user) {
		userService.add(user);
		return ResponseEntity.ok().build();

	}

	@PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO1> updateEmployee(@RequestBody @Validated UserUpdateRequest updateRequest,
			@NotNull @PathVariable(value = "id") Long userId) {
		updateRequest.setUserId(userId);
		return ResponseEntity.ok(userService.update(updateRequest));
	}

}
