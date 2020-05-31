package com.uade.financialGame.messages;

public class GameUserDto {

    private UserDto user;
    private ProfessionDto profession;
    private java.util.List<GameTurnDto> gameTurns;

    public GameUserDto(com.uade.financialGame.models.GameUser gameUser) {
    }
}
