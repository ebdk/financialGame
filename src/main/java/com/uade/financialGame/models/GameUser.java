package com.uade.financialGame.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "GameUser")
@Table(name = "game_user")
@Getter
@Setter
public class GameUser {

    //ATTRIBUTES
    @Id
    @Column(name="GAME_USER_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long gameUserId;

    @ManyToOne
    private User user;

    @ManyToOne
    private Game game;

    @ManyToOne
    private Profession profession;

    @OneToMany(mappedBy = "gameUser")
    private List<GameTurn> gameTurns;


    //BUILDERS
    public GameUser(User user) {
        this.user = user;
    }


    //METHODS
    public com.uade.financialGame.messages.responses.GameUserResponse toDto() {
        return new com.uade.financialGame.messages.responses.GameUserResponse(this);
    }

}
