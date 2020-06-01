package com.uade.financialGame.controller;

import com.uade.financialGame.messages.Response;
import com.uade.financialGame.services.DebugService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("financial_game/api")
public class DebugController {

    @Autowired
    private DebugService service;

    @ApiOperation(
            value = "Ping",
            notes = "Tests connection")
    @ApiResponses({
            @ApiResponse(code = 200, message = "The users were found successfully", response = Object.class),
            @ApiResponse(code = 500, message = "Internal server error", response = Response.class),
    })
    @GetMapping(path="ping", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Object ping() {
        return ResponseEntity.ok(service.ping());
    }

    @ApiOperation(
            value = "Gets ALL entities",
            notes = "Self explanatory")
    @ApiResponses({
            @ApiResponse(code = 200, message = "The users were found successfully", response = Object.class),
            @ApiResponse(code = 500, message = "Internal server error", response = Response.class),
    })
    @GetMapping(path="entities", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Object getAllEntities() {
        return ResponseEntity.ok(service.getAllEntities());
    }
}
