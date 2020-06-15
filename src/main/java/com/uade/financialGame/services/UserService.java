package com.uade.financialGame.services;

import com.uade.financialGame.messages.requests.UserRequest;
import com.uade.financialGame.messages.responses.UserResponse;

import java.util.List;

public interface UserService {

    List<UserResponse> getAllusers();

    Object getByUsername(String username);

    Object validateByUserNameAndPassword(String userName, String password);

    Object createUser(UserRequest userRequest);

    Object updateCoins(String username, Integer coinsValue);
}
