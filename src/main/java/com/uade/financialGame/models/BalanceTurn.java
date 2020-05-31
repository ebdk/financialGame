/*package com.uade.financialGame.models;

import com.uade.financialGame.messages.BalanceTurnDto;
import lombok.Getter;

import javax.persistence.*;

@Entity(name = "BalanceTurn")
@Table(name = "balance_turn")
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_turn_id")
    private GameTurn gameTurn;


    //BUILDERS

    //METHODS
    public BalanceTurnDto toDto() {
        return new BalanceTurnDto(this);
    }

}
*/