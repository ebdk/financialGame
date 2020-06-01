package com.uade.financialGame.messages.requests;

@lombok.Getter
public class GameRequest {

    private String gameType;
    private String gameDifficulty;

    public GameRequest(com.uade.financialGame.models.Game game) {
        this.gameType = game.getGameType() != null ? game.getGameType().toString() : null;
        this.gameDifficulty = game.getGameDifficulty() != null ? game.getGameDifficulty().toString() : null;
    }

    public GameRequest() {
    }
}
