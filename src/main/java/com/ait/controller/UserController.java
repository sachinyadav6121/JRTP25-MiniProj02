package com.ait.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ait.binding.LoginForm;
import com.ait.binding.RegisterForm;
import com.ait.binding.ResetPwdForm;
import com.ait.entity.User;
import com.ait.repository.CountryRepo;
import com.ait.service.IUserService;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

@Controller
public class UserController {

	@Autowired
	private IUserService uService;
	
	@GetMapping("/")
	private String index(Model model) {
		model.addAttribute("login", new LoginForm());
		return "login-view";
	}
	
	@PostMapping("/login")
	private String login(@ModelAttribute("login") LoginForm login,Model model) {
		
		User user = uService.login(login);
		
		if(user==null) {
			model.addAttribute("errMsg","Invalid Credentials");
			return "login-view";
		}
		
		if(user.getPwdUpdated().equals("NO")) {
			
			ResetPwdForm resetPwdForm = new ResetPwdForm();
			resetPwdForm.setUserId(user.getUserId());
			
			model.addAttribute("resetpwd", resetPwdForm);
			return "resetPwd";
		}
		
			return "redirect:dashboard";
	}
	
	@PostMapping("/updatePwd")
	public String updatePassword(ResetPwdForm form, Model model) {
		
		if(!form.getNewPwd().equals(form.getConfirmPwd())) {
			model.addAttribute("errMsg", "Both password should be match");
			model.addAttribute("resetpwd", new ResetPwdForm());
			return "resetPwd";
		}
			
		boolean resetPwd = uService.resetPwd(form);
		
		if(resetPwd) {
			return "redirect:dashboard";
		}
		
		model.addAttribute("resetpwd", new ResetPwdForm());
		model.addAttribute("errMsg","Password updation failed");
		return "resetPwd";
	}
	
	@GetMapping("/register")
	public String loadRegisterPage(Model model) {
		Map<Integer, String> countries = uService.getCountries();
		model.addAttribute("registerForm",new RegisterForm());
		model.addAttribute("countries",countries);
		return "register";
	}
	
	@GetMapping("/getStates")
	@ResponseBody
	public Map<Integer, String> getStates(@RequestParam("countryId") Integer countryId) {
		return uService.getStates(countryId);
	}
	
	@GetMapping("/getCities")
	@ResponseBody
	public Map<Integer, String> getCities(@RequestParam("stateId") Integer stateId) {
		return uService.getCities(stateId);
	}
	
	@PostMapping("/register")
	public String registerUser(Model model,@ModelAttribute("registerForm") RegisterForm registerUser) {
		boolean user = uService.saveUser(registerUser);
		
		if(user) {
			model.addAttribute("succMsg","Registration Success");
		}else {
			model.addAttribute("errMsg","Registration Failed");
		}
		
		Map<Integer, String> countries = uService.getCountries();
		model.addAttribute("countries",countries);
		
		return "register";
	}
	
}
