package com.uade.financialGame.models;

import com.uade.financialGame.messages.responses.TurnResponse;
import com.uade.financialGame.models.Transaction.TransactionType;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

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

    private int balanceIncome;
    private int balanceExpenses;
    private int balanceActive;
    private int balancePassive;



    @ManyToOne
    private Player player;

    @ManyToOne
    private Card card;

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

    public void calculateBalance(){
        Map<TransactionType, List<Transaction>> cardTransactions = card.getTransactions()
                .stream()
                .collect(groupingBy(Transaction::getTransactionType));

        Profession profession = player.getProfession();

        List<Turn> previousTurns = player.getTurns()
                .stream()
                .sorted(comparing(Turn::getTurnNumber))
                .collect(toList());




    }

}
