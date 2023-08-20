package com.mo.jpastudy;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    //카운트
    int countAllByWriter(String writer);

    List<Board> findBoardByWriter(String writer);

    List<Board> findBoardByTitleAndWriter(String title, String writer);


    @Transactional
    int deleteByWriter(String writer);
}
