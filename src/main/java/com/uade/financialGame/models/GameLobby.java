package com.uade.financialGame.models;

import com.uade.financialGame.messages.GameLobbyDto;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
@Getter
public class GameLobby {

    //ATTRIBUTES
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long gameLobbyId;
    private GameLobbyStatus status;

    private List<GameUser> gameUsers;

    private Game gameGenerated;

    public enum GameLobbyStatus {
    }


    //BUILDERS

    //METHODS
    public GameLobbyDto toDto() {
        return new GameLobbyDto(this);
    }

}
