package com.devuoon.backboard.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.devuoon.backboard.entity.Board;
import com.devuoon.backboard.entity.Member;
import com.devuoon.backboard.entity.Reply;
import com.devuoon.backboard.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Service
@Log4j2
public class ReplyService {
   private final ReplyRepository replyRepository;

    public void setReply(Board board, String content, Member writer) {
        Reply reply = Reply.builder().content(content).createDate(LocalDateTime.now()).board(board).build();
        log.info("댓글 객체 생성");
        reply.setWriter(writer);        // 작성자 추가
        this.replyRepository.save(reply);
        log.info("댓글 객체 저장성공");

    }

}

