package com.epam.task;

import com.epam.task.model.UserModel;
import com.epam.task.repository.UserRepository;
import com.epam.task.service.DieService;
import com.epam.task.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.when;

@SpringBootTest
public class ServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    DieService dieService;

    @MockBean
    UserRepository userRepository;

    @Test
    public void testGetCurrentPosition(){
        UserModel userModel = new UserModel(1,5);
        when(userRepository.getUserModelById(userModel.getId())).thenReturn(userModel);
        Assertions.assertEquals(userModel, userService.getCurrentPosition(userModel.getId()));



    }

    @Test
    public void testMoveToken(){
        UserModel userModel = new UserModel(1,5);
        when(userRepository.save(userModel)).thenReturn(userModel);
        when(userRepository.getUserModelById(userModel.getId())).thenReturn(userModel);
        int steps = 5;
        Assertions.assertEquals(new UserModel(userModel.getId(), userModel.getCurrentPlace() + steps),
                userService.moveToken(userModel.getId(), steps));

    }

    @Test
    public void testMoveToken2(){
        UserModel userModel = new UserModel(1,97);
        when(userRepository.save(userModel)).thenReturn(userModel);
        when(userRepository.getUserModelById(userModel.getId())).thenReturn(userModel);
        int steps = 5;
        Assertions.assertEquals(userModel,
                userService.moveToken(userModel.getId(), steps));

    }

    @Test
    public void testRollDie(){
        int num;
        int[] arr = new int[7];
        for (int i = 0; i < 50_000; i++) {
            num = dieService.rollDie().getNum();
            Assertions.assertTrue(num >= 1 && num <= 6);
            arr[num] = 1;


        }
        for (int i = 1; i < 7; i++) {
            Assertions.assertEquals(1, arr[i]);
        }
    }
}
