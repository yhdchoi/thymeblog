package com.yhdc.thymeblog.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.thymeleaf.util.StringUtils;

import com.yhdc.thymeblog.model.Board;

@Component
public class BoardValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
        return Board.class.equals(clazz);
    }

	@Override
    public void validate(Object obj, Errors e) {
        Board b = (Board) obj;
        
        if (StringUtils.isEmpty(b.getContent())) {
            e.rejectValue("content", "key", "Please insert content.");        
        }		
    }
	
}
