package com.ait.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ait.binding.LoginForm;
import com.ait.binding.RegisterForm;
import com.ait.binding.ResetPwdForm;
import com.ait.entity.City;
import com.ait.entity.Country;
import com.ait.entity.State;
import com.ait.entity.User;
import com.ait.repository.CityRepo;
import com.ait.repository.CountryRepo;
import com.ait.repository.StateRepo;
import com.ait.repository.UserRepo;
import com.ait.utils.EmailUtils;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private CountryRepo countryRepo;
	
	@Autowired
	private StateRepo stateRepo;
	
	@Autowired
	private CityRepo cityRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private EmailUtils emailutil;

	@Override
	public Map<Integer, String> getCountries() {
		
		Map<Integer, String> countriesMap=new HashMap<>();
		
		List<Country> countryList = countryRepo.findAll();
		
		/*for ( Country c : countryList) {
			countries.put(c.getCountryId(), c.getCountryName());
		}*/
		
		countryList.forEach(c -> {
			countriesMap.put(c.getCountryId(),c.getCountryName());
		});
		
		return countriesMap;
	}

	@Override
	public Map<Integer, String> getStates(Integer countryId) {
		
		Map<Integer, String> statesMap=new HashMap<>();
		
		List<State> stateList = stateRepo.findByCountryId(countryId);
		
		stateList.forEach(s -> {
			statesMap.put(s.getStateId(), s.getStateName());
		});
		return statesMap;
	}

	@Override
	public Map<Integer, String> getCities(Integer stateId) {
		
		Map<Integer, String> citiesMap=new HashMap<>();
		
		List<City> cityList = cityRepo.findByStateId(stateId);
		
		cityList.forEach(c -> {
			citiesMap.put(c.getCityId(), c.getCityName());
		});
		return citiesMap;
	}

	@Override
	public User getUser(String email) {
		return userRepo.findByEmail(email);
	}

	@Override
	public boolean saveUser(RegisterForm formObj) {
		
		formObj.setPwd(generateRandomPassword());
		formObj.setPwdUpdated("NO");
		
		User userEntity = new User();	
		BeanUtils.copyProperties(formObj, userEntity);
	
		 userRepo.save(userEntity);
		 
		 String subject="Your account created Ashok IT!!";
		 String body="<h1>Your password is : "+formObj.getPwd()+"<h1/>";
		 
		 return emailutil.sendEmail(subject, body, formObj.getEmail());
	}

	private String generateRandomPassword() {
		String alphanumericCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuv";
		 
	    StringBuffer randomString = new StringBuffer(5);
	    Random random = new Random();
	 
	    for (int i = 0; i < 5; i++) {
	        int randomIndex = random.nextInt(alphanumericCharacters.length()-1);
	        char randomChar = alphanumericCharacters.charAt(randomIndex);
	        randomString.append(randomChar);
	    }
	    return randomString.toString();
	}

	@Override
	public User login(LoginForm formObj) {
		return userRepo.findByEmailAndPwd(formObj.getEmail(),formObj.getPwd());
	}

	@Override
	public boolean resetPwd(ResetPwdForm formObj) {
		
		Optional<User> findById = userRepo.findById(formObj.getUserId());
		
		if (findById.isPresent()) {
			User user = findById.get();
			user.setPwd(formObj.getNewPwd());
			user.setPwdUpdated("YES");
			userRepo.save(user);
			System.out.println(user);
			return true;
		}
		return false;
	}

}
