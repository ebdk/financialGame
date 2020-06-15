package com.uade.financialGame.messages.responses;

import com.uade.financialGame.messages.Response;
import com.uade.financialGame.models.Transaction;
import lombok.Getter;

@Getter
public class TransactionResponse implements Response {

    private Long id;
    private String transactionType;
    private String description;
    private String numericType;
    private int value;

    public TransactionResponse(Transaction transaction) {
        if(transaction != null){
            this.id = transaction.getTransactionId();
            this.transactionType = transaction.getTransactionType() != null
                    ? transaction.getTransactionType().toString() : null;
            this.description = transaction.getDescription() != null ? transaction.getDescription() : null;
            this.value = transaction.getValue();
            this.numericType = transaction.getNumericType().toString();
        }
    }

    public TransactionResponse() {
    }
}
