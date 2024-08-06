package com.smhrd.todolist.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "schedule")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "s_idx")
    private Long idx;

    @Column(name = "s_title", length = 255)
    private String title;

    @Column(name = "s_content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "s_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(name = "s_member_id")
    private String memberId;
}
