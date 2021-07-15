package com.yhdc.thymeblog.repotest;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yhdc.thymeblog.model.Board;
import com.yhdc.thymeblog.repository.BoardRepository;

@SpringBootTest
public class BoardRepoTest {

	@Autowired
	private BoardRepository boardRepository;
	
	
	@Test
	public void insert() {
		
		IntStream.rangeClosed(11, 50).forEach(i -> {						
			Board board = Board.builder().title("Title..." + i).content("Board content..." + i)
					.build();

			boardRepository.save(board);
		});	
		
	}
}
