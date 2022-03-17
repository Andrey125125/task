package com.epam.task.service;


import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class DieService {

    public DieModel rollDie(){
        Random random = new Random();
        int num = random.nextInt(6) + 1;
        return DieModel.builder().num(num).build();
    }
}
