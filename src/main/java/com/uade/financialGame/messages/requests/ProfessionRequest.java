package com.uade.financialGame.messages.requests;

@lombok.Getter
public class ProfessionRequest {

    private String name;
    private String imgUrl;
    private String description;


    public ProfessionRequest(com.uade.financialGame.models.Profession profession) {
        this.name = profession.getName();
        this.imgUrl = profession.getImgUrl();
        this.description = profession.getDescription();
    }

    public ProfessionRequest() {
    }
}
