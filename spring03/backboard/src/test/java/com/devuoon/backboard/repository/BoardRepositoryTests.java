package com.devuoon.backboard.repository;

// import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.devuoon.backboard.entity.Board;
import com.devuoon.backboard.service.BoardService;

// import java.util.List;
import java.util.Optional;

@SpringBootTest
public class BoardRepositoryTests {
    // JUnit 테스트
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardService boardService;
    
    @Test
    void testThreeHundredBoards() {
        for (int i = 0; i < 400; i++) {
            this.boardService.setBoard(String.format("테스트 데이터 - [%03d]", i + 1),
                                            "별 내용 없습니다.");
        }
    }
    
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
    }

    // @Test
    // void testSelectBoard() {
    //     List<Board> all = this.boardRepository.findAll(); // select * from board
    //     assertEquals(4, all.size());

    //     Board bd = all.get(0); // 게시글 중 첫번째 값
    //     assertEquals(1, bd.getBno());
    // }

    @Test
    void testUpdateBoard() {
        Optional<Board> bd = this.boardRepository.findById(1L);
        assertTrue(bd.isPresent()); // bno가 1번인 게시글이 객체 넘어왔는지 확인
        Board ubd = bd.get();
        ubd.setContent("테스트로 수정합니다.");
        this.boardRepository.save(ubd); // save() id가 없으면 INSERT, 있으면 UPDATE 쿼리 자동 실행
    }

    // @Test
    // void testDeleteBoard() {
    //     Optional<Board> bd = this.boardRepository.findById(2L);
    //     assertTrue(bd.isPresent());
    //     Board dbd = bd.get();
    //     this.boardRepository.delete(dbd); // delete 쿼리 실행
    //     System.out.println("삭제완료");
    // }
}
