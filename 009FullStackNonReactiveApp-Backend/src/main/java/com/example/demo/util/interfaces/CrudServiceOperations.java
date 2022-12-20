package com.example.demo.util.interfaces;

public interface CrudServiceOperations<BaseDTO,Entity,ID> {

	Entity getAll();

	Entity findById();

	void deleteById(ID id);
	
	Object update(Object data);
}
