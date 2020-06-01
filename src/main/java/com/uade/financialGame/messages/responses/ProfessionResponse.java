package com.uade.financialGame.messages.responses;

import com.uade.financialGame.messages.Response;
import com.uade.financialGame.models.Profession;
import lombok.Getter;

@Getter
public class ProfessionResponse implements Response {

    private Long id;
    private String name;
    private String imgUrl;
    private String description;


    public ProfessionResponse(Profession profession) {
        this.id = profession.getProfessionId();
        this.name = profession.getProfessionName();
        this.imgUrl = profession.getProfessionImgUrl();
        this.description = profession.getProfessionDescription();
    }

    public ProfessionResponse() {
    }
}
