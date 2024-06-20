package com.devuoon.backboard.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.devuoon.backboard.entity.Board;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardRepository extends JpaRepository<Board, Long>{
  
    // 메서드명만 잘 만들면 쿼리를 생성하지 않고 JPA 만들어주는 기능
    Optional<Board> findByBno(Long bno);
    // Board findByContent(String content);
    Board findByTitle(String title);
    List<Board> findByTitleLike(String title);

    // 페이징용 JPA 쿼리 자동생성 인터페이스 메서드 작성
    @SuppressWarnings("null")   // 경고 메시지 없애주는 어노테이션
    Page<Board> findAll(Pageable pageable);
}
