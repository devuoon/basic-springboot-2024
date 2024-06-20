package com.devuoon.backboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devuoon.backboard.entity.Board;
import java.util.List;


public interface BoardRepository extends JpaRepository<Board, Long>{
  
    // 메서드명만 잘 만들면 쿼리를 생성하지 않고 JPA 만들어주는 기능
    Board findByBno(Long bno);
    // Board findByContent(String content);
    Board findByTitle(String title);
    List<Board> findByTitleLike(String title);
    
}
