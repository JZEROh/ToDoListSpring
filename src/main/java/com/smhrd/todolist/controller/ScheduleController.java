package com.smhrd.todolist.controller;

import com.smhrd.todolist.Model.Schedule;

import com.smhrd.todolist.jwt.JwtTokenProvider;
import com.smhrd.todolist.service.ScheduleService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final JwtTokenProvider tokenProvider;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Schedule> createSchedule(@RequestBody Schedule schedule, HttpServletRequest request) {
        String token = tokenProvider.resolveToken(request);
        String memberId = tokenProvider.getUserIdFromToken(token); // 변경
        return ResponseEntity.ok(scheduleService.createSchedule(schedule, memberId)); // 변경
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Schedule>> getSchedules(HttpServletRequest request) {
        String token = tokenProvider.resolveToken(request);
        String memberId = tokenProvider.getUserIdFromToken(token);
        return ResponseEntity.ok(scheduleService.getSchedules(memberId));
    }

    @GetMapping("/{scheduleIdx}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Schedule> getScheduleById(@PathVariable Long scheduleIdx, HttpServletRequest request) {
        String token = tokenProvider.resolveToken(request);
        String memberId = tokenProvider.getUserIdFromToken(token);
        return ResponseEntity.ok(scheduleService.getScheduleById(scheduleIdx, memberId));
    }

    @PutMapping("/{scheduleId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Schedule> updateSchedule(@PathVariable Long scheduleId, @RequestBody Schedule schedule, HttpServletRequest request) {
        String token = tokenProvider.resolveToken(request);
        String memberId = tokenProvider.getUserIdFromToken(token);
        // 권한 확인 등 추가적인 로직 필요 (예: 해당 일정의 작성자인지 확인)
        return ResponseEntity.ok(scheduleService.updateSchedule(schedule, memberId));
    }

    @DeleteMapping("/{scheduleId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long scheduleId, HttpServletRequest request) {
        String token = tokenProvider.resolveToken(request);
        String memberId = tokenProvider.getUserIdFromToken(token);
        // 권한 확인 등 추가적인 로직 필요 (예: 해당 일정의 작성자인지 확인)
        scheduleService.deleteSchedule(scheduleId, memberId);
        return ResponseEntity.noContent().build();
    }
}
