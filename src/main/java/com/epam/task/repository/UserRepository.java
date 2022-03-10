package com.epam.task.repository;


import com.epam.task.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Integer> {

    UserModel getUserModelById(int id);
}
