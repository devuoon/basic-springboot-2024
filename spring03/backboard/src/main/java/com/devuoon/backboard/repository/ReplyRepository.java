package com.devuoon.backboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devuoon.backboard.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long>{
  
}
