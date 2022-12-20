package com.example.demo.service;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.dto.AlergieDTO;
import com.example.demo.repository.AlergieSelectDTORepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AlergiaService {

	private final AlergieSelectDTORepository selectDTORepository;

	@Async
	@Transactional(timeout = 5)
	public CompletableFuture<Set<AlergieDTO>> findByMedicalId(Long medicalId) {
		return CompletableFuture
			.<Set<AlergieDTO>>completedFuture(selectDTORepository.findByMedicalId(medicalId, AlergieDTO.class));

	}
}
