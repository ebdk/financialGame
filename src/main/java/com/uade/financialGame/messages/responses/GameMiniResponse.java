package com.uade.financialGame.messages.responses;

import com.uade.financialGame.messages.Response;
import com.uade.financialGame.models.Game;
import com.uade.financialGame.models.Player;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class GameMiniResponse implements Response {

    private Long gameId;
    private String gameType;
    private String gameDifficulty;
    private List<Long> playersIds;

    public GameMiniResponse(Game game) {
        if(game != null){
            this.gameId = game.getGameId() != null ? game.getGameId() : null;
            this.gameType = game.getGameType() != null ? game.getGameType().toString() : null;
            this.gameDifficulty = game.getGameDifficulty() != null ? game.getGameDifficulty().toString() : null;
            this.playersIds = game.getPlayers() != null
                    ? game.getPlayers().stream().map(Player::getPlayerId).collect(Collectors.toList()) : null;
        }
    }

    public GameMiniResponse() {
    }
}
