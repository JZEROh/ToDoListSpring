package com.smhrd.todolist.Repository;

import com.smhrd.todolist.Model.Member;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByIdAndPw(String id, String pw);
}
