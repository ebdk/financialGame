package com.uade.financialGame.models;

import com.uade.financialGame.messages.responses.GameUserResponse;
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

    private int balanceIncome;
    private int balanceExpenses;
    private int balanceActive;
    private int balancePassive;

    @ManyToOne
    private User user;

    @ManyToOne(cascade = {CascadeType.ALL})
    private Game game;

    @ManyToOne
    private Profession profession;

    @OneToMany(mappedBy = "gameUser")
    private List<GameTurn> gameTurns;


    //BUILDERS
    public GameUser(User user) {
        this.user = user;
    }

    public GameUser() {
    }

    //METHODS
    public GameUserResponse toDto() {
        return new GameUserResponse(this);
    }

    public Long getUserId(){
        return user.getUserId();
    }

    public void calculateBalance(){
        this.balanceIncome = gameTurns
                .stream()
                .mapToInt(GameTurn::getBalanceIncome)
                .sum();
        this.balanceExpenses = gameTurns
                .stream()
                .mapToInt(GameTurn::getBalanceExpenses)
                .sum();
        this.balanceActive = gameTurns
                .stream()
                .mapToInt(GameTurn::getBalanceActive)
                .sum();
        this.balancePassive = gameTurns
                .stream()
                .mapToInt(GameTurn::getBalancePassive)
                .sum();
    }

}
