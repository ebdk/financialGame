package com.uade.financialGame.messages.responses;

import com.uade.financialGame.models.Card;
import com.uade.financialGame.models.GameTurn;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CardResponse {

    private Long cardId;
    private String cardName;
    private String cardImgUrl;
    private String cardDescription;
    private String cardLevel;
    private String cardType;
    private int cardIncome;
    private int cardExpenses;
    private int cardPercentage;
    private List<GameTurnResponse> gameTurns;

    public CardResponse(Card card) {
        this.cardId = card.getCardId();
        this.cardName = card.getCardName();
        this.cardImgUrl = card.getCardImgUrl();
        this.cardDescription = card.getCardDescription();
        this.cardLevel = card.getCardLevel().toString();
        this.cardType = card.getCardType().toString();
        this.cardIncome = card.getCardIncome();
        this.cardExpenses = card.getCardExpenses();
        this.cardPercentage = card.getCardPercentage();
        this.gameTurns = card.getGameTurns().stream().map(GameTurn::toDto).collect(Collectors.toList());
    }

    public CardResponse() {
    }
}
