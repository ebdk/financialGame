package com.uade.financialGame.models;

import com.uade.financialGame.messages.responses.ProfessionResponse;
import com.uade.financialGame.models.Game.GameDifficulty;
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
    private String name;
    private String imgUrl;
    private String description;
    private GameDifficulty difficulty;

    @OneToMany(mappedBy = "profession")
    private List<Player> player;

    //BUILDERS


    public Profession() {
    }

    //METHODS
    public ProfessionResponse toDto() {
        return new ProfessionResponse(this);
    }

}
