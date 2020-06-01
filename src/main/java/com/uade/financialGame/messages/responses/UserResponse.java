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
    private List<GameUserResponse> gameUserIds;

    //BUILDERS
    public UserResponse(User user) {
        if(user != null){
            this.userId = user.getUserId() != null ? user.getUserId() : null;
            this.userName = user.getUserName() != null ? user.getUserName() : null;
            this.password = user.getPassword() != null ? user.getPassword() : null;
            this.rank = user.getRank() != null ? user.getRank().toString() : null;
            this.gameUserIds = user.getGames() != null ?
                    user.getGames().stream().map(GameUser::toDto).collect(Collectors.toList()) : null;
        }
    }

    public UserResponse() {
    }

}
