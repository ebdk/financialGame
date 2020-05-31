package com.uade.financialGame.models;

import com.uade.financialGame.messages.GameUserDto;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
@Getter
public class GameUser {

    //ATTRIBUTES
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long gameUserId;

    private User user;

    private Game game;

    private Profession profession;

    private List<GameTurn> gameTurns;

    //BUILDERS

    //METHODS
    public GameUserDto toDto() {
        return new GameUserDto(this);
    }

    public void soemthing(){
    }

}
