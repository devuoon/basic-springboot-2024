package com.devuoon.backboard.restcontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devuoon.backboard.dto.Header;
import com.devuoon.backboard.entity.Member;
import com.devuoon.backboard.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@RequestMapping("/api/member")
@RestController
@Log4j2
public class RestmemberController {
  
  private final MemberService memberService;   // 사용자 정보

  @PostMapping("/login")
  public Header<Member> login(@RequestParam Map<String, String> loginInfo) {
      log.info(String.format( " >> React에서 넘어온 정보 : $s", loginInfo.get("username")));

      // 계정정보 객체
      //Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
      String username = loginInfo.get("username");
      String password = loginInfo.get("password");

      try{
        Member member = this.memberService.getMemberByUsernameAndPassword(username, password);

        if (member != null) {
          Header<Member> result = Header.OK(member);
          return result;
        } else {
          Header<Member> fail = Header.OK("Member Not found");
          return fail;
        }
      } catch(Exception e) {
        log.debug(e);
        Header<Member> fail = Header.OK("Member Not found");
        return fail;
      }

  }
  
}
