package com.uade.financialGame.models;

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

    private Player player;

    public boolean canBeCharged(int monthReference) {
        return !charged && (monthReference >= endsAtMonthNumber);
    }

    public int charge() {
        charged = true;
        return returnValue;
    }

}
