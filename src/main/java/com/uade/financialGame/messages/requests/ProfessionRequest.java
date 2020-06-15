package com.uade.financialGame.messages.requests;

import com.uade.financialGame.models.Profession;
import lombok.Getter;

@Getter
public class ProfessionRequest {

    private String name;
    private String imgUrl;
    private String description;


    public ProfessionRequest(Profession profession) {
        this.name = profession.getName();
        this.imgUrl = profession.getImgUrl();
        this.description = profession.getDescription();
    }

    public ProfessionRequest() {
    }
}
