package com.uade.financialGame.models;

import com.uade.financialGame.messages.responses.PlayerMiniResponse;
import com.uade.financialGame.messages.responses.PlayerResponse;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Player")
@Table(name = "player")
@Getter
@Setter
public class Player {

    //ATTRIBUTES
    @Id
    @Column(name="PLAYER_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long playerId;
    private PlayerType playerType;

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

    @OneToMany(mappedBy = "player")
    private List<Turn> turns;

    public enum PlayerType {
        HUMAN,
        CPU
    }

    //BUILDERS
    public Player(User user, PlayerType playerType) {
        this.user = user;
        this.playerType = playerType;
    }

    public Player() {
    }

    //METHODS
    public PlayerResponse toDto() {
        return new PlayerResponse(this);
    }

    public PlayerMiniResponse toMiniDto() {
        return new PlayerMiniResponse(this);
    }

    public Long getUserId(){
        return user.getUserId();
    }

    /*
    public void calculateBalance(){
        this.balanceIncome = turns
                .stream()
                .mapToInt(Turn::getBalanceIncome)
                .sum();
        this.balanceExpenses = turns
                .stream()
                .mapToInt(Turn::getBalanceExpenses)
                .sum();
        this.balanceActive = turns
                .stream()
                .mapToInt(Turn::getBalanceActive)
                .sum();
        this.balancePassive = turns
                .stream()
                .mapToInt(Turn::getBalancePassive)
                .sum();
    }
    */


}