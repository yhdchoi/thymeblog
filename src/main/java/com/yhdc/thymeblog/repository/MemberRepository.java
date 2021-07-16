package com.yhdc.thymeblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yhdc.thymeblog.model.User;

public interface MemberRepository extends JpaRepository<User, Long> {

}
