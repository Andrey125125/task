package com.epam.task;


import com.epam.task.api.UserController;
import com.epam.task.api.error.ErrorHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserController userController;


    @Test
    public void testGetUser() throws Exception {
        mockMvc.perform(get("/users/1")).andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"currentPlace\":1}"));
    }
    @Test
    public void testGetUser_whenNotExist() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setControllerAdvice(new ErrorHandler())
                .build();

        mockMvc.perform(get("/users/378")).andExpect(status().isNotFound());
    }

    @Test
    public void testMoveUser() throws Exception {
        mockMvc.perform(patch("/users/1/moveToken").contentType("application/vnd.api+json")
                        .content("{\"step\":5}")).andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"currentPlace\":6}"));
    }
    @Test
    public void testMoveUser_whenOutOfScope() throws Exception {
        mockMvc.perform(patch("/users/1/moveToken").contentType("application/vnd.api+json")
                .content("{\"step\":8}")).andExpect(status().isBadRequest());
    }

    @Test
    public void testRollDie() throws Exception {
        mockMvc.perform(get("/rollDie")).andExpect(status().isOk());
    }
}
