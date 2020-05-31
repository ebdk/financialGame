package com.uade.financialGame.models;

import com.uade.financialGame.messages.CardDto;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
public class Card {

    //ATTRIBUTES
    @Id
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
