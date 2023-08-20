package com.mo.jpastudy;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    @Order(1)
    public void insertTest() {
        Board board = new Board();
        board.setBno(1L);
        board.setTitle("test title");
        board.setContent("test content");
        board.setWriter("writer");
        board.setViewCount(0L);
        board.setInDate(new Date());
        board.setUpDate(new Date());
        boardRepository.save(board);
    }

    @Test
    @Order(2)
    public void selectTest() {
        Board board = boardRepository.findById(1L)
                .orElse(null);

        assertTrue(board != null);
    }

    @Test
    @Order(3)
    public void 업데이트() {
        Board board = boardRepository.findById(1L).orElse(null);
        assertTrue(board != null);

        board.setTitle("Modify Title");
        boardRepository.save(board);
        Board board2 = boardRepository.findById(1L).orElse(new Board());

        assertTrue(board.getTitle().equals(board2.getTitle()));
    }

    @Test
    @Order(4)
    public void 삭제() {
        boardRepository.deleteById(1L);

        Board board = boardRepository.findById(1L).orElse(null);
        assertTrue(board == null);
    }
}
