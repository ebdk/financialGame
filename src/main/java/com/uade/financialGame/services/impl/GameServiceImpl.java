package com.uade.financialGame.services.impl;

import com.uade.financialGame.messages.MessageResponse;
import com.uade.financialGame.models.Game;
import com.uade.financialGame.models.Game.GameDifficulty;
import com.uade.financialGame.models.Game.GameLobbyStatus;
import com.uade.financialGame.models.Game.GameType;
import com.uade.financialGame.models.Player;
import com.uade.financialGame.models.Player.PlayerType;
import com.uade.financialGame.models.Profession;
import com.uade.financialGame.models.User;
import com.uade.financialGame.models.User.UserRank;
import com.uade.financialGame.repositories.GameDAO;
import com.uade.financialGame.repositories.PlayerDAO;
import com.uade.financialGame.repositories.ProfessionDAO;
import com.uade.financialGame.repositories.UserDAO;
import com.uade.financialGame.services.GameService;
import com.uade.financialGame.utils.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.uade.financialGame.models.Game.GAME_FULL_NUMBER;
import static com.uade.financialGame.models.Game.GameLobbyStatus.*;
import static com.uade.financialGame.models.Player.PlayerType.HUMAN;
import static com.uade.financialGame.utils.MathUtils.generateRandomNumber;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private UserDAO userRepository;

    @Autowired
    private PlayerDAO playerRepository;

    @Autowired
    private GameDAO gameRepository;

    @Autowired
    private ProfessionDAO professionRepository;

    @Override
    public Object createGame(String gameTypeParam, String gameDifficultyParam, String idUser) {
        Optional<User> user = userRepository.findById(Long.valueOf(idUser));
        GameType gameType = GameType.valueOf(gameTypeParam);
        GameDifficulty gameDifficulty = GameDifficulty.valueOf(gameDifficultyParam);

        Player player;
        Game game;

        if(user.isPresent()){
            player = new Player(user.get(), HUMAN);
        } else {
            return new MessageResponse(new Pair("message", "Invalido"),
                    new Pair("error", "Usuario no encontrado.")).getMapMessage();
        }

        Map<GameLobbyStatus, List<Game>> games = gameRepository.findAll()
                .stream()
                .filter(x -> (gameType.equals(x.getGameType())) && (gameDifficulty.equals(x.getGameDifficulty())) )
                .collect(groupingBy(Game::getStatus));

        if(!(games.getOrDefault(AWAITING_PLAYERS, new ArrayList<>()).isEmpty())){
            List<Game> availableGames = games.get(AWAITING_PLAYERS)
                    .stream()
                    .sorted(comparing(Game::getGameSize))
                    .collect(toList());
            game = availableGames.get(0);
        } else if(!(games.getOrDefault(EMPTY, new ArrayList<>()).isEmpty())){
            List<Game> availableGames = games.get(EMPTY);
            game = availableGames.get(0);
        } else {
            game = new Game(gameType, gameDifficulty);
        }

        if(!(FULL.equals(game.getStatus()))){
            game.addPlayer(player);
            player.setGame(game);
        } else {
            return new MessageResponse(new Pair("message", "Invalido"),
                    new Pair("error", "Se intento unir a usuario en un juego lleno.")).getMapMessage();
        }

        playerRepository.save(player);
        gameRepository.save(game);

        return game.toDto();
    }

    @Override
    public Object fillWithBots(Long gameId) {
        Game game = gameRepository.getOne(gameId);

        List<User> botUserSearch = userRepository.findByRank(UserRank.CPU);
        User botUser;
        if(!botUserSearch.isEmpty()){
            botUser = botUserSearch.get(0);
        } else {
            User newBotUser = new User(PlayerType.CPU);
            userRepository.save(newBotUser);
            botUser = newBotUser;
        }

        int i = game.getPlayers().size();
        while(i < GAME_FULL_NUMBER) {
            Player botPlayer = new Player(botUser, PlayerType.CPU);
            game.addPlayer(botPlayer);
            botPlayer.setGame(game);
            playerRepository.save(botPlayer);
            i++;
        }

        gameRepository.save(game);
        return game.toDto();
    }


    @Override
    public Object modifyPlayersProfessions(Long gameId) {

        Game game = gameRepository.getOne(gameId);
        List<Long> playerIds = game.getPlayers()
                .stream()
                .map(Player::getPlayerId)
                .collect(toList());

        List<Player> players = playerRepository.findAll()
                .stream().filter(x -> playerIds.contains(x.getPlayerId()))
                .collect(toList());

        List<Profession> professions = professionRepository.findAll()
                .stream()
                .filter(x -> game.getGameDifficulty().equals(x.getDifficulty()))
                .collect(toList());

        players.forEach(x -> {
            Profession chosenProfession = professions.get(generateRandomNumber(0, professions.size()));
            if(x.getProfession() == null || !x.getProfession().equals(chosenProfession)){
                x.setProfession(chosenProfession);
                playerRepository.save(x);
            }
        });

        return game.toDto();
    }


}
