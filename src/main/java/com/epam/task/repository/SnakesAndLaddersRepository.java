package com.epam.task.repository;

import com.epam.task.model.SnakesAndLaddersModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SnakesAndLaddersRepository extends JpaRepository<SnakesAndLaddersModel, Integer> {

    Optional<SnakesAndLaddersModel> findByFromPositionEquals(int from);

}
