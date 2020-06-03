package com.uade.financialGame.messages.requests;

@lombok.Getter
public class CardRequest {

    private String cardName;
    private String cardImgUrl;
    private String cardDescription;
    private String cardLevel;
    private String cardType;
    private int cardIncome;
    private int cardExpenses;
    private int cardPercentage;

    public CardRequest(com.uade.financialGame.models.Card card) {
        this.cardName = card.getCardName();
        this.cardImgUrl = card.getCardImgUrl();
        this.cardDescription = card.getCardDescription();
        this.cardLevel = card.getCardDifficulty().toString();
        this.cardType = card.getCardType().toString();
        this.cardIncome = card.getCardIncome();
        this.cardExpenses = card.getCardExpenses();
        this.cardPercentage = card.getCardPercentage();
    }

    public CardRequest() {
    }
}
