package com.smhrd.todolist.service;

import com.smhrd.todolist.Model.Schedule;
import com.smhrd.todolist.repository.MemberRepository;
import com.smhrd.todolist.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final MemberRepository memberRepository; // 필요에 따라 추가

    @Transactional
    public Schedule createSchedule(Schedule schedule, String memberId) {
        schedule.setMemberId(memberId);
        return scheduleRepository.save(schedule);
    }

    @Transactional(readOnly = true)
    public List<Schedule> getSchedules(String memberId) {
        return scheduleRepository.findByMemberId(memberId);
    }

    @Transactional(readOnly = true)
    public Schedule getScheduleById(Long scheduleIdx, String memberId) {
        return scheduleRepository.findByIdxAndMemberId(scheduleIdx, memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정을 찾을 수 없습니다."));
    }

    @Transactional
    public Schedule updateSchedule(Schedule schedule, String memberId) {
        return scheduleRepository.save(schedule);
    }

    @Transactional
    public void deleteSchedule(Long scheduleId, String memberId) {
        scheduleRepository.deleteById(scheduleId);
    }
}
