package com.ait.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "STATE_MASTER")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class State {

	@Id
	private Integer stateId;
	
	private String stateName;
	
	private Integer countryId;
}
