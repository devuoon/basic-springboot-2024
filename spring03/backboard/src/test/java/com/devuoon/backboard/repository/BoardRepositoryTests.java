package com.devuoon.backboard.repository;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.devuoon.backboard.repository.BoardRepository;
import com.devuoon.backboard.entity.Board;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class BoardRepositoryTests {
    // JUnit 테스트
    @Autowired
    private BoardRepository boardRepository;

    @Test
    void testInsertBoard() {
        Board board1 = new Board();
        board1.setTitle("test1");
        board1.setContent("Test Content");
        board1.setCreateDate(LocalDateTime.now());
        this.boardRepository.save(board1);
        // Builder를 사용한 객체 생성방식
        Board board2 = Board.builder().title("Test2")
                .content("test2 content")
                .createDate(LocalDateTime.now()).build();
        this.boardRepository.save(board2);
        log.info("success board Test");

    }
}
