package com.yhdc.thymeblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yhdc.thymeblog.model.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

}