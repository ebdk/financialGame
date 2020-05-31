package com.uade.financialGame.models;

import com.uade.financialGame.messages.GameDto;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
@Getter
public class Game {

    //ATTRIBUTES
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long gameId;
    private GameType gameType;
    private GameStatus gameStatus;

    private List<GameUser> users;

    private GameLobby lobby;

    public enum GameType {
    }

    public enum GameStatus {
    }


    //BUILDERS

    //METHODS
    public GameDto toDto() {
        return new GameDto(this);
    }

}
