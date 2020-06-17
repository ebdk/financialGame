package com.uade.financialGame.models;

import com.uade.financialGame.messages.requests.TransactionRequest;
import com.uade.financialGame.messages.responses.TransactionMiniResponse;
import com.uade.financialGame.messages.responses.TransactionResponse;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static com.uade.financialGame.models.Transaction.NumericType.NUMBER;
import static com.uade.financialGame.models.Transaction.NumericType.PERCENTAGE;
import static com.uade.financialGame.models.Transaction.TransactionTime.CURRENT;
import static com.uade.financialGame.models.Transaction.TransactionTime.MONTHLY;
import static com.uade.financialGame.models.Transaction.TransactionType.*;

@Entity(name = "Transaction")
@Table(name = "transaction")
@Getter
@Setter
public class Transaction {

    //ATTRIBUTES
    @Id
    @Column(name="TRANSACTION_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transactionId;

    private String description;
    private TransactionType transactionType;
    private NumericType numericType;
    private TransactionTime transactionTime;
    private int value;

    @ManyToOne(cascade = {CascadeType.ALL})
    private TransactionList transactionList;

    public Transaction(TransactionRequest transactionRequest) {
        this.description = transactionRequest.getDescription();
        this.transactionType = TransactionType.valueOf(transactionRequest.getTransactionType());
        this.numericType = NumericType.valueOf(transactionRequest.getNumericType());
        this.value = transactionRequest.getValue();
        this.transactionTime = TransactionTime.valueOf(transactionRequest.getTransactionTime());
    }

    public Transaction(Transaction transaction, Integer monthNumber) {
        this.description = transaction.getDescription().concat(" Month " + monthNumber);
        this.transactionType = transaction.getTransactionType();
        this.transactionTime = CURRENT;
        this.numericType = transaction.getNumericType();
        this.value = transaction.getValue();
    }

    public Transaction(String description, TransactionType transactionType, NumericType numericType, TransactionTime transactionTime, int value) {
        this.description = description;
        this.transactionType = transactionType;
        this.numericType = numericType;
        this.transactionTime = transactionTime;
        this.value = value;
    }

    public Transaction(String description, TransactionType transactionType, NumericType numericType, TransactionTime transactionTime, int value, TransactionList transactionList) {
        this.description = description;
        this.transactionType = transactionType;
        this.numericType = numericType;
        this.transactionTime = transactionTime;
        this.value = value;
        this.transactionList = transactionList;
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

    public enum TransactionTime {
        CURRENT,
        MONTHLY
    }


    //METHODS
    public TransactionResponse toDto() {
        return new TransactionResponse(this);
    }

    public TransactionMiniResponse toMiniDto() {
        return new TransactionMiniResponse(this);
    }

    public boolean isMonthlyExpenses() {
        return MONTHLY.equals(transactionTime) && EXPENSES.equals(transactionType);
    }

    public boolean isMonthlyIncome() {
        return MONTHLY.equals(transactionTime) && INCOMES.equals(transactionType) && NUMBER.equals(numericType);
    }

    public boolean isCurrentTransaction() {
        return CURRENT.equals(transactionTime) && NUMBER.equals(numericType);
    }

    public boolean isPercentage() {
        return PERCENTAGE.equals(numericType);
    }

    public boolean isCurrentPassive() {
        return CURRENT.equals(transactionTime) && PASSIVE.equals(transactionType) && NUMBER.equals(numericType);
    }

    public boolean isCurrentIncome() {
        return CURRENT.equals(transactionTime) && INCOMES.equals(transactionType) && NUMBER.equals(numericType);
    }

    public boolean isCurrentExpenses() {
        return CURRENT.equals(transactionTime) && EXPENSES.equals(transactionType) && NUMBER.equals(numericType);
    }

    public boolean isCurrentActive() {
        return CURRENT.equals(transactionTime) && ACTIVE.equals(transactionType) && NUMBER.equals(numericType);
    }

    public boolean isCurrent() {
        return CURRENT.equals(transactionTime) && NUMBER.toString().equals(numericType.toString());
    }

    public void turnFromMonthlyToTransaction(Integer monthNumber) {
        this.description = description.concat(" Month " + monthNumber);
        this.transactionTime = CURRENT;
        this.transactionId = null;
    }

}
