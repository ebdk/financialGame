package com.uade.financialGame.models;

import com.uade.financialGame.messages.UserDto;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
public class User {

    //ATTRIBUTES
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String userName;
    private String password;
    private int gold;
    private String rank;

    //BUILDERS
    public User(UserDto userDto) {
        this.userId = userDto.getUserId();
        this.userName = userDto.getUserName();
        this.password = userDto.getPassword();
        this.gold = userDto.getGold();
        this.rank = userDto.getRank();
    }

    public User() {
    }

    //METHODS
    public UserDto toDto() {
        return new UserDto(this);
    }

}
