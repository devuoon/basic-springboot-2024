package com.devuoon.backboard.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.devuoon.backboard.controller.common.NotFoundException;
import com.devuoon.backboard.entity.Reset;
import com.devuoon.backboard.repository.ResetRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Service
@Log4j2
public class ResetService {
  
  private final ResetRepository resetRepository;

  public void setReset(String uuid, String email) {
    Reset reset = Reset.builder().uuid(uuid).email(email).regDate(LocalDateTime.now()).build();

    this.resetRepository.save(reset);
    log.info("setReset() 성공!!!");
  }

  public Reset getReset(String uuid) {
    Optional<Reset> _reset = this. resetRepository.findByUuid(uuid);

    if(_reset.isPresent()) {
        log.info("<<<<<<< getReset() 데이터 있음!!!");
        return _reset.get();
    } else {
        throw new NotFoundException("Reset not found!");
    }

  }
}
