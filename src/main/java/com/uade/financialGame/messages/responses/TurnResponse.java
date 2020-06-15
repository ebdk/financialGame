package com.uade.financialGame.messages.responses;

import com.uade.financialGame.messages.Response;
import com.uade.financialGame.models.Turn;
import lombok.Getter;

@Getter
public class TurnResponse implements Response {

    private Long turnId;
    private int turnNumber;
    private int balanceIncome;
    private int balanceExpenses;
    private int balanceActive;
    private int balancePassive;

    private PlayerResponse player;
    private CardResponse card;

    public TurnResponse(Turn turn) {
        if(turn != null){
            this.turnId = turn.getTurnId() != null ? turn.getTurnId() : null;
            this.turnNumber = turn.getTurnNumber();
            this.balanceIncome = turn.getBalanceIncome();
            this.balanceExpenses = turn.getBalanceExpenses();
            this.balanceActive = turn.getBalanceActive();
            this.balancePassive = turn.getBalancePassive();
            this.player = turn.getPlayer() != null ? new PlayerResponse(turn.getPlayer()) : null;
            this.card = turn.getCard() != null ? new CardResponse(turn.getCard()) : null;
        }
    }

    public TurnResponse() {
    }
}
