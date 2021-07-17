package com.yhdc.thymeblog.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.thymeleaf.util.StringUtils;

import com.yhdc.thymeblog.model.Comment;

@Component
public class CommentValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Comment.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors e) {

		Comment c = (Comment) obj;

		if (StringUtils.isEmpty(c.getContent())) {
			e.rejectValue("content", "key", "The content cannot be empty.");
		}
	}

}
