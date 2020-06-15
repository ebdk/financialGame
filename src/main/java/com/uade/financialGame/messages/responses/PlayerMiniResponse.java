package com.uade.financialGame.messages.responses;

import com.uade.financialGame.messages.Response;
import com.uade.financialGame.models.Player;
import com.uade.financialGame.models.Turn;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PlayerMiniResponse implements Response {

    private Long playerId;
    private String type;
    private Long userId;
    private ProfessionResponse profession;
    private List<Long> turnsIds;

    public PlayerMiniResponse(Player player) {
        if(player != null){
            this.playerId = player.getPlayerId() != null ? player.getPlayerId() : null;
            this.type = player.getPlayerType().toString();
            this.userId = player.getUser().getUserId() != null ? player.getUser().getUserId() : null;
            this.profession = player.getProfession() != null ? new ProfessionResponse(player.getProfession()) : null;
            this.turnsIds =  player.getTurns() != null ?
                    player.getTurns().stream().map(Turn::getTurnId).collect(Collectors.toList()) : null;
        }
    }

    public PlayerMiniResponse() {
    }
}
