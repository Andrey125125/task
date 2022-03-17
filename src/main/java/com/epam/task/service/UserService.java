package com.epam.task.service;

import com.epam.task.dto.DieDto;
import com.epam.task.dto.UserDto;
import com.epam.task.model.UserModel;
import com.epam.task.repository.SnakesAndLaddersRepository;
import com.epam.task.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final SnakesAndLaddersRepository snakesAndLaddersRepository;

    public UserService(UserRepository userRepository, SnakesAndLaddersRepository snakesAndLaddersRepository) {
        this.snakesAndLaddersRepository = snakesAndLaddersRepository;
        this.userRepository = userRepository;
    }

    public UserDto makeMove(){
        return null;

    }

    public DieDto rollDie(){


        return null;
    }

    public UserDto addUser(){
        return null;
    }

    public List<UserModel> findUsers(){
        return null;
    }


    public UserModel restartGame(){
        return null;
    }
}
