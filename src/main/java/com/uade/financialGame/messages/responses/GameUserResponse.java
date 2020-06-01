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
        this.id = gameUser.getGameUserId();
        this.userId = gameUser.getUser().getUserId();
        this.profession = new ProfessionResponse(gameUser.getProfession());
        this.game = new GameResponse(gameUser.getGame());
        this.gameTurnsIds =  gameUser.getGameTurns().stream().map(GameTurn::getGameTurnId).collect(Collectors.toList());
    }

    public GameUserResponse() {
    }
}
