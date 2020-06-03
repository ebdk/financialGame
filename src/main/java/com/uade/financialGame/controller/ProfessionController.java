package com.uade.financialGame.controller;

import com.uade.financialGame.messages.responses.UserResponse;
import com.uade.financialGame.services.ProfessionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("financial_game/api/profession")
public class ProfessionController {

    @Autowired
    private ProfessionService service;

    @ApiOperation(
            value = "Gets all professions given difficulty",
            notes = "Self explanatory")
    @ApiResponses({
            @ApiResponse(code = 200, message = "The game was crated successfully", response = UserResponse.class),
    })
    @GetMapping(path="{difficulty}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Object getRandomCard(
            @ApiParam(value = "The profession's difficulty, based on the game. GameDifficulties: EASY, MEDIUM, HARD", required = true)
            @PathVariable("difficulty") String difficulty) {
        return service.getAllProfessions(difficulty);
    }

}
