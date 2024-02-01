package com.ait.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ait.entity.State;

public interface StateRepo extends JpaRepository<State, Integer> {
	
	public List<State> findByCountryId(Integer countryId);
}