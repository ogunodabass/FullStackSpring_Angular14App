package com.example.demo.repository;

import java.util.Set;

import com.example.demo.custom.repository.SelectDTORepository;
import com.example.demo.entity.NextOfKin;
import com.example.demo.model.dto.NextOfKinBaseDTO;

public interface NextOfKinSelectDTORepository extends SelectDTORepository<NextOfKinBaseDTO, NextOfKin, Long>{

	<R extends NextOfKinBaseDTO> Set<R> findByUserId(Long userId,Class<R> clazz);
	
	
}
