package com.uade.financialGame.messages.responses;

import com.uade.financialGame.messages.Response;
import com.uade.financialGame.models.GameTurn;
import com.uade.financialGame.models.GameUser;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class GameUserResponse implements Response {

    private Long id;
    private Long userId;
    private ProfessionResponse profession;
    private GameResponse game;
    private List<Long> gameTurnsIds;

    public GameUserResponse(GameUser gameUser) {
        if(gameUser != null){
            this.id = gameUser.getGameUserId() != null ? gameUser.getGameUserId() : null;
            this.userId = gameUser.getUser().getUserId() != null ? gameUser.getUser().getUserId() : null;
            this.profession = gameUser.getProfession() != null ? new ProfessionResponse(gameUser.getProfession()) : null;
            this.game = gameUser.getGame() != null ? new GameResponse(gameUser.getGame()): null;
            this.gameTurnsIds =  gameUser.getGameTurns() != null ?
                    gameUser.getGameTurns().stream().map(GameTurn::getGameTurnId).collect(Collectors.toList()) : null;
        }
    }

    public GameUserResponse() {
    }
}
