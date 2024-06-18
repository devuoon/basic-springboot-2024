package com.devuoon.backboard.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.devuoon.backboard.entity.Board;
import com.devuoon.backboard.service.BoardService;

import ch.qos.logback.core.model.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping("/board") // Restful URL은 /board로 시작
@Controller
@RequiredArgsConstructor
public class BoardController {
  
  private final BoardService boardservice;

  // @ResquestMappling("list", method=RequestMethod.GET) 아래와 동일
  @GetMapping("/list")
  // @RequestMapping("/list", method=RequestMethod.GET)
  // Model -> controller에 있는 객체를 View로 보내주는 역할을 하는 객체
  public String requestMethodName(Model model) {
      List<Board> boardList = this.boardservice.getList();
      return "board/list"; // templates/board/list.html 렌더링해서 리턴하라
  }
  
}
