package com.uade.financialGame.models;

import com.uade.financialGame.messages.BalanceTurnDto;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
public class BalanceTurn {

    //ATTRIBUTES
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long balanceTurnId;
    private int income;
    private int expenses;
    private int active;
    private int passive;


    private GameTurn gameTurn;


    //BUILDERS

    //METHODS
    public BalanceTurnDto toDto() {
        return new BalanceTurnDto(this);
    }

}
