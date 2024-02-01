package com.ait.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CITY_MASTER")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class City {

	@Id
	private Integer cityId;
	
	private String cityName;
	
	private Integer stateId;
}
