package com.ait.runner;

import java.util.Arrays;

import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.ait.entity.City;
import com.ait.entity.Country;
import com.ait.entity.State;
import com.ait.repository.CityRepo;
import com.ait.repository.CountryRepo;
import com.ait.repository.StateRepo;

@Component
public class DataLoader implements ApplicationRunner {
	
	@Autowired
	private CountryRepo countryRepo;
	
	@Autowired
	private StateRepo stateRepo;
	
	@Autowired
	private CityRepo cityRepo;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		countryRepo.deleteAll();
		stateRepo.deleteAll();
		cityRepo.deleteAll();
		
		Country c1=new Country(1, "India");
		Country c2=new Country(2, "USA");
		countryRepo.saveAll(Arrays.asList(c1,c2));
		
		State s1=new State(1, "MP", 1);
		State s2=new State(2, "UP", 1);
		State s3=new State(3, " Alabama", 2);
		State s4=new State(4, "Alaska", 2);
		stateRepo.saveAll(Arrays.asList(s1,s2,s3,s4));
		
		City ct1=new City(1, "Khurai", 1);
		City ct2=new City(2, "Sagar", 1);
		City ct3=new City(3, "Ayodhya", 2);
		City ct4=new City(4, "Prayagraj", 2);
		City ct5=new City(5, "Akron", 3);
		City ct6=new City(6, "Alabaster", 3);
		City ct7=new City(7, "Barrow", 4);
		City ct8=new City(8, "Cooper", 4);
		cityRepo.saveAll(Arrays.asList(ct1,ct2,ct3,ct4,ct5,ct6,ct7,ct8));

	}
}
