package com.epam.task.api;


import com.epam.task.model.DieModel;
import com.epam.task.service.DieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rollDie", produces = "application/vnd.api+json")
public class DieController {
    private final DieService dieService;

    public DieController(DieService dieService) {
        this.dieService = dieService;
    }


    @GetMapping("")
    ResponseEntity<DieModel> rollDie(){
        return  null;
    }

}
