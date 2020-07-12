package com.uade.financialGame.messages.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uade.financialGame.models.Card;
import com.uade.financialGame.models.Company;
import com.uade.financialGame.models.CompanyChanges;
import com.uade.financialGame.models.Transaction;
import lombok.Getter;

@Getter
public class CompanyChangesRequest {

    private String attribute;
    private Integer value;
    private Long companyId;

    @JsonIgnore
    public CompanyChanges toEntity() {
        return new CompanyChanges(this);
    }

    public CompanyChanges toEntity(Card card) {
        return new CompanyChanges(this, card);
    }
}
