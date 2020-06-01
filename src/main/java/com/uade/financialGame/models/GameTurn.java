package com.uade.financialGame.models;

import com.uade.financialGame.messages.responses.GameTurnResponse;
import lombok.Getter;

import javax.persistence.*;

@Entity(name = "GameTurn")
@Table(name = "game_turn")
@Getter
public class GameTurn {

    //ATTRIBUTES
    @Id
    @Column(name="GAME_TURN_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long gameTurnId;
    private int months;

    private int balanceIncome;
    private int balanceExpenses;
    private int balanceActive;
    private int balancePassive;

    @ManyToOne
    private GameUser gameUser;

    @ManyToOne
    private Card card;

    //BUILDERS


    public GameTurn() {
    }

    //METHODS
    public GameTurnResponse toDto() {
        return new GameTurnResponse(this);
    }

}
