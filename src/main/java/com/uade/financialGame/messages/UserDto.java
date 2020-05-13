package com.uade.financialGame.messages;

import com.uade.financialGame.models.User;
import lombok.Getter;

@Getter
public class UserDto implements Response {

    private Long personaId;
    private String userName;
    private String password;
    private int gold;
    private String rank;

    public UserDto(User user) {
        this.personaId = user.getPersonaId();
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.gold = user.getGold();
        this.rank = user.getRank();
    }

    public UserDto() {
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
