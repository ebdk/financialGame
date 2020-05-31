package com.uade.financialGame.messages;

import com.uade.financialGame.models.Profession;
import lombok.Getter;

@Getter
public class ProfessionDto {

    private String name;
    private String imgUrl;
    private String description;


    public ProfessionDto(Profession profession) {
    }
}
