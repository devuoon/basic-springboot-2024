package com.devuoon.backboard.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.devuoon.backboard.service.MemberService;
import com.devuoon.backboard.validation.MemberForm;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



@RequiredArgsConstructor
@RequestMapping("/member")
@Controller
public class MemberController {
  
  private final MemberService memberservice;

  @GetMapping("/login")
  public String login() {
    return "member/login";
  }
  

  @GetMapping("/register")
  public String register(MemberForm memberForm) {
      return "member/register"; // templates/member/register.html
  }

  @PostMapping("register")
  public String register(@Valid MemberForm memberForm, BindingResult bindingResult) {
      if(bindingResult.hasErrors()) {
        return "member/register";
      }
      
      if(!memberForm.getPassword1().equals( memberForm.getPassword2())) {
        bindingResult.rejectValue("password2", "passwordInCorret", "패스워드가 일치하지 않습니다.");
        return "member/register";
      }

     try{
      this.memberservice.setMember(memberForm.getUsername(), memberForm.getEmail(), memberForm.getPassword1());
     } catch  (DataIntegrityViolationException e) {
      e.printStackTrace();
      bindingResult.reject("registerFailed", "이미 등록된 사용자입니다.");
      return "member/register";
     } catch (Exception e) {
      e.printStackTrace();
      bindingResult.reject("registerFailed", e.getMessage());
     }
      return "redirect:/";
  }
  
  
  
}
