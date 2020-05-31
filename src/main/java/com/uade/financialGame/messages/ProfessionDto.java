package com.uade.financialGame.messages;

@lombok.Getter
public class ProfessionDto {

    private String name;
    private String imgUrl;
    private String description;


    public ProfessionDto(com.uade.financialGame.models.Profession profession) {
    }
}
