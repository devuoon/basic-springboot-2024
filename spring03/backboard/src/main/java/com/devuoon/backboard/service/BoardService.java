package com.devuoon.backboard.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.devuoon.backboard.entity.Board;
import com.devuoon.backboard.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {
  private final BoardRepository boardRepository;

  public List<Board> getList() {
    return this.boardRepository.findAll();
  }
}
