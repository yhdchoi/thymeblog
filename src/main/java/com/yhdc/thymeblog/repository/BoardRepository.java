package com.yhdc.thymeblog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yhdc.thymeblog.model.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

	List<Board> findByTitle(String title);
}
