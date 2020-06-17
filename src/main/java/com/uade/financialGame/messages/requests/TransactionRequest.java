package com.uade.financialGame.messages.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uade.financialGame.models.Transaction;
import lombok.Getter;

@Getter
public class TransactionRequest {

    private String transactionType;
    private String numericType;
    private String description;
    private String transactionTime;
    private int value;

    @JsonIgnore
    public Transaction toEntity() {
        return new Transaction(this);
    }
}
