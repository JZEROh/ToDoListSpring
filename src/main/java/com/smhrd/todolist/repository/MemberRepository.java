package com.smhrd.todolist.repository;

import com.smhrd.todolist.Model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findById(String id);
}
