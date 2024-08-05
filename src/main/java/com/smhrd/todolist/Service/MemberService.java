package com.smhrd.todolist.Service;

import com.smhrd.todolist.Model.Member;
import com.smhrd.todolist.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private MemberRepository Repository;

    // 회원가입
    public void join(Member m) {
        Repository.save(m);
    }

    // 로그인
    public Optional<Member> login(Member m){

        return Repository.findByIdAndPw(m.getId(),m.getPw());
    }
}
