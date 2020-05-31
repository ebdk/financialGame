package com.uade.financialGame.models;

import com.uade.financialGame.messages.GameUserDto;
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
    public GameUserDto toDto() {
        return new GameUserDto(this);
    }

    public void soemthing(){
    }

}
