package com.uade.financialGame.services;

import com.uade.financialGame.messages.MessageResponse;
import com.uade.financialGame.messages.Response;
import com.uade.financialGame.messages.UserDto;

public interface UserService {

    MessageResponse sayHello();

    Response retrieveFirstUser();

    MessageResponse createUser(String userDto);

    Response getByUsername(String username);

    Response validateByUserNameAndPassword(String userName, String password);
}
