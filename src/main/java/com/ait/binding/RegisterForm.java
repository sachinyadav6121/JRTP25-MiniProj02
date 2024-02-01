package com.ait.binding;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterForm {

	 private Integer userId;
	 private String uname;
	 private String email;
	 private Long phone;
	 private String pwd;
	 private Integer countryId;
	 private Integer stateId;
	 private Integer cityId;
	 private String pwdUpdated;
}
