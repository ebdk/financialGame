package com.uade.financialGame.services;

import com.uade.financialGame.messages.MessageResponse;
import com.uade.financialGame.messages.Response;
import com.uade.financialGame.messages.UserDto;
import com.uade.financialGame.models.User;

import java.util.List;

public interface UserService {

    MessageResponse sayHello();

    Response retrieveFirstUser();

    List<UserDto> getAllusers();

    MessageResponse createUser(String username, String password);

    Response getByUsername(String username);

    Response validateByUserNameAndPassword(String userName, String password);
}
