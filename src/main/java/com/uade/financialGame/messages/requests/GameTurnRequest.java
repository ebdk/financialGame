package com.uade.financialGame.messages.requests;

@lombok.Getter
public class GameTurnRequest {

    private int turnNumber;
    private int balanceIncome;
    private int balanceExpenses;
    private int balanceActive;
    private int balancePassive;

    public GameTurnRequest(com.uade.financialGame.models.GameTurn gameTurn) {
        this.turnNumber = gameTurn.getTurnNumber();
        this.balanceIncome = gameTurn.getBalanceIncome();
        this.balanceExpenses = gameTurn.getBalanceExpenses();
        this.balanceActive = gameTurn.getBalanceActive();
        this.balancePassive = gameTurn.getBalancePassive();
    }

    public GameTurnRequest() {
    }
}
