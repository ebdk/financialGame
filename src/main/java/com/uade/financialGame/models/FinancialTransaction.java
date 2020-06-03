package com.uade.financialGame.models;

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
    private String description;
    private int value;

    @ManyToMany(mappedBy = "transactions")
    private List<Card> cards;

    public enum TransactionType {
        ACTIVE,
        PASSIVE,
        EXPENSES,
        INCOMES
    }

    //METHODS
    public FinancialTransactionResponse toDto() {
        return new FinancialTransactionResponse(this);
    }

}
