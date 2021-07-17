package com.yhdc.thymeblog.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.yhdc.thymeblog.model.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

	// Search title
	List<Board> findByTitle(String title);
	
	// Search title and content
	Page<Board> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);
}
