package com.uade.financialGame.messages.responses;

import com.uade.financialGame.messages.Response;
import com.uade.financialGame.models.CompanyChanges;
import lombok.Getter;

@Getter
public class CompanyChangesResponse implements Response {

    private Long companyChangeId;
    private String attribute;
    private Integer value;
    private Long companyId;
    private String companyName;

    public CompanyChangesResponse(CompanyChanges companyChanges) {
        if(companyChanges != null){
            this.companyChangeId = companyChanges.getCompanyChangeId();
            this.companyId = companyChanges.getCompany() != null ? companyChanges.getCompany().getCompanyId() : null;
            this.companyName = companyChanges.getCompany() != null ? companyChanges.getCompany().getName() : null;
            this.attribute = companyChanges.getAttribute() != null ? companyChanges.getAttribute().toString() : null;
            this.value = companyChanges.getValue() != null ?  companyChanges.getValue() : null;
        }
    }

    public CompanyChangesResponse() {
    }
}
