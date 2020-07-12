package com.uade.financialGame.messages.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uade.financialGame.models.Card;
import com.uade.financialGame.models.Company;
import com.uade.financialGame.models.Share;
import lombok.Getter;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
public class ShareRequest {

    private Integer quantity;
    private Long companyId;

    @JsonIgnore
    public Share toEntity() {
        return new Share(this);
    }

    public Share toEntity(Card card) {
        return new Share(this, card);
    }

	public Share toEntity(Card card, List<Company> companiesInvolved) {
        Company company = companiesInvolved
                .stream()
                .filter(company1 -> this.companyId.equals(company1.getCompanyId()))
                .collect(toList()).get(0);
        return new Share(this, card, company);
	}
}
