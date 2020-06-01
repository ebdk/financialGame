package com.uade.financialGame.controller;

import com.uade.financialGame.messages.customRequests.CreateGameRequest;
import com.uade.financialGame.services.GameService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("financial_game/api/game")
public class GameController {

    @Autowired
    private GameService service;

    @ApiOperation(
            value = "Creates a game",
            notes = "GameTypes: NORMAL, GameDifficulties: EASY, MEDIUM, HARD")
    @ApiResponses({
            @ApiResponse(code = 200, message = "The game was crated successfully", response = com.uade.financialGame.messages.responses.UserResponse.class),
    })
    @PostMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Object createPersona(@RequestBody CreateGameRequest createGameRequest) {
        return service.createGame(createGameRequest);
    }

}
