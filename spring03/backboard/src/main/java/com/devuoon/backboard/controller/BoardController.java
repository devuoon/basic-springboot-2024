package com.devuoon.backboard.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;  // Correct import for Spring MVC Model
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.devuoon.backboard.entity.Board;
import com.devuoon.backboard.service.BoardService;

import lombok.RequiredArgsConstructor;


@RequestMapping("/board") // Restful URL은 /board로 시작
@Controller
@RequiredArgsConstructor
public class BoardController {
  
  private final BoardService boardservice;

  // @RequestMapping("list", method=RequestMethod.GET) 아래와 동일
  @GetMapping("/list")
  // @RequestMapping("/list", method=RequestMethod.GET)
  // Model -> controller에 있는 객체를 View로 보내주는 역할을 하는 객체
  public String requestMethodName(Model model) {
    List<Board> boardList = this.boardservice.getList();
    model.addAttribute("boardList", boardList); // thymeleaf, mustache, jsp 등 view로 보내는 기능
    return "board/list"; // templates/board/list.html 렌더링해서 리턴하라
  }

   @GetMapping("/detail/{bno}")
  public String detail(Model model, @PathVariable("bno") Long bno) throws Exception {
    Board board = this.boardservice.getBoard(bno);
    model.addAttribute("board", board);

    return "board/detail";
  }

  
  
}
