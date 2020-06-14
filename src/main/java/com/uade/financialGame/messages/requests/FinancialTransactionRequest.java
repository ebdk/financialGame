package com.uade.financialGame.messages.requests;

import com.uade.financialGame.models.FinancialTransaction;

@lombok.Getter
public class FinancialTransactionRequest {

    private String transactionType;
    private String numericType;
    private String description;
    private int value;

    public FinancialTransaction toEntity() {
        return new FinancialTransaction(this);
    }
}
