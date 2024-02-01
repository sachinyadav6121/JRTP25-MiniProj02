package com.ait.binding;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResetPwdForm {

	 private Integer userId;
	 private String email;
	 private String newPwd;
	 private String confirmPwd;
}
