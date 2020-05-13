package com.uade.financialGame.models;

import com.uade.financialGame.messages.UserDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

    //ATTRIBUTES
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long personaId;
    private String userName;
    private String password;
    private int gold;
    private String rank;

    public User(UserDto userDto) {
        this.personaId = userDto.getPersonaId();
        this.userName = userDto.getUserName();
        this.password = userDto.getPassword();
        this.gold = userDto.getGold();
        this.rank = userDto.getRank();
    }

    public User() {
    }

    public User(String username) {
        this.userName = username;
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public Long getPersonaId() {
        return personaId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public int getGold() {
        return gold;
    }

    public String getRank() {
        return rank;
    }

}
