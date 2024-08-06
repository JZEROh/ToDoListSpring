package com.smhrd.todolist.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity // jpa
@Table(name = "td_member")
@Data

public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "m_idx")
    private Long idx;

    @Column(name="m_id",length=50 ,unique=true)
    private String id; //컬럼이름 : id, String:varchar, 255
    @Column(name="m_pw",length=50)
    private String pw;
    @Column(name="m_nick",length=50)
    private String nick;
    @Enumerated(EnumType.STRING)
    @Column(name = "m_role")
    private Role role;
}
