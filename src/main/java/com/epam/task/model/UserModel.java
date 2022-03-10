package com.epam.task.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user_model")
public class UserModel {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;


    @Column(name = "current_place")
    private int currentPlace;

}