package com.uade.financialGame.messages.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uade.financialGame.models.Company;
import lombok.Getter;

@Getter
public class CompanyRequest {

    private String name;
    private Integer shareValue;
    private Integer shareDividendValue;

    @JsonIgnore
    public Company toEntity() {
        return new Company(this);
    }
    }
