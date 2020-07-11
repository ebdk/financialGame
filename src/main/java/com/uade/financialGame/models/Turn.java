package com.uade.financialGame.models;

import com.uade.financialGame.messages.responses.TurnResponse;
import lombok.Getter;

import javax.persistence.*;

@Entity(name = "Turn")
@Table(name = "turn")
@Getter
public class Turn {

    //ATTRIBUTES
    @Id
    @Column(name="TURN_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long turnId;
    private Integer turnNumber;

    @ManyToOne
    private Player player;

    @ManyToOne
    private Card card;

    /*
    @OneToOne(mappedBy = "turn")
    private TransactionList transactionList;
    */

    //BUILDERS
    public Turn() {
    }

    public Turn(Player player, Card card, Integer turnNumber) {
        this.player = player;
        this.card = card;
        this.turnNumber = turnNumber;
    }

    //METHODS
    public TurnResponse toDto() {
        return new TurnResponse(this);
    }

    /*
    public void addTransaction(Transaction transaction) {
        this.transactionList.addTransaction(transaction);
    }

    public void addTransactions(List<Transaction> transactionList) {
        this.transactionList.addTransactions(transactionList);
    }
    */


}
