package com.uade.financialGame.messages.responses;

import com.uade.financialGame.messages.Response;
import com.uade.financialGame.models.GameTurn;
import com.uade.financialGame.models.Player;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PlayerMiniResponse implements Response {

    private Long playerId;
    private String type;
    private Long userId;
    private ProfessionResponse profession;
    private List<Long> gameTurnsIds;

    public PlayerMiniResponse(Player player) {
        if(player != null){
            this.playerId = player.getPlayerId() != null ? player.getPlayerId() : null;
            this.type = player.getPlayerType().toString();
            this.userId = player.getUser().getUserId() != null ? player.getUser().getUserId() : null;
            this.profession = player.getProfession() != null ? new ProfessionResponse(player.getProfession()) : null;
            this.gameTurnsIds =  player.getGameTurns() != null ?
                    player.getGameTurns().stream().map(GameTurn::getGameTurnId).collect(Collectors.toList()) : null;
        }
    }

    public PlayerMiniResponse() {
    }
}
