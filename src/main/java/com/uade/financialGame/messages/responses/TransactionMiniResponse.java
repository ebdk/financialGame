package com.uade.financialGame.messages.responses;

import com.uade.financialGame.messages.Response;
import com.uade.financialGame.models.Transaction;
import lombok.Getter;

@Getter
public class TransactionMiniResponse implements Response {

    private String description;
    private String numericType;
    private int value;

    public TransactionMiniResponse(Transaction transaction) {
        if(transaction != null){
            this.description = transaction.getDescription() != null ? transaction.getDescription() : null;
            this.value = transaction.getValue();
            this.numericType = transaction.getNumericType().toString();
        }
    }

    public TransactionMiniResponse() {
    }
}
