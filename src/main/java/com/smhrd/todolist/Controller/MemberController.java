package com.smhrd.todolist.Controller;

import com.smhrd.todolist.Model.Member;
import com.smhrd.todolist.Repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    @Autowired
    private MemberRepository Repository;

    // 회원가입
    @PostMapping("/join")
    public void join(HttpServletRequest request) {
        String param = request.getParameter("joinMember");
        System.out.println(param);


    }
    // 로그인


}
