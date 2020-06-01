package com.uade.financialGame.services.impl;

import com.google.gson.Gson;
import com.uade.financialGame.messages.MessageResponse;
import com.uade.financialGame.models.*;
import com.uade.financialGame.repositories.*;
import com.uade.financialGame.services.DebugService;
import com.uade.financialGame.utils.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class DebugServiceImpl implements DebugService {

    @Autowired
    private UserDAO userRepository;

    @Autowired
    private ProfessionDAO professionRepository;

    @Autowired
    private GameUserDAO gameUserRepository;

    @Autowired
    private GameTurnDAO gameTurnRepository;

    @Autowired
    private GameDAO gameRepository;

    @Autowired
    private CardDAO cardRepository;

    @Override
    public Object ping() {
        return new MessageResponse("Pong.").getMapMessage();
    }

    @Override
    public Object getAllEntities() {
        return new MessageResponse(new Pair("users", getAllUsers()),
                new Pair("cards", getAllCards()),
                new Pair("games", getAllGames()),
                new Pair("gameTurns", getAllGameTurns()),
                new Pair("gameUsers", getAllGameUsers()),
                new Pair("professions", getAllProfessions())).getMapMessage()
                ;
    }

    private String getAllUsers() {
        return new Gson().toJson(userRepository.findAll()
                .stream()
                .map(User::toDto)
                .collect(Collectors.toList()));
    }

    private String getAllCards() {
        return new Gson().toJson(cardRepository.findAll()
                .stream()
                .map(Card::toDto)
                .collect(Collectors.toList()));
    }

    private String getAllGames() {
        return new Gson().toJson(gameRepository.findAll()
                .stream()
                .map(Game::toDto)
                .collect(Collectors.toList()));
    }

    private String getAllGameTurns() {
        return new Gson().toJson(gameTurnRepository.findAll()
                .stream()
                .map(GameTurn::toDto)
                .collect(Collectors.toList()));
    }

    private String getAllGameUsers() {
        return new Gson().toJson(gameUserRepository.findAll()
                .stream()
                .map(GameUser::toDto)
                .collect(Collectors.toList()));
    }

    private String getAllProfessions() {
        return new Gson().toJson(professionRepository.findAll()
                .stream()
                .map(Profession::toDto)
                .collect(Collectors.toList()));
    }

}
