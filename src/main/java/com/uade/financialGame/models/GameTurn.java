package com.uade.financialGame.models;

import com.uade.financialGame.messages.responses.GameTurnResponse;
import com.uade.financialGame.models.FinancialTransaction.TransactionType;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Entity(name = "GameTurn")
@Table(name = "game_turn")
@Getter
public class GameTurn {

    //ATTRIBUTES
    @Id
    @Column(name="GAME_TURN_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long gameTurnId;
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
    public GameTurn() {
    }

    public GameTurn(Player player, Card card, Integer turnNumber) {
        this.player = player;
        this.card = card;
        this.turnNumber = turnNumber;
    }

    //METHODS
    public GameTurnResponse toDto() {
        return new GameTurnResponse(this);
    }

    public void calculateBalance(){
        Map<TransactionType, List<FinancialTransaction>> cardTransactions = card.getTransactions()
                .stream()
                .collect(groupingBy(FinancialTransaction::getTransactionType));

        Profession profession = player.getProfession();

        List<GameTurn> previousTurns = player.getGameTurns()
                .stream()
                .sorted(comparing(GameTurn::getTurnNumber))
                .collect(toList());




    }

}
