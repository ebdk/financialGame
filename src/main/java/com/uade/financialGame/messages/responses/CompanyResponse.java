package com.uade.financialGame.messages.responses;

import com.uade.financialGame.messages.Response;
import com.uade.financialGame.models.Company;
import lombok.Getter;

@Getter
public class CompanyResponse implements Response {

    private Long id;
    private String name;
    private Integer shareValue;
    private Integer shareDividendValue;
    private Boolean isStatic;
    private Long gameId;

    public CompanyResponse(Company company) {
        if(company != null){
            this.id = company.getCompanyId() != null ? company.getCompanyId() : null;
            this.name = company.getName() != null ? company.getName() : null;
            this.shareValue = company.getShareValue() != null ? company.getShareValue() : null;
            this.shareDividendValue = company.getShareDividendValue() != null ?  company.getShareDividendValue() : null;
            this.gameId = company.getGame() != null ? company.getGame().getGameId() : null;
        }
    }

    public CompanyResponse() {
    }
}
