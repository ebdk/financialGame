package com.uade.financialGame.services.impl;

import com.uade.financialGame.messages.MessageResponse;
import com.uade.financialGame.messages.Response;
import com.uade.financialGame.messages.UserDto;
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
    public MessageResponse sayHello() {
        return new MessageResponse(new Pair("prueba", "Hola Mundo"));
        /*return new MessageResponse(new Pair("message", "Invalido"),
                new Pair("reason", "Error, usuario no encontrado"));*/
    }

    @Override
    public Response retrieveFirstUser() {
        Optional<User> persona = userRepository.findById((long) 1);
        return persona.isPresent() ?
                new UserDto(persona.get()) :
                new MessageResponse(new Pair("error", "No hay usuarios"));
    }

    @Override
    public List<UserDto> getAllusers() {
        List<User> personas = userRepository.findAll();
        return personas.stream().map(User::toDto).collect(Collectors.toList());
    }

    @Override
    public MessageResponse createUser(String username, String password) {
        User newUser = new User(username, password);
        userRepository.save(newUser);
        return new MessageResponse(new Pair("message", "Agregado" + newUser.getUserName() + " correctamente."));
    }

    @Override
    public Response getByUsername(String username) {
        Optional<User> persona = userRepository.findByUserName(username);
        return persona.isPresent() ?
                new UserDto(persona.get()) :
                new MessageResponse(new Pair("error", "Error, no pudo ser encontrada la persona con id " + username));
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

    @Override
    public com.uade.financialGame.messages.MessageResponse createUser(com.uade.financialGame.messages.UserDto userDto) {
        userRepository.save(new User(userDto));
        return new MessageResponse("Agregado" + userDto.getUserName() + " correctamente.");
    }


}
