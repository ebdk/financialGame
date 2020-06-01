package com.uade.financialGame.messages.responses;

import com.uade.financialGame.messages.Response;
import com.uade.financialGame.models.Game;
import com.uade.financialGame.models.GameUser;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class GameResponse implements Response {

    private Long gameId;
    private String gameType;
    private String gameDifficulty;
    private List<GameUserResponse> users;

    public GameResponse(Game game) {
        this.gameId = game.getGameId() != null ? game.getGameId() : null;
        this.gameType = game.getGameType() != null ? game.getGameType().toString() : null;
        this.gameDifficulty = game.getGameDifficulty() != null ? game.getGameDifficulty().toString() : null;
        this.users = game.getUsers() != null
                ? game.getUsers().stream().map(GameUser::toDto).collect(Collectors.toList()) : null;
    }

    public GameResponse() {
    }
}
