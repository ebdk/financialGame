package com.uade.financialGame.services;

import java.util.List;

public interface UserService {

    List<com.uade.financialGame.messages.responses.UserResponse> getAllusers();

    Object getByUsername(String username);

    Object validateByUserNameAndPassword(String userName, String password);

    Object createUser(com.uade.financialGame.messages.requests.UserRequest userRequest);
}
