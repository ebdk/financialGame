package com.uade.financialGame.models;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Profession")
@Table(name = "profession")
@Getter
public class Profession {

    //ATTRIBUTES
    @Id
    @Column(name="PROFESSION_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long professionId;
    private String professionName;
    private String professionImgUrl;
    private String professionDescription;

    @OneToMany(mappedBy = "profession")
    private List<GameUser> gameUser;

    //BUILDERS

    //METHODS
    public com.uade.financialGame.messages.responses.ProfessionResponse toDto() {
        return new com.uade.financialGame.messages.responses.ProfessionResponse(this);
    }

}
