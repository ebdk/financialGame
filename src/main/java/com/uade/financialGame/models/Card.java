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
    private String cardName;
    private String cardImgUrl;
    private String cardDescription;
    private GameDifficulty cardDifficulty;
    private CardType cardType;
    private int cardIncome;
    private int cardExpenses;
    private int cardActive;
    private int cardPassive;
    private int cardPercentage;

    @OneToMany(mappedBy = "card")
    private List<GameTurn> gameTurns;

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
