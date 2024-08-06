package com.smhrd.todolist.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smhrd.todolist.Model.Member;
import com.smhrd.todolist.Repository.MemberRepository;
import com.smhrd.todolist.Service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    @Autowired
    private MemberService service;

    // 회원가입
    @PostMapping("/join")
    public String join(HttpServletRequest request) throws JsonProcessingException {
        String param = request.getParameter("joinMember");
        System.out.println(param); //json(String) -> Object(Jackson)

        ObjectMapper om = new ObjectMapper();
        Member m = om.readValue(param,Member.class);

        service.join(m);

        return "Save at DB";
    }
    // 로그인


}
