package com.uade.financialGame.messages;

import com.uade.financialGame.models.User;
import lombok.Getter;

@Getter
public class UserDto implements Response {

    //ATTRIBUTES
    private Long userId;
    private String userName;
    private String password;
    private int gold;
    private String rank;

    //BUILDERS
    public UserDto(User user) {
        this.userId = user.getUserId();
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.gold = user.getGold();
        this.rank = user.getRank();
    }

    public UserDto() {
    }

}
