package com.epam.task.exception;


public class DieMustBeRolledBeforeMakingMoveException extends RuntimeException{

    @Override
    public String getMessage() {
        return "You can not make move before you moved die. Please roll die and then make move";
    }

}
