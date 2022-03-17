package com.epam.task.exception;

public class CanNotAddUserWhenGameIsStartedException  extends RuntimeException{

    @Override
    public String getMessage() {
        return "You can not add user if gama is started. Please restart game and add user(s) afterwards";
    }

}

