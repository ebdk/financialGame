package com.uade.financialGame.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "Game")
@Table(name = "game")
@Getter
@Setter
public class Company {

    private String name;
    private Integer shareValue;
    private Integer shareDividendValue;
    private Integer bondValue;
    private Integer bondDividendValue;
    private Game gameItBelongs;
    private Boolean isStatic;

    public Company() {
    }

    public Company(String name, Integer shareValue, Integer shareDividendValue, Integer bondValue, Integer bondDividendValue, Game gameItBelongs) {
        this.name = name;
        this.shareValue = shareValue;
        this.shareDividendValue = shareDividendValue;
        this.bondValue = bondValue;
        this.bondDividendValue = bondDividendValue;
        this.gameItBelongs = gameItBelongs;
        this.isStatic = false;
    }

    public enum CompanyAttribute {
        SHARE,
        SHARE_DIVIDEND,
        BOND,
        BOND_DIVIDEND
    }

    public void changeCompanyAttribute(CompanyAttribute attribute, Integer value) {
        switch(attribute) {
            case SHARE:
                setShareValue(shareValue + value);
                break;
            case SHARE_DIVIDEND:
                setShareDividendValue(shareDividendValue + value);
                break;
            case BOND:
                setBondValue(bondValue + value);
                break;
            case BOND_DIVIDEND:
                setBondDividendValue(bondDividendValue + value);
                break;
            default:
                setShareValue(shareValue + value);
        }
    }

}
