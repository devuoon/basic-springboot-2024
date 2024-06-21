package com.devuoon.backboard.service;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.devuoon.backboard.entity.Member;
import com.devuoon.backboard.repository.MemberRepository;
import com.devuoon.backboard.security.MemberRole;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
    
    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    public Member setMember(String username, String email, String password){
        Member member = Member.builder().username(username).email(email).regDate(LocalDateTime.now()).build();

        // ... 처리되는 일이 많아서 1~2초시간이 걸리면 
        // BcryptPasswordEncoder 매번 새롭게 객체 생성 -> Bean 등록이 더 좋음(유지보수)
        // BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
        member.setPassword(passwordEncoder.encode(password)); // 암호화한 값을 DB에 저장 준비
        member.setRegDate(LocalDateTime.now());
        member.setRole(MemberRole.USER);    //일반사용자 권한
        this.memberRepository.save(member);

        return member;
    }
}
