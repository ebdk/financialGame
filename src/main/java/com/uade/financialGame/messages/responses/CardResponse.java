package com.uade.financialGame.messages.responses;

import com.uade.financialGame.messages.Response;
import com.uade.financialGame.models.Card;
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
    private int cardIncome;
    private int cardExpenses;
    private int cardPercentage;

    private List<Long> gameTurnsIds;

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
        this.gameTurnsIds = card.getGameTurns().stream().map(GameTurn::getGameTurnId).collect(Collectors.toList());
    }

    public CardResponse() {
    }
}
