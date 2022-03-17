package com.epam.task.api;


import com.epam.task.dto.DieDto;
import com.epam.task.dto.UserDto;
import com.epam.task.model.UserModel;
import com.epam.task.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@RestController
@RequestMapping(value = "/users", produces = "application/vnd.api+json")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @Operation(summary = "Get list of users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful response.", content = {
                    @Content(mediaType = "application/vnd.api+json")
            })
    })
    @GetMapping("")
    public @ResponseBody
    ResponseEntity<List<UserModel>> getUsers(){
        return null;
    }

    @Operation(summary = "restarts the game. Sets # of users to 1")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful response. game restarted", content = {
                    @Content(mediaType = "application/vnd.api+json")
            })
    })
    @PutMapping("/restart")
    public @ResponseBody ResponseEntity<UserModel> restartGame(){
        return null;
    }

    @Operation(summary = "add new user to game")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful response. User added", content = {
                    @Content(mediaType = "application/vnd.api+json")
            }),
            @ApiResponse(responseCode = "409", description = "conflict. attempt to add user when game has started")
    })
    @PostMapping("/addUser")
    public @ResponseBody ResponseEntity<UserDto> addUser(){
        return null;
    }

    @Operation(summary = "roll die")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful response. User added", content = {
                    @Content(mediaType = "application/vnd.api+json")
            }),
            @ApiResponse(responseCode = "409", description = "conflict. attempt to roll die when die is already rolled")
    })
    @PostMapping("/rollDie")
    public @ResponseBody ResponseEntity<DieDto> rollDie(){
        return null;
    }


    @Operation(summary = "move token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful response. User added", content = {
                    @Content(mediaType = "application/vnd.api+json")
            }),
            @ApiResponse(responseCode = "409", description = "conflict. attempt to move token prior to roll die")
    })
    @PostMapping("/makeMove")
    public @ResponseBody ResponseEntity<UserDto> makeMove(){
        return null;
    }



}
