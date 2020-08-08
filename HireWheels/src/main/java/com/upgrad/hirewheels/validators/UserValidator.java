package com.upgrad.hirewheels.validators;

import com.upgrad.hirewheels.dtos.LoginDTO;
import com.upgrad.hirewheels.dtos.UserDto;
import com.upgrad.hirewheels.exceptions.APIException;

public interface UserValidator {

    public void validateUser(UserDto userDto) throws APIException;
    public void validateuserLogin(LoginDTO loginDTO) throws APIException;
}
