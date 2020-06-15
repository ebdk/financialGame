package com.uade.financialGame.messages.responses;

import com.uade.financialGame.messages.Response;
import com.uade.financialGame.models.GameTurn;
import com.uade.financialGame.models.Player;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PlayerResponse implements Response {

    private Long playerId;
    private String type;
    private Long userId;
    private ProfessionResponse profession;
    private GameResponse game;
    private List<Long> gameTurnsIds;

    public PlayerResponse(Player player) {
        if(player != null){
            this.playerId = player.getPlayerId() != null ? player.getPlayerId() : null;
            this.type = player.getPlayerType().toString();
            this.userId = player.getUser().getUserId() != null ? player.getUser().getUserId() : null;
            this.profession = player.getProfession() != null ? new ProfessionResponse(player.getProfession()) : null;
            this.game = player.getGame() != null ? new GameResponse(player.getGame()): null;
            this.gameTurnsIds =  player.getGameTurns() != null ?
                    player.getGameTurns().stream().map(GameTurn::getGameTurnId).collect(Collectors.toList()) : null;
        }
    }

    public PlayerResponse() {
    }
}
