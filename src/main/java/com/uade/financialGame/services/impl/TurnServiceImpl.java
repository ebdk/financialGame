package com.uade.financialGame.services.impl;

import com.uade.financialGame.messages.responses.TurnResponse;
import com.uade.financialGame.models.Card;
import com.uade.financialGame.models.Game;
import com.uade.financialGame.models.Player;
import com.uade.financialGame.models.Turn;
import com.uade.financialGame.repositories.CardDAO;
import com.uade.financialGame.repositories.GameDAO;
import com.uade.financialGame.repositories.PlayerDAO;
import com.uade.financialGame.repositories.TurnDAO;
import com.uade.financialGame.services.TurnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TurnServiceImpl implements TurnService {

    @Autowired
    private GameDAO gameRepository;

    @Autowired
    private CardDAO cardRepository;

    @Autowired
    private PlayerDAO playerRepository;

    @Autowired
    private TurnDAO turnRepository;

    @Override
    public Object createTurn(Long userId, Long cardId, Long boxId, Integer turnNumber) {
        List<Player> allPlayers = playerRepository.findAll();
        Player player = allPlayers
                .stream()
                .filter(x -> userId.equals(x.getUserId()))
                .collect(Collectors.toList())
                .get(0);

        Card card = cardRepository.getOne(cardId);

        Turn turn = new Turn(player, card, turnNumber);
        //turn.calculateBalance();


        turnRepository.save(turn);

        return turn.toDto();
    }

    @Override
    public List<TurnResponse> getPlayerTurns(String playerId) {
        Player player = playerRepository.getOne(Long.valueOf(playerId));
        List<TurnResponse> turns = player.getTurns()
                .stream()
                .map(Turn::toDto)
                .collect(Collectors.toList());

        return turns;
    }

    @Override
    public List<TurnResponse> getTurns(String gameId) {
        Game game = gameRepository.getOne(Long.valueOf(gameId));
        List<TurnResponse> turns = game.getPlayers()
                .stream()
                .flatMap(c -> c.getTurns().stream()) //transforms each user's turns into a singe list
                .map(Turn::toDto)
                .collect(Collectors.toList());

        return turns;
    }
}
