package com.uade.financialGame.models;

import com.uade.financialGame.messages.responses.CardResponse;
import com.uade.financialGame.models.Game.GameDifficulty;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Card")
@Table(name = "card")
@Getter
public class Card {

    //ATTRIBUTES
    @Id
    @Column(name="CARD_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cardId;
    private String name;
    private String imgUrl;
    private String description;
    private GameDifficulty difficulty;
    private CardType type;

    @OneToMany(mappedBy = "card")
    private List<GameTurn> gameTurns;

    @ManyToMany
    @JoinTable(
            name = "card_transaction",
            joinColumns = @JoinColumn(name = "CARD_ID"),
            inverseJoinColumns = @JoinColumn(name = "FINANCIAL_TRANSACTION_ID"))
    private List<FinancialTransaction> transactions;

    public enum CardType {
        EXPENSES,
        DREAM,
        OPPORTUNITY
    }


    //BUILDERS
    public Card() {
    }

    //METHODS
    public CardResponse toDto() {
        return new CardResponse(this);
    }

}
