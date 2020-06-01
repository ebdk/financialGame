package com.uade.financialGame.models;

import com.uade.financialGame.messages.responses.GameResponse;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static com.uade.financialGame.models.Game.GameLobbyStatus.*;

@Entity(name = "Game")
@Table(name = "game")
@Getter
public class Game {

    private static int GAME_FULL_NUMBER = 6;

    //ATTRIBUTES
    @Id
    @Column(name="GAME_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long gameId;
    private GameType gameType;
    private GameDifficulty gameDifficulty;

    @OneToMany(mappedBy = "game")
    private List<GameUser> users;

    public enum GameType {
        NORMAL
    }

    public enum GameDifficulty {
        EASY,
        MEDIUM,
        HARD
    }

    public enum GameLobbyStatus {
        EMPTY,
        FULL,
        AWAITING_PLAYERS
    }


    //BUILDERS
    public Game(GameType gameType, GameDifficulty gameDifficulty) {
        this.gameType = gameType;
        this.gameDifficulty = gameDifficulty;
        this.users = new ArrayList<>();
    }

    public Game() {
    }

    //METHODS
    public GameLobbyStatus getStatus(){
        return users.isEmpty() ? EMPTY :
                (users.size() >= GAME_FULL_NUMBER ? FULL : AWAITING_PLAYERS);
    }

    public int getGameSize(){
        return users.size();
    }

    public void addGameUser(GameUser gameUser) {
        users.add(gameUser);
    }

    public GameResponse toDto() {
        return new GameResponse(this);
    }

}
