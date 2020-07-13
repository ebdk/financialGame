package com.uade.financialGame.models;

import com.uade.financialGame.messages.requests.BondRequest;
import com.uade.financialGame.messages.responses.BondResponse;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "Bond")
@Table(name = "bond")
@Getter
@Setter
public class Bond {

    //ATTRIBUTES
    @Id
    @Column(name="BOND_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bondId;

    private String description;
    private String smallDescription;
    private Integer endsAtMonthNumber;
    private Integer monthNumberLenght;
    private Integer buyValue;
    private Integer returnValue;
    private Boolean charged;

    @ManyToOne
    private Player player;

    @ManyToOne
    private Card card;

    public Bond(BondRequest bondRequest) {
        this.description = bondRequest.getDescription();
        this.smallDescription = bondRequest.getSmallDescription();
        this.monthNumberLenght = bondRequest.getMonthNumberLenght();
        this.buyValue = bondRequest.getBuyValue();
        this.returnValue = bondRequest.getReturnValue();
        this.charged = false;
    }

    public Bond(BondRequest bondRequest, Card card) {
        this.description = bondRequest.getDescription();
        this.smallDescription = bondRequest.getSmallDescription();
        this.monthNumberLenght = bondRequest.getMonthNumberLenght();
        this.buyValue = bondRequest.getBuyValue();
        this.returnValue = bondRequest.getReturnValue();
        this.charged = false;
        this.card = card;
    }

    public Bond() {
    }

	public Bond(Bond cardBond) {
        this.description = cardBond.getDescription();
        this.smallDescription = cardBond.getSmallDescription();
        this.monthNumberLenght = cardBond.getMonthNumberLenght();
        this.buyValue = cardBond.getBuyValue();
        this.returnValue = cardBond.getReturnValue();
        this.charged = false;
	}

	public boolean canBeCharged(int monthReference) {
        return !charged && (monthReference >= endsAtMonthNumber);
    }

    public int charge() {
        charged = true;
        return returnValue;
    }

    public int charge(int monthReference) {
        return canBeCharged(monthReference) ? charge() : 0;
    }


    public boolean notCharged() {
        return !charged;
    }

	public BondResponse toDto() {
        return new BondResponse(this);
	}
}
