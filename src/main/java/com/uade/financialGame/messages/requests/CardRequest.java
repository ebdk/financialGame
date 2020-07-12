package com.uade.financialGame.messages.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uade.financialGame.models.*;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Getter
public class CardRequest {

    private String name;
    private String imgUrl;
    private String description;
    private String difficulty;
    private String type;
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
        this.type = card.getOptionType().toString();
    }

    public CardRequest() {
    }

    @JsonIgnore
    public TransactionList getTransactionList(Card card) {
        TransactionList transactionList = this.getTransactionListRequest().toEntity();
        transactionList.setCard(card);
        return transactionList;
    }

    public Card toEntity() {
        return new Card(this);
    }

    public List<Share> getShares(Card card) {
        return sharesRequest.stream().map(shareRequest -> shareRequest.toEntity(card)).collect(toList());
    }

    public List<Bond> getBonds(Card card) {
        return bondsRequest.stream().map(bondRequest -> bondRequest.toEntity(card)).collect(toList());
    }

    public List<Property> getProperties(Card card) {
        return propertiesRequest.stream().map(propertyRequest -> propertyRequest.toEntity(card)).collect(toList());
    }

    public List<CompanyChanges> getCompanyChanges(Card card) {
        return companyChangesRequest.stream().map(companyChangesRequest1 -> companyChangesRequest1.toEntity(card)).collect(toList());
    }


    public GlosarySlot getGlosarySlot(Card card) {
        return glosarySlotRequest.toEntity(card);
    }

}
