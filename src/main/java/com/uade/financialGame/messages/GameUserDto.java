package com.uade.financialGame.messages;

import com.uade.financialGame.models.GameUser;

import java.util.List;

public class GameUserDto {

    private UserDto user;
    private ProfessionDto profession;
    private List<GameTurnDto> gameTurns;

    public GameUserDto(GameUser gameUser) {
    }
}
