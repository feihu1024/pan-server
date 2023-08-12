package com.feihu1024.panserver;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class PanServerApplicationTests {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void contextLoads() {
        String password = passwordEncoder.encode("123456");
        String secret = passwordEncoder.encode("secret");

        System.out.println("password: ----------------------------------------------->>>>>>"+password);
        System.out.println("secret: ----------------------------------------------->>>>>>"+secret);
    }
}
