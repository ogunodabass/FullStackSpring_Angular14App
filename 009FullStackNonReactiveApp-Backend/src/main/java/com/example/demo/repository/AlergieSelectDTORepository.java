package com.example.demo.repository;

import java.util.Set;

import com.example.demo.custom.repository.SelectDTORepository;
import com.example.demo.entity.Alergie;
import com.example.demo.model.dto.AlergieBaseDTO;

public interface AlergieSelectDTORepository extends SelectDTORepository<AlergieBaseDTO, Alergie, Long>{

	<R extends AlergieBaseDTO> Set<R> findByMedicalId(Long medicalId,Class<R> clazz);

}
