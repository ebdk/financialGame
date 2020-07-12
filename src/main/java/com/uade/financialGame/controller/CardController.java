package com.uade.financialGame.controller;

import com.uade.financialGame.messages.requests.CardRequest;
import com.uade.financialGame.messages.responses.UserResponse;
import com.uade.financialGame.services.CardService;
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
@RequestMapping("financial_game/api/card")
public class CardController {

    @Autowired
    private CardService service;

    @ApiOperation(
            value = "Gets a random card given difficulty and type",
            notes = "Self explanatory")
    @ApiResponses({
            @ApiResponse(code = 200, message = "The game was crated successfully", response = UserResponse.class),
    })
    @GetMapping(path="{type}/{difficulty}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Object getRandomCard(
            @ApiParam(value = "The card's type", required = true)
            @PathVariable("type") String type,
            @ApiParam(value = "The card's difficulty, based on the game. GameDifficulties: EASY, MEDIUM, HARD", required = true)
            @PathVariable("difficulty") String difficulty) {
        return service.getRandomCard(type, difficulty);
    }

    @ApiOperation(
            value = "Creates a card",
            notes = "Self explanatory")
    @ApiResponses({
            @ApiResponse(code = 200, message = "The card was crated successfully", response = UserResponse.class),
    })
    @PostMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Object createCard(@RequestBody List<CardRequest> cardRequest) {
        return service.createCard(cardRequest);
    }

}
