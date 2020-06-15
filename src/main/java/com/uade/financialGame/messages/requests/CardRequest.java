package com.uade.financialGame.messages.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uade.financialGame.models.Card;
import com.uade.financialGame.models.Transaction;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CardRequest {

    private String name;
    private String imgUrl;
    private String description;
    private String difficulty;
    private String type;
    private List<TransactionRequest> transactionRequests;

    public CardRequest(Card card) {
        this.name = card.getName();
        this.imgUrl = card.getImgUrl();
        this.description = card.getDescription();
        this.difficulty = card.getDifficulty().toString();
        this.type = card.getType().toString();
    }

    public CardRequest() {
    }

    @JsonIgnore
    public List<Transaction> getTransactions() {
        return transactionRequests
                .stream()
                .map(TransactionRequest::toEntity)
                .collect(Collectors.toList());
    }

}
