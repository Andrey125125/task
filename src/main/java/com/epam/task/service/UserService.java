package com.epam.task.service;

import com.epam.task.model.UserModel;
import com.epam.task.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserModel getCurrentPosition(int id){
        return userRepository.getUserModelById(id);
    }

    public UserModel moveToken(int id, int steps){
        UserModel userModel = userRepository.getUserModelById(id);

        if (userModel.getCurrentPlace() + steps > 100) return userModel;

        userModel.setCurrentPlace(userModel.getCurrentPlace() + steps);

        userRepository.save(userModel);

        return userModel;
    }
}
