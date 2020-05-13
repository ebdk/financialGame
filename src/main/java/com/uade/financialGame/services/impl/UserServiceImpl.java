package com.uade.financialGame.services.impl;

import com.uade.financialGame.messages.MessageResponse;
import com.uade.financialGame.messages.Response;
import com.uade.financialGame.messages.UserDto;
import com.uade.financialGame.models.User;
import com.uade.financialGame.repositories.UserDAO;
import com.uade.financialGame.services.UserService;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userRepository;

    @Override
    public MessageResponse sayHello() {
        //return new MessageResponse(new Pair("prueba", "Hola Mundo"));
        return new MessageResponse(new Pair("message", "Invalido"),
                new Pair("reason", "Error, usuario no encontrado"));
    }

    @Override
    public Response retrieveFirstUser() {
        Optional<User> persona = userRepository.findById((long) 1);
        return persona.isPresent() ?
                new UserDto(persona.get()) :
                new MessageResponse(new Pair("error", "No hay usuarios"));
    }

    @Override
    public MessageResponse createUser(UserDto userRequest) {
        userRepository.save(new User(userRequest));
        return new MessageResponse(new Pair("message", "Agregado" + userRequest.getUserName() + " correctamente."));
    }

    @Override
    public Response getById(Long id) {
        Optional<User> persona = userRepository.findById(id);
        return persona.isPresent() ?
                new UserDto(persona.get()) :
                new MessageResponse(new Pair("error", "Error, no pudo ser encontrada la persona con id " + id));
    }

    @Override
    public Response validateByUserNameAndPassword(String userName, String password) {
        Optional<User> persona = userRepository.findByUserName(userName);

        return persona.isPresent() ?
                (persona.get().getPassword().equals(password) ?
                        new MessageResponse(new Pair("message", "Valido")) :
                        new MessageResponse(new Pair("message", "Invalido"),
                                new Pair("reason", "Error, usuario y contraseña no concuerdan"))
                        ) :
                new MessageResponse(new Pair("message", "Invalido"),
                        new Pair("reason", "Error, usuario no encontrado"));

    }


}
