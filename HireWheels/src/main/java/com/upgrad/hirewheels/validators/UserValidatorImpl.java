package com.upgrad.hirewheels.validators;

import com.upgrad.hirewheels.dtos.LoginDTO;
import com.upgrad.hirewheels.dtos.UserDto;
import com.upgrad.hirewheels.exceptions.APIException;
import org.springframework.stereotype.Service;

@Service
public class UserValidatorImpl implements UserValidator {
    @Override
    public void validateUser(UserDto userDto) throws APIException {
        if(userDto.getEmail() == null || userDto.getEmail().length() <= 0)
            throw new APIException("Invalid username");
        if(userDto.getFirstName() == null || userDto.getFirstName().length() <= 0 )
            throw new APIException("Invalid firstname");
        if(userDto.getLastName() == null || userDto.getLastName().length() <= 0 )
            throw new APIException("Invalid lastname");
        if(userDto.getPassword() == null || userDto.getPassword().length() <= 0   )
            throw new APIException("Invalid password");
        if(userDto.getMobileNo() == null || userDto.getMobileNo().length() <= 0   )
            throw new APIException("Invalid password");

    }

    public void validateuserLogin(LoginDTO user) throws APIException {
        if (user.getEmail() == null || user.getEmail().length() <= 0) {
            throw new APIException("Invalid username");
        }
        if(user.getPassword() == null || user.getPassword().length() <= 0   ) {
            throw new APIException("Invalid password");
        }
    }
}
