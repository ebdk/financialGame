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
    private OptionType optionType;
    private TargetType targetType;
    private EffectType effectType;

    @OneToMany(mappedBy = "card")
    private List<Turn> turns;

    @OneToOne(mappedBy = "card")
    private TransactionList transactionList;

    @OneToMany(mappedBy = "card")
    private List<Share> shares;

    @OneToMany(mappedBy = "card")
    private List<Bond> bonds;

    @OneToMany(mappedBy = "card")
    private List<Property> properties;

    @OneToMany(mappedBy = "card")
    private List<CompanyChanges> companyChanges;

    @ManyToOne
    private GlosarySlot glosarySlot;

    public enum OptionType {
        EVENT,
        OPPORTUNITY
    }

    public enum TargetType {
        PERSONAL,
        GLOBAL
    }

    public enum EffectType {
        TRANSACTION_ONLY,
        PROPERTY_BUY,
        SHARE_BUY,
        BOND_BUY,
        COMPANY_VALUE_CHANGE
    }


    //BUILDERS
    public Card(CardRequest cardRequest) {
        this.name = cardRequest.getName();
        this.imgUrl = cardRequest.getImgUrl();
        this.description = cardRequest.getDescription();
        this.difficulty = GameDifficulty.valueOf(cardRequest.getDifficulty());
        this.optionType = OptionType.valueOf(cardRequest.getOptionType());
        this.effectType = EffectType.valueOf(cardRequest.getEffectType());
        this.targetType = TargetType.valueOf(cardRequest.getTargetType());
        this.transactionList = cardRequest.getTransactionList(this);
        this.shares = cardRequest.getShares(this);
        this.bonds = cardRequest.getBonds(this);
        this.properties = cardRequest.getProperties(this);
        this.companyChanges = cardRequest.getCompanyChanges(this);
        this.glosarySlot = cardRequest.getGlosarySlot(this);
    }

    /*
    public Card(CardRequest cardRequest, TransactionList transactionList) {
        this.name = cardRequest.getName();
        this.imgUrl = cardRequest.getImgUrl();
        this.description = cardRequest.getDescription();
        this.difficulty = GameDifficulty.valueOf(cardRequest.getDifficulty());
        this.optionType = OptionType.valueOf(cardRequest.getType());
        this.targetType = TargetType.valueOf(cardRequest.getType());
        this.transactionList = transactionList;
    }
     */

    public Card() {
    }

    //METHODS
    public CardResponse toDto() {
        return new CardResponse(this);
    }

}
