package com.uade.financialGame.messages.responses;

import com.uade.financialGame.messages.Response;
import com.uade.financialGame.models.Card;
import com.uade.financialGame.models.FinancialTransaction;
import com.uade.financialGame.models.GameTurn;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CardResponse implements Response {

    private Long cardId;
    private String cardName;
    private String cardImgUrl;
    private String cardDescription;
    private String cardLevel;
    private String cardType;

    private List<Long> gameTurnsIds;
    private List<FinancialTransactionResponse> transactions;

    public CardResponse(Card card) {
        if(card != null){
            this.cardId = card.getCardId() != null ? card.getCardId() : null;
            this.cardName = card.getName() != null ? card.getName() : null;
            this.cardImgUrl = card.getImgUrl() != null ? card.getImgUrl() : null;
            this.cardDescription = card.getDescription() != null ? card.getDescription() : null;
            this.cardLevel = card.getDifficulty().toString() != null ? card.getDifficulty().toString() : null;
            this.cardType = card.getType().toString() != null ? card.getType().toString() : null;
            this.gameTurnsIds = card.getGameTurns() != null ?
                    card.getGameTurns().stream().map(GameTurn::getGameTurnId).collect(Collectors.toList()) : null;
            this.transactions = card.getTransactions() != null ?
                    card.getTransactions().stream().map(FinancialTransaction::toDto).collect(Collectors.toList()) : null;
        }
    }

    public CardResponse() {
    }
}
