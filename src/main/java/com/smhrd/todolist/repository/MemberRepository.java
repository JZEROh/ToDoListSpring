package com.smhrd.todolist.repository;

import com.smhrd.todolist.Model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findById(String id); // 아이디로 회원 정보 조회
    boolean existsById(String id); // 아이디 중복 확인
}
