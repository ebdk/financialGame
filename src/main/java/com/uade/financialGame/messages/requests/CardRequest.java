package com.uade.financialGame.messages.requests;

@lombok.Getter
public class CardRequest {

    private String name;
    private String imgUrl;
    private String description;
    private String difficulty;
    private String type;

    public CardRequest(com.uade.financialGame.models.Card card) {
        this.name = card.getName();
        this.imgUrl = card.getImgUrl();
        this.description = card.getDescription();
        this.difficulty = card.getDifficulty().toString();
        this.type = card.getType().toString();
    }

    public CardRequest() {
    }
}
