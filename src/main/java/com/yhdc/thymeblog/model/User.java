package com.yhdc.thymeblog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 50)
	private String username;

	@Column(nullable = false, length = 50)
	private String email;

	@Column(nullable = false, length = 100)
	private String password;

	@Enumerated(EnumType.STRING)
	private RoleType role;

	@Enumerated(EnumType.STRING)
	private EnableType enable;

	@CreationTimestamp
	private Timestamp regDate;

	@UpdateTimestamp
	private Timestamp modDate;
}
