package com.uade.financialGame.messages.responses;

import com.uade.financialGame.messages.Response;
import com.uade.financialGame.models.GameTurn;
import com.uade.financialGame.models.GameUser;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class GameUserResponse implements Response {

    private UserResponse user;
    private ProfessionResponse profession;
    private GameResponse game;
    private List<GameTurnResponse> gameTurns;

    public GameUserResponse(GameUser gameUser) {
        this.user = new UserResponse(gameUser.getUser());
        this.profession = new ProfessionResponse(gameUser.getProfession());
        this.game = new GameResponse(gameUser.getGame());
        this.gameTurns =  gameUser.getGameTurns().stream().map(GameTurn::toDto).collect(Collectors.toList());
    }

    public GameUserResponse() {
    }
}
