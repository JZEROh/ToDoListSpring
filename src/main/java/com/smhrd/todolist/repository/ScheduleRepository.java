package com.smhrd.todolist.repository;

import com.smhrd.todolist.Model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByMemberId(String memberId);
    Optional<Schedule> findByIdxAndMemberId(Long scheduleIdx, String memberId);
}
