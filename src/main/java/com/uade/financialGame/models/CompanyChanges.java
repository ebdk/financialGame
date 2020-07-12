package com.uade.financialGame.models;

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

    private Company company;

}
