package com.uade.financialGame.messages.requests;

import com.uade.financialGame.models.Turn;
import lombok.Getter;

@Getter
public class TurnRequest {

    private int turnNumber;
    private int balanceIncome;
    private int balanceExpenses;
    private int balanceActive;
    private int balancePassive;

    public TurnRequest(Turn turn) {
        this.turnNumber = turn.getTurnNumber();
        /*
        this.balanceIncome = turn.getBalanceIncome();
        this.balanceExpenses = turn.getBalanceExpenses();
        this.balanceActive = turn.getBalanceActive();
        this.balancePassive = turn.getBalancePassive();
        */
    }

    public TurnRequest() {
    }
}
