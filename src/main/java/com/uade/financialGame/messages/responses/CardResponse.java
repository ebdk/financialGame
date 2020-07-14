package com.uade.financialGame.messages.responses;

import com.uade.financialGame.messages.Response;
import com.uade.financialGame.models.*;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CardResponse implements Response {

    private Long cardId;
    private String cardName;
    private String cardImgUrl;
    private String cardDescription;
    private String cardLevel;
    private String optionType;
    private String targetType;
    private String effectType;

    private TransactionListResponse transactions;
    private List<ShareResponse> shares;
    private List<BondResponse> bonds;
    private List<PropertyResponse> properties;
    private List<CompanyChangesResponse> companyChanges;

    public CardResponse(Card card) {
        if(card != null){
            this.cardId = card.getCardId() != null ? card.getCardId() : null;
            this.cardName = card.getName() != null ? card.getName() : null;
            this.cardImgUrl = card.getImgUrl() != null ? card.getImgUrl() : null;
            this.cardDescription = card.getDescription() != null ? card.getDescription() : null;
            this.cardLevel = card.getDifficulty().toString() != null ? card.getDifficulty().toString() : null;
            this.optionType = card.getOptionType() != null ? card.getOptionType().toString() : null;
            this.targetType = card.getTargetType() != null ? card.getTargetType().toString() : null;
            this.effectType = card.getEffectType() != null ? card.getEffectType().toString() : null;
            this.transactions = card.getTransactionList() != null ?
                    card.getTransactionList().toDto() : null;
            this.shares = card.getShares() != null
                    ? card.getShares().stream().map(Share::toDto).collect(Collectors.toList()) : null;
            this.bonds = card.getBonds() != null
                    ? card.getBonds().stream().map(Bond::toDto).collect(Collectors.toList()) : null;
            this.properties = card.getProperties() != null
                    ? card.getProperties().stream().map(Property::toDto).collect(Collectors.toList()) : null;
            this.companyChanges = card.getCompanyChanges() != null
                    ? card.getCompanyChanges().stream().map(CompanyChanges::toDto).collect(Collectors.toList()) : null;
        }
    }

    public CardResponse() {
    }
}
