package main.java.com.devuoon.spring02.domain;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class Todo {
  private Integer tno;

  private String title;

  private LocalDateTime dueDate;

  private String writer;

  private Integer isDone;
  
}
