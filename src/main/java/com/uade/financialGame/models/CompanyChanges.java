package com.uade.financialGame.models;

import com.uade.financialGame.models.Company.CompanyAttribute;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyChanges {

    private Company company;
    private CompanyAttribute attribute;
    private Integer value;

}
