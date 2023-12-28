package com.nurbakyt.sporttime;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class SporttimeApplicationTests {

    @Test
    void passwordTest() {
        System.out.println(
                new BCryptPasswordEncoder().encode("1234")
        );

    }

}
