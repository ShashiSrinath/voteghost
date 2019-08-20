package com.voteghost.security;

import com.voteghost.domain.User;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
public class Authority implements GrantedAuthority {
    private static final long serialVersionUID = 7783382592913361465L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String authority;
    @ManyToOne
    private User user;

    @Override
    public String getAuthority() {
        return this.authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
