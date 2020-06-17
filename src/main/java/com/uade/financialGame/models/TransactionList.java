package com.uade.financialGame.models;

import com.uade.financialGame.messages.requests.TransactionListRequest;
import com.uade.financialGame.messages.responses.TransactionListResponse;
import com.uade.financialGame.messages.responses.TransactionResponse;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Entity(name = "TransactionList")
@Table(name = "transaction_list")
@Getter
@Setter
public class TransactionList {

    //ATTRIBUTES
    @Id
    @Column(name="TRANSACTION_LIST_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transactionId;

    @OneToMany(mappedBy = "transactionList", cascade=CascadeType.ALL)
    private List<Transaction> transactions;

    @OneToOne(cascade = {CascadeType.ALL})
    private Card card;

    @OneToOne(cascade = {CascadeType.ALL})
    private Month month;

    @OneToOne(cascade = {CascadeType.ALL})
    private Profession profession;

    @OneToOne(cascade = {CascadeType.ALL})
    private Player player;

    @OneToOne(cascade = {CascadeType.ALL})
    private Turn turn;

    public TransactionList() {
        this.transactions = new ArrayList<>();
    }

    public TransactionList(Player player) {
        this.player = player;
        this.transactions = new ArrayList<>();
    }

    public TransactionList(Turn turn) {
        this.turn = turn;
        this.transactions = new ArrayList<>();
    }

    public TransactionList(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public TransactionList(TransactionListRequest transactionListRequest) {
        this.transactions = transactionListRequest.getTransactions();
        transactions.forEach(x -> x.setTransactionList(this));
    }

    //METHODS
    public TransactionListResponse toDto() {
        return new TransactionListResponse(this);
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        transaction.setTransactionList(this);
    }

    public void addTransactions(List<Transaction> transactionList) {
        transactions.addAll(transactionList);
        transactionList.forEach(x -> x.setTransactionList(this));
    }

    public List<Transaction> getMonthlyExpenses() {
        return transactions.stream()
                .filter(Transaction::isMonthlyExpenses)
                .collect(toList());
    }

    public List<Transaction> getMonthlyIncomes() {
        return transactions.stream()
                .filter(Transaction::isMonthlyIncome)
                .collect(toList());
    }

    /*
    public Map<TransactionType, List<Transaction>> mapAllByType() {
        return transactions
                .stream()
                .collect(groupingBy(Transaction::getTransactionType));
    }

    public java.util.List<TransactionResponse> getTransactionsDto() {
        return transactions
                .stream()
                .map(Transaction::toDto)
                .collect(toList());
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
    */

    public Map<String, List<TransactionResponse>> mapAllByTypeDto() {
        return transactions
                .stream()
                .map(Transaction::toDto)
                .collect(groupingBy(TransactionResponse::getTransactionType));
    }

    public List<Transaction> cloneList() {
        List<Transaction> newTransactions = new ArrayList<>();
        for(Transaction transaction : transactions) {
            Transaction newTransaction = new Transaction(transaction);
            newTransactions.add(newTransaction);
        }
        return newTransactions;
    }





}
