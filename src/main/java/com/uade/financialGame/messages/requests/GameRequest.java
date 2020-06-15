package com.uade.financialGame.messages.requests;

import com.uade.financialGame.models.Game;
import lombok.Getter;

@Getter
public class GameRequest {

    private String gameType;
    private String gameDifficulty;

    public GameRequest(Game game) {
        this.gameType = game.getGameType() != null ? game.getGameType().toString() : null;
        this.gameDifficulty = game.getGameDifficulty() != null ? game.getGameDifficulty().toString() : null;
    }

    public GameRequest() {
    }
}
