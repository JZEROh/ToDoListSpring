package com.smhrd.todolist.Model;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class MembersDetails implements UserDetails {
    private Long idx;
    private String nick;
    private String password; // 비밀번호 (암호화된 상태)
    private Collection<? extends GrantedAuthority> authorities;

    public MembersDetails(Member member) {
        this.idx = member.getIdx();
        this.nick = member.getNick();
        this.password = member.getPw(); // 암호화된 비밀번호
        this.authorities = AuthorityUtils.createAuthorityList("ROLE_USER"); // 기본 권한 설정
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return idx.toString();
    }
}
