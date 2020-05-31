package com.uade.financialGame.services.impl;

import com.uade.financialGame.messages.MessageResponse;
import com.uade.financialGame.messages.customRequests.CreateGameRequest;
import com.uade.financialGame.models.Game;
import com.uade.financialGame.models.Game.GameDifficulty;
import com.uade.financialGame.models.Game.GameLobbyStatus;
import com.uade.financialGame.models.Game.GameType;
import com.uade.financialGame.models.GameUser;
import com.uade.financialGame.models.User;
import com.uade.financialGame.repositories.GameDAO;
import com.uade.financialGame.repositories.GameUserDAO;
import com.uade.financialGame.repositories.UserDAO;
import com.uade.financialGame.services.GameService;
import com.uade.financialGame.utils.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.uade.financialGame.models.Game.GameLobbyStatus.*;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private UserDAO userRepository;

    @Autowired
    private GameUserDAO gameUserRepository;

    @Autowired
    private GameDAO gameRepository;

    @Override
    public Object createGame(CreateGameRequest createGameRequest) {
        Optional<User> user = userRepository.findById(createGameRequest.getIdUser());
        GameType gameType = GameType.valueOf(createGameRequest.getGameType());
        GameDifficulty gameDifficulty = GameDifficulty.valueOf(createGameRequest.getGameDifficulty());

        GameUser gameUser;
        Game game;

        if(user.isPresent()){
            gameUser = new GameUser(user.get());
        } else {
            return new MessageResponse(new Pair("message", "Invalido"),
                    new Pair("error", "Usuario no encontrado.")).getMapMessage();

        }

        Map<GameLobbyStatus, List<Game>> games = gameRepository.findAll()
                .stream()
                .filter(x -> (gameType.equals(x.getGameType())) && (gameDifficulty.equals(x.getGameDifficulty())) )
                .collect(groupingBy(Game::getStatus));

        if(!(games.get(AWAITING_PLAYERS).isEmpty())){
            List<Game> availableGames = games.get(AWAITING_PLAYERS)
                    .stream()
                    .sorted(comparing(Game::getGameSize))
                    .collect(Collectors.toList());
            game = availableGames.get(0);
        } else if(!(games.get(EMPTY).isEmpty())){
            List<Game> availableGames = games.get(EMPTY);
            game = availableGames.get(0);
        } else {
            game = new Game(gameType, gameDifficulty);
        }

        if(!(FULL.equals(game.getStatus()))){
            game.addGameUser(gameUser);
        } else {
            return new MessageResponse(new Pair("message", "Invalido"),
                    new Pair("error", "Se intento unir a usuario en un juego lleno.")).getMapMessage();
        }

        gameUserRepository.save(gameUser);
        gameRepository.save(game);

        return new MessageResponse(String.format("Agregrado usuario %s al Juego %s, el juego ahora esta %s",
                createGameRequest.getIdUser(),
                game.getGameId(),
                game.getStatus().toString()))
                .getMapMessage();
    }


}
