package com.uade.financialGame.services.impl;

import com.uade.financialGame.messages.MessageResponse;
import com.uade.financialGame.messages.responses.*;
import com.uade.financialGame.models.*;
import com.uade.financialGame.repositories.*;
import com.uade.financialGame.services.DebugService;
import com.uade.financialGame.utils.PairObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DebugServiceImpl implements DebugService {

    @Autowired
    private UserDAO userRepository;

    @Autowired
    private ProfessionDAO professionRepository;

    @Autowired
    private PlayerDAO playerRepository;

    @Autowired
    private TurnDAO turnRepository;

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
        return new MessageResponse(new PairObject("users", getAllUsers()),
                new PairObject("cards", getAllCards()),
                new PairObject("games", getAllGames()),
                new PairObject("turns", getAllTurns()),
                new PairObject("players", getAllPlayers()),
                new PairObject("professions", getAllProfessions())).getMapObject();
    }

    private List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(User::toDto)
                .collect(Collectors.toList());
    }

    private List<CardResponse> getAllCards() {
        return cardRepository.findAll()
                .stream()
                .map(Card::toDto)
                .collect(Collectors.toList());
    }

    private List<GameResponse> getAllGames() {
        return gameRepository.findAll()
                .stream()
                .map(Game::toDto)
                .collect(Collectors.toList());
    }

    private List<TurnResponse> getAllTurns() {
        return turnRepository.findAll()
                .stream()
                .map(Turn::toDto)
                .collect(Collectors.toList());
    }

    private List<PlayerResponse> getAllPlayers() {
        return playerRepository.findAll()
                .stream()
                .map(Player::toDto)
                .collect(Collectors.toList());
    }

    private List<ProfessionResponse> getAllProfessions() {
        return professionRepository.findAll()
                .stream()
                .map(Profession::toDto)
                .collect(Collectors.toList());
    }

}
