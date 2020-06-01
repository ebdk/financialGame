package com.uade.financialGame.controller;

import com.uade.financialGame.messages.customRequests.GetGameTurnsRequest;
import com.uade.financialGame.messages.customRequests.GetGameUserTurnsRequest;
import com.uade.financialGame.messages.responses.GameTurnResponse;
import com.uade.financialGame.messages.responses.UserResponse;
import com.uade.financialGame.services.GameTurnService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("financial_game/api/game_turn")
public class GameTurnController {

    @Autowired
    private GameTurnService service;

    @ApiOperation(
            value = "Get turns by user in a game",
            notes = "Self explanatory")
    @ApiResponses({
            @ApiResponse(code = 200, message = "The game was crated successfully", response = UserResponse.class),
    })
    @PostMapping(path="game", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Object getUserTurns(@RequestBody GetGameUserTurnsRequest getGameUserTurnsRequest) {
        return service.getGameUserTurns(getGameUserTurnsRequest);
    }

    @ApiOperation(
            value = "Get all the turns of a game",
            notes = "Self explanatory")
    @ApiResponses({
            @ApiResponse(code = 200, message = "The game was crated successfully", response = UserResponse.class),
    })
    @PostMapping(path="game_user", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Object getAllGameTurns(@RequestBody GetGameTurnsRequest getGameUserTurnsRequest) {
        return service.getGameTurns(getGameUserTurnsRequest);
    }

}
