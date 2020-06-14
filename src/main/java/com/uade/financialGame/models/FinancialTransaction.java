package com.uade.financialGame.models;

import com.uade.financialGame.messages.requests.FinancialTransactionRequest;
import com.uade.financialGame.messages.responses.FinancialTransactionResponse;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "FinancialTransaction")
@Table(name = "financial_transaction")
@Getter
public class FinancialTransaction {

    //ATTRIBUTES
    @Id
    @Column(name="FINANCIAL_TRANSACTION_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long financialTransactionId;

    private TransactionType transactionType;
    private NumericType numericType;
    private String description;
    private int value;

    @ManyToMany(mappedBy = "transactions", cascade = {CascadeType.ALL})
    private List<Card> cards;

    public FinancialTransaction(FinancialTransactionRequest financialTransactionRequest) {
        this.transactionType = TransactionType.valueOf(financialTransactionRequest.getTransactionType());
        this.numericType = NumericType.valueOf(financialTransactionRequest.getNumericType());
        this.description = financialTransactionRequest.getDescription();
        this.value = financialTransactionRequest.getValue();
    }

    public FinancialTransaction() {
    }

    public enum TransactionType {
        ACTIVE,
        PASSIVE,
        EXPENSES,
        INCOMES
    }

    public enum NumericType {
        PERCENTAGE,
        NUMBER
    }

    //METHODS
    public FinancialTransactionResponse toDto() {
        return new FinancialTransactionResponse(this);
    }

}
