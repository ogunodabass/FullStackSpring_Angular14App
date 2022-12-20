package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.NextOfKin;

public interface NextOfKinCrudRepository extends JpaRepository<NextOfKin, Long>{

	
	public List<NextOfKin> findAllById(Iterable<Long> ids);
}
