package com.uade.financialGame.controller;

import com.uade.financialGame.messages.customRequests.CreateGameTurnRequest;
import com.uade.financialGame.messages.responses.UserResponse;
import com.uade.financialGame.services.GameTurnService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping(path="{gameUserId}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Object getUserTurns(
            @ApiParam(value = "The Game User's Id", required = true)
            @PathVariable("gameUserId") String gameUserId) {
        return service.getGameUserTurns(gameUserId);
    }

    @ApiOperation(
            value = "Get all the turns of a game",
            notes = "Self explanatory")
    @ApiResponses({
            @ApiResponse(code = 200, message = "The game was crated successfully", response = UserResponse.class),
    })
    @GetMapping(path="{gameId}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Object getAllGameTurns(
            @ApiParam(value = "The Game's Id", required = true)
            @PathVariable("gameId") String gameId) {
        return service.getGameTurns(gameId);
    }

    @ApiOperation(
            value = "Creates a Game Turn",
            notes = "Self explanatory")
    @ApiResponses({
            @ApiResponse(code = 200, message = "The Game Turn was crated successfully", response = UserResponse.class),
    })
    @PostMapping(path="{userId}/{cardId}/{boxId}/{turnNumber}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Object createGameTurn(@ApiParam(value = "The user's id", required = true)
                                    @PathVariable("userId") Long userId,
                                @ApiParam(value = "The card's id", required = true)
                                    @PathVariable("cardId") Long cardId,
                                @ApiParam(value = "The box's id", required = true)
                                    @PathVariable("boxId") Long boxId,
                                 @ApiParam(value = "The Turn Number", required = true)
                                     @PathVariable("turnNumber") Integer turnNumber) {
        return service.createGameTurn(userId, cardId, boxId, turnNumber);
    }

}
