package com.epam.task.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "snakes_ladders")
public class SnakesAndLaddersModel {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    @Column(columnDefinition = "integer default 0")
    private int fromPosition;

    @Column(columnDefinition = "integer default 0")
    private int toPosition;


}

