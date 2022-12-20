package com.example.demo.custom.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import com.example.demo.model.dto.AbstractDTO;

@NoRepositoryBean
public interface SelectDTORepository<BaseDTO extends AbstractDTO,Entity, ID extends Serializable> extends Repository<Entity, ID>{

	public <R extends BaseDTO> List<R> findAllBy(Class<R> clazz);

	public <R extends BaseDTO> R findById(ID id, Class<R> clazz);
	

}