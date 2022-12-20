package com.example.demo.service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.dto.NextOfKinDTO;
import com.example.demo.repository.NextOfKinSelectDTORepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NextOfKinService {

	private final NextOfKinSelectDTORepository selectDTORepository;


	@Transactional(timeout = 5, readOnly = true, propagation = Propagation.REQUIRED)
	public List<NextOfKinDTO> findAllBy() {
		return selectDTORepository.findAllBy(NextOfKinDTO.class);
	}

	@Async
	@Transactional(timeout = 5, readOnly = true, propagation = Propagation.REQUIRED)
	public CompletableFuture<Set<NextOfKinDTO>> findByUserId(Long userId) {
		return CompletableFuture
				.<Set<NextOfKinDTO>>completedFuture(selectDTORepository.findByUserId(userId, NextOfKinDTO.class));
	}

}
