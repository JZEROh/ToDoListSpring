package com.smhrd.todolist.Controller;

import com.smhrd.todolist.Model.Member;
import com.smhrd.todolist.Repository.MemberRepository;
import com.smhrd.todolist.Service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    @Autowired
    private MemberRepository Repository;
    @Autowired
    private MemberService memberService;

    // 회원가입
    @PostMapping("/join")
    public void join(@RequestBody Member member) {
        memberService.join(member);
        System.out.println(member);
    }
    // 로그인


}
