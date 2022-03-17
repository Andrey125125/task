package com.epam.task.service;

import com.epam.task.dto.DieDto;
import com.epam.task.dto.UserDto;
import com.epam.task.exception.CanNotAddUserWhenGameIsStartedException;
import com.epam.task.exception.DieAlreadyRolledException;
import com.epam.task.exception.DieMustBeRolledBeforeMakingMoveException;
import com.epam.task.model.SnakesAndLaddersModel;
import com.epam.task.model.UserModel;
import com.epam.task.repository.SnakesAndLaddersRepository;
import com.epam.task.repository.UserRepository;
import com.epam.task.util.Utils;
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
        UserModel user = userRepository.findByCurrentTrue();

        int step = user.getStep();
            if (step == 0) throw new DieMustBeRolledBeforeMakingMoveException();

        int nextPlace = user.getCurrentPlace() + step > 100 ? user.getCurrentPlace() : user.getCurrentPlace() + step;

        Optional<SnakesAndLaddersModel> jump = snakesAndLaddersRepository.findByFromPositionEquals(nextPlace);

        if (jump.isPresent()) nextPlace = jump.get().getToPosition();

        userRepository.save(new UserModel(user.getId(), nextPlace, 0, false));

        Optional<UserModel> nextUser = userRepository.findById(user.getId() + 1);

        if (nextUser.isPresent()){
            nextUser.get().setCurrent(true);
            userRepository.save(nextUser.get());
        } else {
            UserModel userModel = userRepository.getUserModelById(1);
            userModel.setCurrent(true);
            userRepository.save(userModel);
        }

        return new UserDto(user.getId(), nextPlace);

    }

    public DieDto rollDie(){


        Random random = new Random();
        int num = random.nextInt(6) + 1;

        int rolled = userRepository.rollDie(num);
        if (rolled == 0) throw new DieAlreadyRolledException();
        return DieDto.builder().rolledNum(num).build();
    }

    public UserDto addUser(){
        List<UserModel> users = userRepository.findAll();
        users.forEach( user -> {if (user.getCurrentPlace() != 1)
            throw new CanNotAddUserWhenGameIsStartedException();  });

        UserModel userModel = new UserModel(users.size() + 1, 1, 0, false);
        userRepository.save(userModel);
        return Utils.UserModelToUserDtoTransformer(userModel);
    }

    public List<UserModel> findUsers(){
        return userRepository.findAll();
    }


    public UserModel restartGame(){
        userRepository.deleteAll();
        UserModel userModel = new UserModel(1,1,0, true);
        userModel = userRepository.save(userModel);
        return userModel;
    }
}
