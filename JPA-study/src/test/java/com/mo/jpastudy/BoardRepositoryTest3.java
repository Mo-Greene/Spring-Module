package com.mo.jpastudy;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BoardRepositoryTest3 {

    @Autowired
    public EntityManager em;

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
    @DisplayName("create Query => JPQL 테스트")
    public void createQueryTest() {

        String query = "SELECT b FROM Board b";
        TypedQuery<Board> typedQuery = em.createQuery(query, Board.class);
        List<Board> list = typedQuery.getResultList();

        assertTrue(list.size() == 100);
    }

    @Test
    @DisplayName("Jpql Test")
    public void queryAnnoTest() {
        List<Board> list = boardRepository.findAllBoard();
        assertTrue(list.size() == 100);
    }

    @Test
    @DisplayName("@Query JPQL 테스트 - 매개변수 작성")
    public void queryFindByTitle() {
        List<Board> list = boardRepository.findBoardByTitle("title 1");
        assertTrue(list.size() == 1);
    }
}
