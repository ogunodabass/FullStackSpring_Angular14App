package com.example.demo.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.User;
import com.example.demo.entity.embeddable.UserProperties;
import com.example.demo.model.dto.UserDTO1;
import com.example.demo.model.request.UserUpdateRequest;
import com.example.demo.repository.UserCrudRepository;
import com.example.demo.repository.UserSelectDTORepository;
import com.example.demo.repository.jdbc.UserJDBCRepository;
import com.example.demo.util.functions.Functions;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserSelectDTORepository selectDTORepository;
	private final UserCrudRepository userCrudRepository;
	private final UserJDBCRepository userJDBCRepository;
	@Lazy
	@Autowired
	private TaskExecutor taskExecutor;
	@Lazy
	@Autowired
	private NextOfKinService nextOfKinService;

	@Lazy
	@Autowired
	private AlergiaService alergiaService;

	@Transactional(timeout = 5, readOnly = true)
	public List<UserDTO1> getAll() throws InterruptedException, ExecutionException {

		CompletableFuture<List<UserDTO1>> task1 = CompletableFuture.<List<UserDTO1>>supplyAsync(() -> {
			return selectDTORepository.findAllBy(UserDTO1.class);
		}, taskExecutor).thenApply(v -> {
			List<UserDTO1> res = v.parallelStream().map(e -> {
				e.setNextOfKins(
						nextOfKinService.findByUserId(e.getId()).exceptionally(Functions::exceptionallySet).join());
				e.getMedical().setAlergies(alergiaService.findByMedicalId(e.getMedical().getId())
						.exceptionally(Functions::exceptionallySet).join());

				return e;
			}).collect(Collectors.toList());

			return res;
		});
		return task1.get();
	}

	@Transactional(timeout = 5)
	public CompletableFuture<Void> deleteById(Long id) {
		return CompletableFuture.runAsync(() -> {
			userCrudRepository.deleteById(id);
		});
	}

	@Transactional(timeout = 5, readOnly = true)
	public UserDTO1 findById(Long id) {
		return selectDTORepository.findById(id, UserDTO1.class);
	}

	@Transactional(timeout = 5)
	public User add(User user) {
		return userCrudRepository.save(user);

	}

	@Transactional(timeout = 5)
	public UserDTO1 update(UserUpdateRequest updateRequest) {

		if (updateRequest.hasPropertyNull())
			return null;
		var userProperties = UserProperties.builder().setName(updateRequest.getName())
				.setSurName(updateRequest.getSurName()).build();
		var user = User.builder().setUserProperties(userProperties).setId(updateRequest.getUserId()).build();
		return userJDBCRepository.update(user) ? this.findById(updateRequest.getUserId()) : null;
	}

}
