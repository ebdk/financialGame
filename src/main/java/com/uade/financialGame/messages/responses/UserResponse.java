package com.uade.financialGame.messages.responses;

import com.uade.financialGame.messages.Response;
import com.uade.financialGame.models.GameUser;
import com.uade.financialGame.models.User;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class UserResponse implements Response {

    //ATTRIBUTES
    private Long userId;
    private String userName;
    private String password;
    private String rank;
    private List<GameUserResponse> games;

    //BUILDERS
    public UserResponse(User user) {
        this.userId = user.getUserId();
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.rank = user.getRank().toString();
        this.games = user.getGames().stream().map(GameUser::toDto).collect(Collectors.toList());
    }

    public UserResponse() {
    }

}
