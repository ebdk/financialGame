package com.uade.financialGame.models;

import com.uade.financialGame.messages.requests.CardRequest;
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
    private List<Turn> turns;

    @OneToOne(mappedBy = "card")
    private TransactionList transactionList;

    public enum CardType {
        EVENT,
        DREAM,
        OPPORTUNITY
    }


    //BUILDERS
    public Card(CardRequest cardRequest) {
        this.name = cardRequest.getName();
        this.imgUrl = cardRequest.getImgUrl();
        this.description = cardRequest.getDescription();
        this.difficulty = GameDifficulty.valueOf(cardRequest.getDifficulty());
        this.type = CardType.valueOf(cardRequest.getType());
        this.transactionList = cardRequest.getTransactionList();
        transactionList.setCard(this);
    }

    public Card(CardRequest cardRequest, TransactionList transactionList) {
        this.name = cardRequest.getName();
        this.imgUrl = cardRequest.getImgUrl();
        this.description = cardRequest.getDescription();
        this.difficulty = GameDifficulty.valueOf(cardRequest.getDifficulty());
        this.type = CardType.valueOf(cardRequest.getType());
        this.transactionList = transactionList;
    }

    public Card() {
    }

    //METHODS
    public CardResponse toDto() {
        return new CardResponse(this);
    }

}
