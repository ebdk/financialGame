package com.uade.financialGame.models;

import com.uade.financialGame.messages.requests.TransactionListRequest;
import com.uade.financialGame.messages.responses.TransactionListResponse;
import com.uade.financialGame.messages.responses.TransactionResponse;
import com.uade.financialGame.models.Transaction.TransactionType;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

import static com.uade.financialGame.models.Transaction.TransactionType.*;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Entity(name = "TransactionList")
@Table(name = "transaction_list")
@Getter
@lombok.Setter
public class TransactionList {

    //ATTRIBUTES
    @Id
    @Column(name="TRANSACTION_LIST_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transactionId;

    @OneToMany(mappedBy = "transactionList")
    private List<Transaction> transactions;

    @OneToOne(cascade = {CascadeType.ALL})
    private Card card;

    @OneToOne(cascade = {CascadeType.ALL})
    private Profession profession;

    public TransactionList() {
    }

    public TransactionList(TransactionListRequest transactionListRequest) {
        this.transactions = transactionListRequest.getTransactions();
        transactions.forEach(x -> x.setTransactionList(this));
    }

    //METHODS
    public TransactionListResponse toDto() {
        return new TransactionListResponse(this);
    }

    /*
    public Map<TransactionType, List<Transaction>> mapAllByType() {
        return transactions
                .stream()
                .collect(groupingBy(Transaction::getTransactionType));
    }
    */

    public Map<String, List<TransactionResponse>> mapAllByTypeDto() {
        return transactions
                .stream()
                .map(Transaction::toDto)
                .collect(groupingBy(TransactionResponse::getTransactionType));
    }

    private List<Transaction> groupByType(TransactionType transactionType) {
        return transactions
                .stream()
                .filter(x -> transactionType.equals(x.getTransactionType()))
                .collect(toList());
    }

    public List<Transaction> groupByActive() {
        return groupByType(ACTIVE);
    }

    public List<Transaction> groupByPassive() {
        return groupByType(PASSIVE);
    }

    public List<Transaction> groupByExpenses() {
        return groupByType(EXPENSES);
    }

    public List<Transaction> groupByIncome() {
        return groupByType(INCOMES);
    }

    /*
    public java.util.List<TransactionResponse> getTransactionsDto() {
        return transactions
                .stream()
                .map(Transaction::toDto)
                .collect(toList());
    }
    */
}
