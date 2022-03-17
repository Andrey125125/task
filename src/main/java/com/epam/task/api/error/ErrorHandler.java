package com.epam.task.api.error;

import com.epam.task.exception.CanNotAddUserWhenGameIsStartedException;
import com.epam.task.exception.DieAlreadyRolledException;
import com.epam.task.exception.DieMustBeRolledBeforeMakingMoveException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(DieAlreadyRolledException.class)
    public ResponseEntity handle(DieAlreadyRolledException e) {
        return  ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler(CanNotAddUserWhenGameIsStartedException.class)
    public ResponseEntity handle(CanNotAddUserWhenGameIsStartedException e) {
        return  ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler(DieMustBeRolledBeforeMakingMoveException.class)
    public ResponseEntity handle(DieMustBeRolledBeforeMakingMoveException e) {
        return  ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
}
