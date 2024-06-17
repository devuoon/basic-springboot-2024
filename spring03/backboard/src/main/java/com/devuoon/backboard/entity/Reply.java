package com.devuoon.backboard.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reply {
  
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long rno;

  @Column(name="content", length=1000)
  private String content;

  @CreatedDate
  @Column(name = "createDate", updatable = false)
  private LocalDateTime createDate;

  //중요, RelationShip
  @ManyToOne
  private Board board;
}
