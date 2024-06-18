package com.devuoon.backboard.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.devuoon.backboard.entity.Board;



@Controller
@Log4j2
public class MainController {
  private Object boardService;

  @GetMapping("/hello")
  public String getHello() {
    log.info("getHello(); 실행.");
    return "hello";
  }

  @GetMapping("/")
  public String getMain() {
    return "redirect:/board/list"; // localhost:8080/ -> localhost:8080/board/list 변경
  }
}
