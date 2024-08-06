package com.smhrd.todolist.controller;

import com.smhrd.todolist.Model.JwtToken;
import com.smhrd.todolist.Model.LoginDto;
import com.smhrd.todolist.Model.Member;
import com.smhrd.todolist.Model.Role;
import com.smhrd.todolist.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/sign-in")
    public JwtToken signIn(@RequestBody LoginDto LoginDto) {
        String id = LoginDto.getId();
        String pw = LoginDto.getPw();
        JwtToken jwtToken = memberService.signIn(id, pw);
        log.info("request id = {}, pw = {}", id, pw);
        log.info("jwtToken accessToken = {}, refreshToken = {}", jwtToken.getAccessToken(), jwtToken.getRefreshToken());
        return jwtToken;
    }
    @PostMapping("/sign-up")
    public ResponseEntity<String> signup(@RequestBody Member member) {
        try {
            memberService.signup(member);
            return ResponseEntity.ok("회원가입 성공");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/test")
    public String test() {
        return "success";
    }

}