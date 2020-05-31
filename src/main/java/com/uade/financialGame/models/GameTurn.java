package com.uade.financialGame.models;

import com.uade.financialGame.messages.GameTurnDto;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
public class GameTurn {

    //ATTRIBUTES
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long gameTurnId;
    private int months;

    private GameUser gameUser;

    private BalanceTurn balanceTurn;

    private Card card;

    //BUILDERS

    //METHODS
    public GameTurnDto toDto() {
        return new GameTurnDto(this);
    }

}
