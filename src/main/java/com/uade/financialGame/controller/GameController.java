package com.uade.financialGame.controller;

import com.uade.financialGame.messages.requests.CompanyRequest;
import com.uade.financialGame.messages.responses.UserResponse;
import com.uade.financialGame.services.GameService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            @ApiParam(value = "The game's gameType. GameTypes: NORMAL", required = true)
            @PathVariable("gameType") String gameType,
            @ApiParam(value = "The game's gameDifficulty. GameDifficulties: EASY, MEDIUM, HARD", required = true)
            @PathVariable("gameDifficulty") String gameDifficulty,
            @ApiParam(value = "The user's id", required = true)
            @PathVariable("idUser") String idUser) {
        return service.createGame(gameType, gameDifficulty, idUser);
    }

    @ApiOperation(
            value = "Fills a game with bots",
            notes = "Self explanatory")
    @ApiResponses({
            @ApiResponse(code = 200, message = "The game was crated successfully", response = UserResponse.class),
    })
    @PostMapping(path="fill_with_bots/{gameId}",produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Object fillWithPlayers(
            @ApiParam(value = "The game's id", required = true)
            @PathVariable("gameId") Long gameId) {
        return service.fillWithBots(gameId);
    }

    @ApiOperation(
            value = "Start Game",
            notes = "Assigns professions, initiliaze balance and share of every player")
    @ApiResponses({
            @ApiResponse(code = 200, message = "The users were assigned professions successfully", response = UserResponse.class),
    })
    @PostMapping(path="startGame/{gameId}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Object startGame(@ApiParam(value = "The game's id", required = true)
                                               @PathVariable("gameId") Long gameId) {
        return org.springframework.http.ResponseEntity.ok(service.startGame(gameId));
    }

    @ApiOperation(
            value = "Shows the Companies from a Game",
            notes = "Self explanatory")
    @ApiResponses({
            @ApiResponse(code = 200, message = "The companies were retreived succesfuly", response = UserResponse.class),
    })
    @GetMapping(path="show_companies/{gameId}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Object showGameCompanies(@ApiParam(value = "The game's id", required = true)
                                           @PathVariable("gameId") Long gameId) {
        return org.springframework.http.ResponseEntity.ok(service.showGameCompanies(gameId));
    }

    @ApiOperation(
            value = "Posts a Company",
            notes = "Self explanatory")
    @ApiResponses({
            @ApiResponse(code = 200, message = "The companies were retreived succesfuly", response = UserResponse.class),
    })
    @PostMapping(path="post_company", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Object postCompanies(@RequestBody List<CompanyRequest> companyRequests) {
        return org.springframework.http.ResponseEntity.ok(service.postCompanies(companyRequests));
    }

}
