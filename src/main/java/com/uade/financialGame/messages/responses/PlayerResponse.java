package com.uade.financialGame.messages.responses;

import com.uade.financialGame.messages.Response;
import com.uade.financialGame.models.*;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PlayerResponse implements Response {

    private Long playerId;
    private String type;
    private Long userId;
    private ProfessionResponse profession;
    private GameMiniResponse game;
    private List<Long> turnsIds;
    private TransactionListResponse balance;
    private List<ShareResponse> shares;
    private List<BondResponse> bonds;
    private List<PropertyResponse> properties;

    public PlayerResponse(Player player) {
        if(player != null){
            this.playerId = player.getPlayerId() != null ? player.getPlayerId() : null;
            this.type = player.getPlayerType().toString();
            this.userId = player.getUser().getUserId() != null ? player.getUser().getUserId() : null;
            this.profession = player.getProfession() != null ? player.getProfession().toDto() : null;
            this.game = player.getGame() != null ? player.getGame().toMiniDto() : null;
            this.turnsIds =  player.getTurns() != null ?
                    player.getTurns().stream().map(Turn::getTurnId).collect(Collectors.toList()) : null;
            this.balance = player.getBalance() != null ?
                    player.getBalance().toDto() : null;
            this.shares = player.getShares() != null
                    ? player.getShares().stream().map(Share::toDto).collect(Collectors.toList()) : null;
            this.bonds = player.getBonds() != null
                    ? player.getBonds().stream().map(Bond::toDto).collect(Collectors.toList()) : null;
            this.properties = player.getProperties() != null
                    ? player.getProperties().stream().map(Property::toDto).collect(Collectors.toList()) : null;
        }
    }

    public PlayerResponse() {
    }
}
