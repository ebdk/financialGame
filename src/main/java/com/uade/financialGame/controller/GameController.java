package com.uade.financialGame.controller;

import com.uade.financialGame.messages.responses.UserResponse;
import com.uade.financialGame.services.GameService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
            notes = "Self explanatory")
    @ApiResponses({
            @ApiResponse(code = 200, message = "The game was crated successfully", response = UserResponse.class),
    })
    @PostMapping(path="{gameType}/{gameDifficulty}/{idUser}",produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Object createGame(
            @ApiParam(value = "The card's gameType. GameTypes: NORMAL", required = true)
            @PathVariable("gameType") String gameType,
            @ApiParam(value = "The card's gameDifficulty. GameDifficulties: EASY, MEDIUM, HARD", required = true)
            @PathVariable("gameDifficulty") String gameDifficulty,
            @ApiParam(value = "The card's idUser", required = true)
            @PathVariable("idUser") String idUser) {
        return service.createGame(gameType, gameDifficulty, idUser);
    }

}
