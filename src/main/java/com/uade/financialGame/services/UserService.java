package com.uade.financialGame.services;

import com.uade.financialGame.messages.MessageResponse;
import com.uade.financialGame.messages.Response;
import com.uade.financialGame.messages.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> getAllusers();

    Response getByUsername(String username);

    MessageResponse validateByUserNameAndPassword(String userName, String password);

    MessageResponse createUser(UserDto userDto);
}
