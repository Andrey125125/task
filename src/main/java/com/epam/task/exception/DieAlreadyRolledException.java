package com.epam.task.exception;

public class DieAlreadyRolledException extends RuntimeException{
    @Override
    public String getMessage() {
        return "Die already rolled! You should make move";
    }
}
