package com.uade.financialGame.models;

import com.uade.financialGame.messages.requests.TransactionRequest;
import com.uade.financialGame.messages.responses.TransactionResponse;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Transaction")
@Table(name = "transaction")
@Getter
public class Transaction {

    //ATTRIBUTES
    @Id
    @Column(name="TRANSACTION_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transactionId;

    private TransactionType transactionType;
    private NumericType numericType;
    private String description;
    private int value;

    @ManyToMany(mappedBy = "transactions", cascade = {CascadeType.ALL})
    private List<Card> cards;

    public Transaction(TransactionRequest transactionRequest) {
        this.transactionType = TransactionType.valueOf(transactionRequest.getTransactionType());
        this.numericType = NumericType.valueOf(transactionRequest.getNumericType());
        this.description = transactionRequest.getDescription();
        this.value = transactionRequest.getValue();
    }

    public Transaction() {
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
    public TransactionResponse toDto() {
        return new TransactionResponse(this);
    }

}
