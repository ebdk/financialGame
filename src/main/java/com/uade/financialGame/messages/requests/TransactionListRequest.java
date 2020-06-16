package com.uade.financialGame.messages.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uade.financialGame.models.Transaction;
import com.uade.financialGame.models.TransactionList;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class TransactionListRequest {

    private List<TransactionRequest> transactionRequests;

    @JsonIgnore
    public TransactionList toEntity() {
        return new TransactionList(this);
    }

    @JsonIgnore
    public List<Transaction> getTransactions() {
        return transactionRequests
                .stream()
                .map(TransactionRequest::toEntity)
                .collect(Collectors.toList());
    }

}
