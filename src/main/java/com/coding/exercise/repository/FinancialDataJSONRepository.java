package com.coding.exercise.repository;

import org.springframework.data.repository.CrudRepository;

import com.coding.exercise.entity.FinancialDataJSON;

public interface FinancialDataJSONRepository 
	extends CrudRepository<FinancialDataJSON, Integer>{

}
