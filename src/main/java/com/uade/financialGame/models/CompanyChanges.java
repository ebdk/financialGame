package com.uade.financialGame.models;

import com.uade.financialGame.messages.requests.CompanyChangesRequest;
import com.uade.financialGame.models.Company.CompanyAttribute;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "CompanyChange")
@Table(name = "company_change")
@Getter
@Setter
public class CompanyChanges {

    //ATTRIBUTES
    @Id
    @Column(name="COMPANY_CHANGE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long companyChangeId;

    private CompanyAttribute attribute;
    private Integer value;

    @ManyToOne
    private Company company;

    @ManyToOne
    private Card card;

	public CompanyChanges(CompanyChangesRequest companyChangesRequest) {
	}

	public CompanyChanges(CompanyChangesRequest companyChangesRequest, Card card) {
		this.attribute = CompanyAttribute.valueOf(companyChangesRequest.getAttribute());
		this.value = companyChangesRequest.getValue();
		this.card = card;
	}

	public CompanyChanges(CompanyChangesRequest companyChangesRequest, Card card, Company company) {
		this.attribute = CompanyAttribute.valueOf(companyChangesRequest.getAttribute());
		this.value = companyChangesRequest.getValue();
		this.card = card;
		this.company = company;
	}

	public CompanyChanges() {
	}
}
