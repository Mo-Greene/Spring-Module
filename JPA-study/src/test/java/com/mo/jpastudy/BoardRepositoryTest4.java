package com.mo.jpastudy;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BoardRepositoryTest4 {

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
    public void testQueryDSL() {
        QBoard board = QBoard.board;
        //1. JPQQueryFactory 생성
        JPAQueryFactory qf = new JPAQueryFactory(em);

        //2. 쿼리 작성
        JPAQuery<Board> query = qf.selectFrom(board)
                .where(board.title.eq("title 1"));

        //3. 쿼리 실행
        List<Board> list = query.fetch();
        list.forEach(System.out::println);
    }

    @Test
    public void querydslTest1() {
        QBoard board = QBoard.board;
        JPAQueryFactory qf = new JPAQueryFactory(em);

        JPAQuery<Tuple> query = qf.select(board.writer, board.viewCount.sum()).from(board)
                .where(board.writer.eq("writer 1"))
                .where(board.content.contains("content"))
                .groupBy(board.writer)
                .orderBy(board.writer.asc())
                .orderBy(board.viewCount.sum().desc());

        List<Tuple> list = query.fetch();
        list.forEach(System.out::println);
    }

    @Test
    @DisplayName("동적쿼리 BooleanBuilder")
    public void querydslTest2() {
        QBoard board = QBoard.board;

        String searchBy = "TC";
        String keyword = "1";
        keyword = "%" + keyword + "%";
        BooleanBuilder builder = new BooleanBuilder();

        if (searchBy.equalsIgnoreCase("T")) {
            builder.and(board.title.like(keyword));
        } else if (searchBy.equalsIgnoreCase("C")) {
            builder.and(board.content.like(keyword));
        } else if (searchBy.equalsIgnoreCase("TC")) {
            builder.and(board.title.like(keyword).or(board.content.like(keyword)));
        }

        JPAQueryFactory qf = new JPAQueryFactory(em);

        JPAQuery query = qf.selectFrom(board)
                .where(builder)
                .orderBy(board.upDate.desc());

        List<Board> list = query.fetch();
        list.forEach(System.out::println);
    }
}
