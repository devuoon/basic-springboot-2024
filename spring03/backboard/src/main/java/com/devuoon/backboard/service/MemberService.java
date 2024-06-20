package com.devuoon.backboard.service;

import org.springframework.stereotype.Service;

import com.devuoon.backboard.entity.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
  
  private final MemberRepository MemberRepository;

  public Member setMember(String name, String email, String password) {
    Memeber member = Member.builder().username(username).email(email).build();
  }
}
