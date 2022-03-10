package com.epam.task.api;


import com.epam.task.model.MoveModel;
import com.epam.task.model.UserModel;
import com.epam.task.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/users", produces = "application/vnd.api+json")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public @ResponseBody
    ResponseEntity<UserModel>
    getUserById(@PathVariable @NotEmpty String id) {

        return null;

    }

    @PatchMapping("/{id}/moveToken")
    public @ResponseBody
    ResponseEntity<UserModel>
    moveTokenById(@PathVariable @NotEmpty  String id, @RequestBody @Valid MoveModel moveModel) {

        return null;
    }
}
