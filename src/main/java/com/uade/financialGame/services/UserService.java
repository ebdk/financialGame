package com.uade.financialGame.services;

import com.uade.financialGame.messages.MessageResponse;
import com.uade.financialGame.messages.Response;
import com.uade.financialGame.messages.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> getAllusers();

    Object getByUsername(String username);

    Object validateByUserNameAndPassword(String userName, String password);

    Object createUser(UserDto userDto);
}
