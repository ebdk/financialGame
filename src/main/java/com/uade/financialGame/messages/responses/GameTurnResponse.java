package com.uade.financialGame.messages.responses;

import com.uade.financialGame.messages.Response;
import com.uade.financialGame.models.GameTurn;
import lombok.Getter;

@Getter
public class GameTurnResponse implements Response {

    private Long gameTurnId;
    private int turnNumber;
    private int balanceIncome;
    private int balanceExpenses;
    private int balanceActive;
    private int balancePassive;

    private GameUserResponse gameUser;
    private CardResponse card;

    public GameTurnResponse(GameTurn gameTurn) {
        if(gameTurn != null){
            this.gameTurnId = gameTurn.getGameTurnId() != null ? gameTurn.getGameTurnId() : null;
            this.turnNumber = gameTurn.getTurnNumber();
            this.balanceIncome = gameTurn.getBalanceIncome();
            this.balanceExpenses = gameTurn.getBalanceExpenses();
            this.balanceActive = gameTurn.getBalanceActive();
            this.balancePassive = gameTurn.getBalancePassive();
            this.gameUser = gameTurn.getGameUser() != null ? new GameUserResponse(gameTurn.getGameUser()) : null;
            this.card = gameTurn.getCard() != null ? new CardResponse(gameTurn.getCard()) : null;
        }
    }

    public GameTurnResponse() {
    }
}
