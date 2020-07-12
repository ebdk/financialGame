package com.uade.financialGame.messages.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uade.financialGame.models.*;
import lombok.Getter;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
public class CardRequest {

    private String name;
    private String imgUrl;
    private String description;
    private String difficulty;
    private String optionType;
    private String targetType;
    private String effectType;
    private TransactionListRequest transactionListRequest;
    private List<ShareRequest> sharesRequest;
    private List<BondRequest> bondsRequest;
    private List<CompanyChangesRequest> companyChangesRequest;
    private List<PropertyRequest> propertiesRequest;
    private GlosarySlotRequest glosarySlotRequest;

    public CardRequest(Card card) {
        this.name = card.getName();
        this.imgUrl = card.getImgUrl();
        this.description = card.getDescription();
        this.difficulty = card.getDifficulty().toString();
        //this.type = card.getOptionType().toString();
    }

    public CardRequest() {
    }

    @JsonIgnore
    public TransactionList getTransactionList(Card card) {
        if(getTransactionListRequest() != null) {
            TransactionList transactionList = this.getTransactionListRequest().toEntity();
            transactionList.setCard(card);
            return transactionList;
        } else {
            return null;
        }
    }


    public List<Share> getShares(Card card) {
        return sharesRequest != null ? sharesRequest.stream().map(shareRequest -> shareRequest.toEntity(card)).collect(toList()) : null;
    }

    public List<Share> getShares(Card card, List<Company> companiesInvolved) {
        return sharesRequest != null ? sharesRequest.stream().map(shareRequest -> shareRequest.toEntity(card, companiesInvolved)).collect(toList()) : null;
    }

    public List<Bond> getBonds(Card card) {
        return bondsRequest != null ? bondsRequest.stream().map(bondRequest -> bondRequest.toEntity(card)).collect(toList()) : null;
    }

    public List<Property> getProperties(Card card) {
        return propertiesRequest != null ? propertiesRequest.stream().map(propertyRequest -> propertyRequest.toEntity(card)).collect(toList()): null;
    }

    public List<CompanyChanges> getCompanyChanges(Card card) {
        return companyChangesRequest != null ? companyChangesRequest.stream().map(companyChangesRequest1 -> companyChangesRequest1.toEntity(card)).collect(toList()): null;
    }

    public List<CompanyChanges> getCompanyChanges(Card card, List<Company> companiesInvolved) {
        return companyChangesRequest != null ? companyChangesRequest.stream().map(companyChangesRequest1 -> companyChangesRequest1.toEntity(card, companiesInvolved)).collect(toList()): null;
    }


    public GlosarySlot getGlosarySlot(Card card) {
        return glosarySlotRequest != null ? glosarySlotRequest.toEntity(card): null;
    }

    public Card toEntity(List<Company> companiesInvolved) {
        return new Card(this, companiesInvolved);
    }

    public Card toEntity() {
        return new Card(this);
    }

}
