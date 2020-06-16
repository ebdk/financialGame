package com.uade.financialGame.messages.responses;

import com.uade.financialGame.messages.Response;
import com.uade.financialGame.models.TransactionList;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class TransactionListResponse implements Response {

    private Map<String, List<TransactionResponse>> transactions;

    public TransactionListResponse(TransactionList transactionList) {
        if(transactionList != null){
            this.transactions = transactionList.mapAllByTypeDto();
        }
    }

    public TransactionListResponse() {
    }

}
