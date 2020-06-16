package com.uade.financialGame.messages.requests;

import com.uade.financialGame.models.Transaction;
import lombok.Getter;

@Getter
public class TransactionRequest {

    private String transactionType;
    private String numericType;
    private String description;
    private int value;

    @com.fasterxml.jackson.annotation.JsonIgnore
    public Transaction toEntity() {
        return new Transaction(this);
    }
}
