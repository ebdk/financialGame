package com.uade.financialGame.models;

import com.uade.financialGame.messages.CardDto;
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
    private CardLevel cardLevel;
    private CardType cardType;
    private int cardIncome;
    private int cardExpenses;
    private int cardPercentage;

    @OneToMany(mappedBy = "card")
    private List<GameTurn> gameTurns;

    public enum CardLevel {
    }

    public enum CardType {
    }


    //BUILDERS

    //METHODS
    public CardDto toDto() {
        return new CardDto(this);
    }

}
