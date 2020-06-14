package com.uade.financialGame.messages.responses;

@lombok.Getter
public class FinancialTransactionResponse implements com.uade.financialGame.messages.Response {

    private String transactionType;
    private String description;
    private String numericType;
    private int value;

    public FinancialTransactionResponse(com.uade.financialGame.models.FinancialTransaction financialTransaction) {
        if(financialTransaction != null){
            this.transactionType = financialTransaction.getTransactionType() != null
                    ? financialTransaction.getTransactionType().toString() : null;
            this.description = financialTransaction.getDescription() != null ? financialTransaction.getDescription() : null;
            this.value = financialTransaction.getValue();
            this.numericType = financialTransaction.getNumericType().toString();
        }
    }

    public FinancialTransactionResponse() {
    }
}
