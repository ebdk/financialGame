package com.uade.financialGame.messages.responses;

import com.uade.financialGame.models.GameTurn;

@lombok.Getter
public class GameTurnResponse {

    private Long gameTurnId;
    private int months;
    private int balanceIncome;
    private int balanceExpenses;
    private int balanceActive;
    private int balancePassive;

    private GameUserResponse gameUser;
    private CardResponse card;

    public GameTurnResponse(GameTurn gameTurn) {
        this.gameTurnId = gameTurn.getGameTurnId();
        this.months = gameTurn.getMonths();
        this.balanceIncome = gameTurn.getBalanceIncome();
        this.balanceExpenses = gameTurn.getBalanceExpenses();
        this.balanceActive = gameTurn.getBalanceActive();
        this.balancePassive = gameTurn.getBalancePassive();
        this.gameUser = new GameUserResponse(gameTurn.getGameUser());
        this.card = new CardResponse(gameTurn.getCard());
    }

    public GameTurnResponse() {
    }
}
