package com.devuoon.backboard.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplyDto {
  
    private Long rno;

    private String content;

    private LocalDateTime createDate; // 글생성일

    private LocalDateTime modifyDate; 

    private String writer; 
}
