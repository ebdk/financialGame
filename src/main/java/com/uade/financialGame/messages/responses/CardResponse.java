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
            this.cardName = card.getCardName() != null ? card.getCardName() : null;
            this.cardImgUrl = card.getCardImgUrl() != null ? card.getCardImgUrl() : null;
            this.cardDescription = card.getCardDescription() != null ? card.getCardDescription() : null;
            this.cardLevel = card.getCardDifficulty().toString() != null ? card.getCardDifficulty().toString() : null;
            this.cardType = card.getCardType().toString() != null ? card.getCardType().toString() : null;
            this.gameTurnsIds = card.getGameTurns() != null ?
                    card.getGameTurns().stream().map(GameTurn::getGameTurnId).collect(Collectors.toList()) : null;
            this.transactions = card.getTransactions() != null ?
                    card.getTransactions().stream().map(FinancialTransaction::toDto).collect(Collectors.toList()) : null;
        }
    }

    public CardResponse() {
    }
}
