package com.mo.jpastudy;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query("SELECT b FROM Board b")
    List<Board> findAllBoard(); //메서드 이름 상관없음

    @Query("SELECT b FROM Board b WHERE b.title = :title")
    List<Board> findBoardByTitle(@Param("title") String title);

    //카운트
    int countAllByWriter(String writer);

    List<Board> findBoardByWriter(String writer);

    List<Board> findBoardByTitleAndWriter(String title, String writer);

    @Transactional
    int deleteByWriter(String writer);
}
