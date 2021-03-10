package com.coding.exercise.repository;

import org.springframework.data.repository.CrudRepository;

import com.coding.exercise.entity.AutoCompleteDataJSON;

public interface DefaultDataJSONRepository 
	extends CrudRepository<AutoCompleteDataJSON, Integer>{

}
