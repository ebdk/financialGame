package com.uade.financialGame.messages.responses;

import com.uade.financialGame.messages.Response;
import com.uade.financialGame.models.Player;
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
    private List<PlayerResponse> playerIds;

    //BUILDERS
    public UserResponse(User user) {
        if(user != null){
            this.userId = user.getUserId() != null ? user.getUserId() : null;
            this.userName = user.getUserName() != null ? user.getUserName() : null;
            this.password = user.getPassword() != null ? user.getPassword() : null;
            this.rank = user.getRank() != null ? user.getRank().toString() : null;
            this.playerIds = user.getGames() != null ?
                    user.getGames().stream().map(Player::toDto).collect(Collectors.toList()) : null;
        }
    }

    public UserResponse() {
    }

}
