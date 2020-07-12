package com.uade.financialGame.messages.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uade.financialGame.models.Card;
import com.uade.financialGame.models.Company;
import com.uade.financialGame.models.CompanyChanges;
import com.uade.financialGame.models.Transaction;
import lombok.Getter;

import java.util.List;

import static java.util.stream.Collectors.toList;

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

    public CompanyChanges toEntity(Card card, List<Company> companiesInvolved) {
        Company company = companiesInvolved
                .stream()
                .filter(company1 -> this.companyId.equals(company1.getCompanyId()))
                .collect(toList()).get(0);
        return new CompanyChanges(this, card, company);
    }
}
