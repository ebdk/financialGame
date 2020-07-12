package com.uade.financialGame.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Company")
@Table(name = "company")
@Getter
@Setter
public class Company {

    //ATTRIBUTES
    @Id
    @Column(name="COMPANY_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long companyId;

    private String name;
    private Integer shareValue;
    private Integer shareDividendValue;
    private Boolean isStatic;

    @ManyToOne
    private Game game;

    @OneToMany(mappedBy = "company")
    private List<CompanyChanges> changesInvolved;

    public Company() {
    }

    public Company(String name, Integer shareValue, Integer shareDividendValue, Integer bondValue, Integer bondDividendValue, Game gameItBelongs) {
        this.name = name;
        this.shareValue = shareValue;
        this.shareDividendValue = shareDividendValue;
        this.game = gameItBelongs;
        this.isStatic = false;
    }

    public enum CompanyAttribute {
        SHARE,
        SHARE_DIVIDEND
    }

    public void changeCompanyAttribute(CompanyAttribute attribute, Integer value) {
        switch(attribute) {
            case SHARE_DIVIDEND:
                setShareDividendValue(shareDividendValue + value);
                break;
            case SHARE:
            default:
                setShareValue(shareValue + value);
        }
    }

}
