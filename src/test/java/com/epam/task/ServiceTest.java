package com.epam.task;


import com.epam.task.exception.CanNotAddUserWhenGameIsStartedException;
import com.epam.task.exception.DieAlreadyRolledException;
import com.epam.task.exception.DieMustBeRolledBeforeMakingMoveException;
import com.epam.task.model.UserModel;
import com.epam.task.repository.SnakesAndLaddersRepository;
import com.epam.task.repository.UserRepository;
import com.epam.task.service.UserService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;



    @Test
    public void givenGameStarted_whenAddUser_expectExceptionThrown(){

        when(userRepository.findAll()).thenReturn(Arrays.asList(new UserModel(1, 4, 0, false),
                new UserModel(2, 0, 4, true)));

        Assertions.assertThrows(CanNotAddUserWhenGameIsStartedException.class, () ->  userService.addUser());


    }

    @Test
    public void whenFindAllCalled_thenExpectReturnAll(){
        List<UserModel> lst = Arrays.asList(new UserModel(1, 4, 0, false),
                new UserModel(2, 0, 4, true));

        when(userRepository.findAll()).thenReturn(lst);

        Assertions.assertEquals(lst, userService.findUsers());
    }

    @Test
    public void givenUserDidntRoleDie_whenMakeMoveCalled_expectException(){
        when(userRepository.findByCurrentTrue()).thenReturn(new UserModel(1, 1, 0, true));

        Assertions.assertThrows(DieMustBeRolledBeforeMakingMoveException.class, () -> userService.makeMove());
    }

    @Test
    public void givenUserAlreadyRolledDie_whenCallRollDie_thenExpectException(){
        when(userRepository.rollDie(any(Integer.class))).thenReturn(0);

        Assertions.assertThrows(DieAlreadyRolledException.class, () -> userService.rollDie());
    }

    @Test
    public void whenRestartGame_expectUserReturned(){
        UserModel userModel = new UserModel(1,1,0, true);
        when(userRepository.save(userModel)).thenReturn(userModel);
        Assertions.assertEquals(userModel, userService.restartGame());

    }
}
