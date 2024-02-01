package com.ait.service;

import java.util.Map;

import com.ait.binding.LoginForm;
import com.ait.binding.RegisterForm;
import com.ait.binding.ResetPwdForm;
import com.ait.entity.User;

public interface IUserService {

public Map<Integer, String> getCountries( );
	
	public Map<Integer, String> getStates(Integer countryId);
	
	public Map<Integer, String> getCities(Integer stateId);
	
	public User getUser(String email);
	
	public boolean saveUser(RegisterForm formObj);
	
	public User login(LoginForm formObj);
	
	public boolean resetPwd(ResetPwdForm formObj);
}
