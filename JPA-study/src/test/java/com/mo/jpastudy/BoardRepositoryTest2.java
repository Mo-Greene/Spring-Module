package com.mo.jpastudy;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BoardRepositoryTest2 {

    @Autowired
    private BoardRepository boardRepository;

    @BeforeEach
    public void testData() {
        for (int i = 1; i <= 100; i++) {
            Board board = new Board();
            board.setBno((long) i);
            board.setTitle("title " + i);
            board.setContent("content " + i);
            board.setWriter("writer " + (i % 5));
            board.setViewCount((long)(Math.random() * 100));
            board.setInDate(new Date());
            board.setUpDate(new Date());
            boardRepository.save(board);
        }
    }

    @Test
    @Order(1)
    public void countTest() {
        assertTrue(boardRepository.countAllByWriter("writer 1") == 20);
    }

    @Test
    @Order(2)
    public void findTest() {
        List<Board> list = boardRepository.findBoardByWriter("writer 1");

        assertTrue(list.size() == 20);
    }

    @Test
    public void deleteTest() {
        assertTrue(boardRepository.deleteByWriter("writer 1") == 20);
        List<Board> list = boardRepository.findBoardByWriter("writer 1");
        assertTrue(list.isEmpty());
    }
}
