package com.epam.task;

import com.epam.task.model.SnakesAndLaddersModel;
import com.epam.task.model.UserModel;
import com.epam.task.repository.SnakesAndLaddersRepository;
import com.epam.task.repository.UserRepository;
import com.epam.task.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = {
        "classpath:applicationTest.yaml"})
@SpringBootTest
public class ServiceIntegrationTest {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SnakesAndLaddersRepository snakesAndLaddersRepository;

    @Before
    public void init(){
        userRepository.deleteAll();
        snakesAndLaddersRepository.deleteAll();
    }

    @Test
    public void givenAfterStepUserWillBeOutOfBorder_whenMakeMoveCalled_expectUserStayOnSamePlace(){

        userRepository.save(  new UserModel(1, 95, 6, true)  );

        userService.makeMove();

        Assertions.assertEquals(95, userRepository.getUserModelById(1).getCurrentPlace());

    }

    @Test
    public void givenAfterStepUserWillBeInBorder_whenMakeMoveCalled_expectUserMoved(){

        userRepository.save(  new UserModel(1, 90, 6, true)  );

        userService.makeMove();

        Assertions.assertEquals(96, userRepository.getUserModelById(1).getCurrentPlace());

    }

    @Test
    public void givenAfterStepUserWillBeInLadder_whenMakeMoveCalled_expectUserMovedInLadder(){

        userRepository.save(  new UserModel(1, 1, 6, true)  );

        snakesAndLaddersRepository.save(new SnakesAndLaddersModel(1, 7, 20));

        userService.makeMove();

        Assertions.assertEquals(20, userRepository.getUserModelById(1).getCurrentPlace());

    }

    @Test
    public void whenUserMoved_expectNextUserToBeCurrent(){

        userRepository.save(  new UserModel(1, 1, 6, true)  );
        userRepository.save(  new UserModel(2, 1, 0, false)  );

        userService.makeMove();

        Assertions.assertFalse(userRepository.getUserModelById(1).isCurrent());
        Assertions.assertTrue(userRepository.getUserModelById(2).isCurrent());

    }

    @Test
    public void whenUserMoved_expectNextUserToBeCurrent2(){

        userRepository.save(  new UserModel(1, 5, 0, false)  );
        userRepository.save(  new UserModel(2, 1, 4, true)  );

        userService.makeMove();

        Assertions.assertFalse(userRepository.getUserModelById(2).isCurrent());
        Assertions.assertTrue(userRepository.getUserModelById(1).isCurrent());
    }


    @Test
    public void whenDieRolled_expectStepToBeInBetween1And6(){

        userRepository.save(  new UserModel(1, 5, 0, true)  );
        userService.rollDie();

        int step = userRepository.getUserModelById(1).getStep();
        Assertions.assertTrue(step > 0 && step <=6);

    }

    @Test
    public void testAddingUsers() {
        userRepository.save( new UserModel(1, 1, 0, true)  );
        userService.addUser();
        userService.addUser();

        UserModel user2 = userRepository.getUserModelById(2);
        UserModel user3 = userRepository.getUserModelById(3);

        Assertions.assertEquals( new UserModel(2, 1, 0, false), user2);
        Assertions.assertEquals( new UserModel(3, 1, 0, false), user3);

    }

    @Test
    public void testRestartingGame() {

        userRepository.save( new UserModel(1, 1, 0, true)  );
        userService.addUser();
        userService.addUser();

        userService.restartGame();

        List<UserModel> users = userRepository.findAll();

        Assertions.assertEquals(1, users.size());

        Assertions.assertEquals(new UserModel(1, 1, 0, true),  users.get(0));
    }

}
