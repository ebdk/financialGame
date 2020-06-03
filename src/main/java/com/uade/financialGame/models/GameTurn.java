package com.uade.financialGame.models;

import com.uade.financialGame.messages.responses.GameTurnResponse;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "GameTurn")
@Table(name = "game_turn")
@Getter
public class GameTurn {

    //ATTRIBUTES
    @Id
    @Column(name="GAME_TURN_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long gameTurnId;
    private int turnNumber;

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

    public void calculateBalance(){
        Card card = this.card;
        Profession profession = gameUser.getProfession();
        List<GameTurn> previousTurns = gameUser.getGameTurns();
    }

}
