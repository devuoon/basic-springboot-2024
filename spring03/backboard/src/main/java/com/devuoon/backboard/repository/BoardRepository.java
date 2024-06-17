package com.devuoon.backboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devuoon.backboard.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{
  
}
