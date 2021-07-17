package com.yhdc.thymeblog.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class UserDTO {

	private Long id;
	
	private String username;
	private String email;
	
	private String password;	
	private String confirmPassword;
		
	private Timestamp regDate;
	private Timestamp modDate;	
}
