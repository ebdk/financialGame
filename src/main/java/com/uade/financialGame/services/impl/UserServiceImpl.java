package com.uade.financialGame.services.impl;

import com.uade.financialGame.messages.MessageResponse;
import com.uade.financialGame.messages.requests.UserRequest;
import com.uade.financialGame.messages.responses.UserResponse;
import com.uade.financialGame.models.User;
import com.uade.financialGame.repositories.UserDAO;
import com.uade.financialGame.services.UserService;
import com.uade.financialGame.utils.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userRepository;

    @Override
    public List<UserResponse> getAllusers() {
        List<User> personas = userRepository.findAll();
        return personas.stream().map(User::toDto).collect(Collectors.toList());
    }

    @Override
    public Object getByUsername(String username) {
        Optional<User> persona = userRepository.findByUserName(username);
        return persona.isPresent() ?
                new UserResponse(persona.get()) :
                new MessageResponse(new Pair("error", "Error, no pudo ser encontrada la persona con id " + username)).getMapMessage();
    }

    @Override
    public Object validateByUserNameAndPassword(String userName, String password) {
        Optional<User> persona = userRepository.findByUserName(userName);

        return persona.isPresent() ?
                (persona.get().getPassword().equals(password) ?
                        new MessageResponse(new Pair("message", "Valido")).getMapMessage() :
                        new MessageResponse(new Pair("message", "Invalido"),
                                new Pair("error", "Usuario y contraseña no concuerdan")).getMapMessage()
                        ) :
                new MessageResponse(new Pair("message", "Invalido"),
                        new Pair("error", "Usuario no encontrado")).getMapMessage();

    }

    @Override
    public Object createUser(UserRequest userRequest) {
        User newUser = new User(userRequest);
        userRepository.save(newUser);
        return newUser.toDto();
    }

    @Override
    public Object updateCoins(String username, Integer coinsValue) {
        Integer result = null;
        Optional<User> userSearch = userRepository.findByUserName(username);
        if(userSearch.isPresent()){
            User user = userSearch.get();
            result = user.getCoins() + coinsValue;
            user.setCoins(result);
            userRepository.save(user);
        } else {
            new MessageResponse(new Pair("message", "Invalido"),
                    new Pair("error", "Usuario no encontrado")).getMapMessage();
        }
        return result;
    }


}
