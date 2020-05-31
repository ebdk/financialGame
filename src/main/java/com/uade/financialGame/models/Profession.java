package com.uade.financialGame.models;

import com.uade.financialGame.messages.ProfessionDto;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
public class Profession {

    //ATTRIBUTES
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long professionId;
    private String professionName;
    private String professionImgUrl;
    private String professionDescription;

    //BUILDERS

    //METHODS
    public ProfessionDto toDto() {
        return new ProfessionDto(this);
    }

}
