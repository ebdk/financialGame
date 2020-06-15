package com.uade.financialGame.messages.requests;

import com.uade.financialGame.messages.Response;
import com.uade.financialGame.models.User;
import lombok.Getter;

@Getter
public class UserRequest implements Response {

    //ATTRIBUTES
    private String userName;
    private String password;
    private String rank;
    private Integer coins;

    //BUILDERS
    public UserRequest(User user) {
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.rank = user.getRank().toString();
        this.coins = user.getCoins();
    }

    public UserRequest() {
    }

}
