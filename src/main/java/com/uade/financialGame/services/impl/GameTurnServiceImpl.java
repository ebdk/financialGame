package com.uade.financialGame.services.impl;

import com.uade.financialGame.messages.GameTurnDto;
import com.uade.financialGame.messages.customRequests.CreateGameTurnRequest;
import com.uade.financialGame.messages.customRequests.GetGameUserTurnsRequest;
import com.uade.financialGame.models.Game;
import com.uade.financialGame.models.GameTurn;
import com.uade.financialGame.models.GameUser;
import com.uade.financialGame.repositories.GameDAO;
import com.uade.financialGame.repositories.GameTurnDAO;
import com.uade.financialGame.repositories.GameUserDAO;
import com.uade.financialGame.services.GameTurnService;
import com.uade.financialGame.messages.customRequests.GetGameTurnsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameTurnServiceImpl implements GameTurnService {

    @Autowired
    private GameDAO gameRepository;

    @Autowired
    private GameUserDAO gameUserRepository;

    @Autowired
    private GameTurnDAO gameTurnRepository;

    @Override
    public Object createGameTurn(CreateGameTurnRequest createGameTurnRequest) {
        return null;
    }

    @Override
    public Object getGameUserTurns(GetGameUserTurnsRequest getGameUserTurnsRequest) {
        GameUser gameUser = gameUserRepository.getOne(getGameUserTurnsRequest.getGameUserId());
        List<GameTurnDto> gameTurns = gameUser.getGameTurns()
                .stream()
                .map(GameTurn::toDto)
                .collect(Collectors.toList());

        return gameTurns;
    }

    @Override
    public Object getGameTurns(GetGameTurnsRequest getGameTurnsRequest) {
        Game game = gameRepository.getOne(getGameTurnsRequest.getGameId());
        List<GameTurnDto> gameTurns = game.getUsers()
                .stream()
                .flatMap(c -> c.getGameTurns().stream()) //transforms each user's turns into a singe list
                .map(GameTurn::toDto)
                .collect(Collectors.toList());

        return gameTurns;
    }
}
