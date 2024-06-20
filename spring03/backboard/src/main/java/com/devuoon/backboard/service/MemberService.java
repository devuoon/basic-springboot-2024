package com.devuoon.backboard.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.devuoon.backboard.entity.Member;
import com.devuoon.backboard.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
  
  private final MemberRepository MemberRepository;

  public Member setMember(String name, String email, String password) {
    Member member = Member.builder().username(username).email(email).build();

    BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
    member.setPassword(pwdEncoder.encode(password)); //암호화한 값을 DB에 저장
    this.MemberRepository.save(member);

    return member;
  }
}
