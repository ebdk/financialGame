package com.uade.financialGame.services.impl;

import com.uade.financialGame.messages.customRequests.CreateGameTurnRequest;
import com.uade.financialGame.messages.responses.GameTurnResponse;
import com.uade.financialGame.models.Card;
import com.uade.financialGame.models.Game;
import com.uade.financialGame.models.GameTurn;
import com.uade.financialGame.models.GameUser;
import com.uade.financialGame.repositories.CardDAO;
import com.uade.financialGame.repositories.GameDAO;
import com.uade.financialGame.repositories.GameTurnDAO;
import com.uade.financialGame.repositories.GameUserDAO;
import com.uade.financialGame.services.GameTurnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameTurnServiceImpl implements GameTurnService {

    @Autowired
    private GameDAO gameRepository;

    @Autowired
    private CardDAO cardRepository;

    @Autowired
    private GameUserDAO gameUserRepository;

    @Autowired
    private GameTurnDAO gameTurnRepository;

    @Override
    public Object createGameTurn(Long userId, Long cardId, Long boxId, Integer turnNumber) {
        List<GameUser> allGameUsers = gameUserRepository.findAll();
        GameUser gameUser = allGameUsers
                .stream()
                .filter(x -> userId.equals(x.getUserId()))
                .collect(Collectors.toList())
                .get(0);

        Card card = cardRepository.getOne(cardId);

        GameTurn gameTurn = new GameTurn(gameUser, card, turnNumber);
        //gameTurn.calculateBalance();


        gameTurnRepository.save(gameTurn);

        return gameTurn.toDto();
    }

    @Override
    public List<GameTurnResponse> getGameUserTurns(String gameUserId) {
        GameUser gameUser = gameUserRepository.getOne(Long.valueOf(gameUserId));
        List<GameTurnResponse> gameTurns = gameUser.getGameTurns()
                .stream()
                .map(GameTurn::toDto)
                .collect(Collectors.toList());

        return gameTurns;
    }

    @Override
    public List<GameTurnResponse> getGameTurns(String gameId) {
        Game game = gameRepository.getOne(Long.valueOf(gameId));
        List<GameTurnResponse> gameTurns = game.getUsers()
                .stream()
                .flatMap(c -> c.getGameTurns().stream()) //transforms each user's turns into a singe list
                .map(GameTurn::toDto)
                .collect(Collectors.toList());

        return gameTurns;
    }
}
