package com.example.demo.repository;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.custom.repository.SelectDTORepository;
import com.example.demo.entity.User;
import com.example.demo.model.dto.UserBaseDTO;


@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW, timeout = 5)

public interface UserSelectDTORepository extends SelectDTORepository<UserBaseDTO,User, Long>  {

	 
	

}
