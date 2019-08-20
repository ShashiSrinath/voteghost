package com.voteghost.service;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.*;

public class UserDetailsServiceimplTest {
    @Test
    public void generateEncryptedPassword() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "password123";
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println("password:"+encodedPassword);
        assertNotEquals(rawPassword,encodedPassword);
    }
}