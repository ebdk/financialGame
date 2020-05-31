package com.uade.financialGame.messages.customRequests;

@lombok.Getter
public class CreateGameRequest {

    public Long idUser;
    private String gameDifficulty;
    private String gameType;
}
