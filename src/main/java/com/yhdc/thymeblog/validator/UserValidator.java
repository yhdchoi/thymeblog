package com.yhdc.thymeblog.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.thymeleaf.util.StringUtils;

import com.yhdc.thymeblog.model.Board;
import com.yhdc.thymeblog.model.User;

@Component
public class UserValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Board.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors e) {

		User u = (User) obj;

		if (StringUtils.isEmpty(u.getUsername())) {
			e.rejectValue("username", "key", "Please write username.");
		}
		
	}

}
