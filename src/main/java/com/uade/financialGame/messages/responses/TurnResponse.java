package com.uade.financialGame.messages.responses;

import com.uade.financialGame.messages.Response;
import com.uade.financialGame.models.Turn;
import lombok.Getter;

@Getter
public class TurnResponse implements Response {

    private Long turnId;
    private int turnNumber;
    private PlayerResponse player;
    private CardResponse card;
    private TransactionListResponse transactions;

    public TurnResponse(Turn turn) {
        if(turn != null){
            this.turnId = turn.getTurnId() != null ? turn.getTurnId() : null;
            this.turnNumber = turn.getTurnNumber();
            this.player = turn.getPlayer() != null ? new PlayerResponse(turn.getPlayer()) : null;
            this.card = turn.getCard() != null ? new CardResponse(turn.getCard()) : null;
            this.transactions = turn.getTransactionList() != null ?
                    turn.getTransactionList().toDto() : null;
        }
    }

    public TurnResponse() {
    }
}
