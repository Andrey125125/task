package com.epam.task;

import com.epam.task.model.UserModel;
import com.epam.task.repository.UserRepository;
import com.epam.task.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource("/applicationTest.yaml")
@SpringBootTest
public class ServiceIntegrationTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    public void test1() {

        userService.moveToken(1, 5);
        userService.moveToken(1, 3);

        UserModel user1 = userService.getCurrentPosition(1);

        Assertions.assertEquals(9, user1.getCurrentPlace());


    }


    @Test
    public void test2() {

        userRepository.save(new UserModel(1, 94));

        userService.moveToken(1, 5);
        userService.moveToken(1, 3);

        UserModel user1 = userService.getCurrentPosition(1);

        Assertions.assertEquals(99, user1.getCurrentPlace());


    }

}
