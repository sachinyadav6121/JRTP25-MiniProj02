package com.ait.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ait.entity.Country;

public interface CountryRepo extends JpaRepository<Country, Integer>{

}
