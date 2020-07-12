package com.uade.financialGame.messages.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uade.financialGame.models.Profession;
import com.uade.financialGame.models.TransactionList;
import lombok.Getter;

@Getter
public class ProfessionRequest {

    private String name;
    private String imgUrl;
    private String description;
    private String difficulty;
    private TransactionListRequest transactionListRequest;

    public ProfessionRequest(Profession profession) {
        this.name = profession.getName();
        this.imgUrl = profession.getImgUrl();
        this.description = profession.getDescription();
    }

    public ProfessionRequest() {
    }

    public Profession toEntity() {
        return new Profession(this);
    }

    @JsonIgnore
    public TransactionList getTransactionList() {
        return this.getTransactionListRequest().toEntity();
    }

}
