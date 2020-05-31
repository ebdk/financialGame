package com.uade.financialGame.models;

import com.uade.financialGame.messages.GameDto;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

import static com.uade.financialGame.models.Game.GameLobbyStatus.*;

@Entity
@Getter
public class Game {

    //ATTRIBUTES
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long gameId;
    private GameType gameType;
    private GameDifficulty gameDifficulty;

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
    }

    //METHODS
    public GameLobbyStatus getStatus(){
        return users.isEmpty() ? EMPTY :
                (users.size() >= 6 ? FULL : AWAITING_PLAYERS);
    }

    public int getGameSize(){
        return users.size();
    }

    public void addGameUser(GameUser gameUser) {
        users.add(gameUser);
    }

    public GameDto toDto() {
        return new GameDto(this);
    }

}
