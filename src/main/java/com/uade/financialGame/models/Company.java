package com.uade.financialGame.models;

import com.uade.financialGame.messages.requests.CompanyRequest;
import com.uade.financialGame.messages.responses.CompanyResponse;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

import static com.uade.financialGame.models.Company.SaveType.GAME_COMPANY;
import static com.uade.financialGame.models.Company.SaveType.STATIC;

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
    private SaveType saveType;

    public enum SaveType {
        STATIC,
        GAME_COMPANY
    }

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
        this.saveType = GAME_COMPANY;
    }

    public Company(Company company, Game game) {
        this.name = company.getName();
        this.shareValue = company.getShareValue();
        this.shareDividendValue = company.getShareDividendValue();
        this.game = game;
        this.saveType = GAME_COMPANY;
    }

    public Company(CompanyRequest companyRequest) {
        this.name = companyRequest.getName();
        this.shareValue = companyRequest.getShareValue();
        this.shareDividendValue = companyRequest.getShareDividendValue();
        this.saveType = STATIC;
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

    public CompanyResponse toDto() {
        return new CompanyResponse(this);
    }

    /*
    public void ifIsCompanyApplyChange(String companyName, CompanyAttribute attribute, Integer value) {
        if(companyName.equals(name)){
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

    public void ifIsCompanyApplyChange(CompanyChanges companyChanges) {
        String companyName = companyChanges.getCompany().getName();
        CompanyAttribute attribute = companyChanges.getAttribute();
        Integer value = companyChanges.getValue();
        if(companyName.equals(name)){
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
     */



}
