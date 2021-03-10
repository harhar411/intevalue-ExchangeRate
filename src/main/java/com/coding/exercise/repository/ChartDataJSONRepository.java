package com.coding.exercise.repository;

import org.springframework.data.repository.CrudRepository;

import com.coding.exercise.entity.ChartDataJSON;

public interface ChartDataJSONRepository 
	extends CrudRepository<ChartDataJSON, Integer> {

}
