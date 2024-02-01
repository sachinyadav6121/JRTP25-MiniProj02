package com.ait.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "USER_MASTER")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Integer userId;
	 private String uname;
	 private String email;
	 private Long phone;
	 private String pwd;
	 private Integer countryId;
	 private Integer stateId;
	 private Integer cityId;
	
	 private String pwdUpdated;
	 
	 @CreationTimestamp
	private LocalDateTime createdDate;
	 @UpdateTimestamp
	private LocalDateTime updatedDate;
		
}
