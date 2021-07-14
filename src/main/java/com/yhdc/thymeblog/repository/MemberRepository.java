package com.yhdc.thymeblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yhdc.thymeblog.model.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
