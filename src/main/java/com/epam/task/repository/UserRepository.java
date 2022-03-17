package com.epam.task.repository;


import com.epam.task.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Integer> {


    UserModel getUserModelById(int Integer);

    @Transactional
    @Modifying
    @Query("update user_model u set u.step = :step where u.current = true and u.step = 0")
    int rollDie(@Param(value = "step") int step);

    UserModel findByCurrentTrue();

    Optional<UserModel> findById(int id);
}
