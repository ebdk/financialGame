package com.uade.financialGame.models;

import com.uade.financialGame.messages.responses.ProfessionResponse;
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
    private Game.GameDifficulty professionDifficulty;
    private int professionIncome;
    private int professionExpenses;

    @OneToMany(mappedBy = "profession")
    private List<GameUser> gameUser;

    //BUILDERS


    public Profession() {
    }

    //METHODS
    public ProfessionResponse toDto() {
        return new ProfessionResponse(this);
    }

}
