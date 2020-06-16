package com.uade.financialGame.messages.requests;

import com.uade.financialGame.models.Card;
import com.uade.financialGame.models.TransactionList;
import lombok.Getter;

@Getter
public class CardRequest {

    private String name;
    private String imgUrl;
    private String description;
    private String difficulty;
    private String type;
    private TransactionListRequest transactionListRequest;

    public CardRequest(Card card) {
        this.name = card.getName();
        this.imgUrl = card.getImgUrl();
        this.description = card.getDescription();
        this.difficulty = card.getDifficulty().toString();
        this.type = card.getType().toString();
    }

    public CardRequest() {
    }

    @com.fasterxml.jackson.annotation.JsonIgnore
    public TransactionList getTransactionList() {
        return this.getTransactionListRequest().toEntity();
    }
}
