package com.epam.task;

import com.epam.task.api.UserController;
import com.epam.task.api.error.ErrorHandler;
import com.epam.task.dto.DieDto;
import com.epam.task.dto.UserDto;
import com.epam.task.exception.CanNotAddUserWhenGameIsStartedException;
import com.epam.task.exception.DieAlreadyRolledException;
import com.epam.task.exception.DieMustBeRolledBeforeMakingMoveException;
import com.epam.task.model.UserModel;
import com.epam.task.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class ControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setControllerAdvice(ErrorHandler.class)
                .build();
    }

    @Test
    @Order(1)
    public void makeMoveExceptionTest() throws Exception {
        when(userService.makeMove()).thenThrow(new DieMustBeRolledBeforeMakingMoveException());
        mockMvc.perform(post("/users/makeMove"))
                .andExpect(status().isConflict());

    }


    @Test
    @Order(2)
    public void makeMoveTest() throws Exception {
        when(userService.makeMove()).thenReturn(new UserDto(1,1));
        mockMvc.perform(post("/users/makeMove"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"id\":1,\"currentPlace\":1}"));
    }

    @Test
    @Order(3)
    public void rollDieTest() throws Exception {
        when(userService.rollDie()).thenReturn(new DieDto(4));
        mockMvc.perform(post("/users/rollDie"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"rolledNum\":4}"));
    }
    @Test
    @Order(4)
    public void rollDieTestException() throws Exception {
        when(userService.rollDie()).thenThrow(new DieAlreadyRolledException());
        mockMvc.perform(post("/users/rollDie"))
                .andExpect(status().isConflict());
    }


    @Test
    @Order(5)
    public void addUserExceptionTest() throws Exception {
        when(userService.addUser()).thenThrow(new CanNotAddUserWhenGameIsStartedException());
        mockMvc.perform(post("/users/addUser"))
                .andExpect(status().isConflict());
    }

    @Test
    @Order(6)
    public void addUserTest() throws Exception {
        when(userService.addUser()).thenReturn(new UserDto(2, 1));
        mockMvc.perform(post("/users/addUser"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"id\":2,\"currentPlace\":1}"));
    }


    @Test
    @Order(7)
    public void restartGameTest() throws Exception {
        when(userService.restartGame()).thenReturn(new UserModel(1, 1, 0, true));

        mockMvc.perform(put("/users/restart"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"currentPlace\":1,\"step\":0,\"current\":true}"));
    }

    @Test
    @Order(8)
    public void findUsersTest() throws Exception {
        when(userService.findUsers()).thenReturn(Arrays.asList(new UserModel(1, 1, 0, true),
                new UserModel(2, 1, 0, false)));

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"currentPlace\":1,\"step\":0,\"current\":true}," +
                        "{\"id\":2,\"currentPlace\":1,\"step\":0,\"current\":false}]"));
    }










}