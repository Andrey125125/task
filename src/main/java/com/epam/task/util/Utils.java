package com.epam.task.util;

import com.epam.task.dto.UserDto;
import com.epam.task.model.UserModel;

public class Utils {

    public static UserDto UserModelToUserDtoTransformer(UserModel userModel){
        return new UserDto(userModel.getId(), userModel.getCurrentPlace());
    }
}
