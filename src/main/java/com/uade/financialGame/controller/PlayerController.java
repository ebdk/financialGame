package com.uade.financialGame.controller;

import com.uade.financialGame.messages.responses.UserResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("financial_game/api/player")
public class PlayerController {

    @Autowired
    private com.uade.financialGame.services.PlayerService service;

    @ApiOperation(
            value = "Decreases Player's Passive but also adds Expenses by given amount",
            notes = "Self explanatory")
    @ApiResponses({
            @ApiResponse(code = 200, message = "The game was crated successfully", response = UserResponse.class),
    })
    @PostMapping(path="pay_debt/{playerId}/{amount}",produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Object payDebt(
            @ApiParam(value = "The player's id", required = true)
            @PathVariable("playerId") Long playerId,
            @ApiParam(value = "Amount to pay", required = true)
            @PathVariable("amount") Integer amount) {
        return service.payDebt(playerId, amount);
    }

    @ApiOperation(
            value = "Takes 10% of Player's Income and donates to charity",
            notes = "Self explanatory")
    @ApiResponses({
            @ApiResponse(code = 200, message = "The game was crated successfully", response = UserResponse.class),
    })
    @PostMapping(path="donate_to_charity/{playerId}",produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Object donateToCharity(
            @ApiParam(value = "The player's id", required = true)
            @PathVariable("playerId") Long playerId) {
        return service.donateToCharity(playerId);
    }

    @ApiOperation(
            value = "Makes a new Month for Player",
            notes = "Self explanatory")
    @ApiResponses({
            @ApiResponse(code = 200, message = "The game was crated successfully", response = UserResponse.class),
    })
    @PostMapping(path="next_month/{playerId}",produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Object nextMonth(
            @ApiParam(value = "The player's id", required = true)
            @PathVariable("playerId") Long playerId) {
        return service.nextMonth(playerId);
    }

    @ApiOperation(
            value = "Gets Player's Transaction Balance",
            notes = "Self explanatory")
    @ApiResponses({
            @ApiResponse(code = 200, message = "The Player's ownerships were retrieved succesfuly", response = UserResponse.class),
    })
    @GetMapping(path="balance/{playerId}",produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Object getBalance(
            @ApiParam(value = "The player's id", required = true)
            @PathVariable("playerId") Long playerId) {
        return service.getBalance(playerId);
    }

    @ApiOperation(
            value = "Gets all of Player's ownerships",
            notes = "Self explanatory")
    @ApiResponses({
            @ApiResponse(code = 200, message = "The Player's ownerships were retrieved succesfuly", response = UserResponse.class),
    })
    @GetMapping(path="ownerships/{playerId}",produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Object showPlayerOwnerships(
            @ApiParam(value = "The player's id", required = true)
            @PathVariable("playerId") Long playerId) {
        return service.showPlayerOwnerships(playerId);
    }

    @ApiOperation(
            value = "Get Player",
            notes = "Self explanatory")
    @ApiResponses({
            @ApiResponse(code = 200, message = "The Player's ownerships were retrieved succesfuly", response = UserResponse.class),
    })
    @GetMapping(path="{playerId}",produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Object getPlayer(
            @ApiParam(value = "The player's id", required = true)
            @PathVariable("playerId") Long playerId) {
        return service.getPlayer(playerId);
    }

    @ApiOperation(
            value = "Sells a Player's share for a given quantity",
            notes = "Self explanatory")
    @ApiResponses({
            @ApiResponse(code = 200, message = "The Player's ownerships were retrieved succesfuly", response = UserResponse.class),
    })
    @PostMapping(path="sell_shares/{playerId}/{shareId}/{quantity}",produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Object sellShare(
            @ApiParam(value = "The player's id", required = true)
            @PathVariable("playerId") Long playerId,
            @ApiParam(value = "The player's Share's Id", required = true)
            @PathVariable("shareId") Long shareId,
            @ApiParam(value = "The quantity to be sold", required = true)
            @PathVariable("quantity") Integer quantity) {
        return service.sellShare(playerId, shareId, quantity);
    }

    @ApiOperation(
            value = "Puts a Player's property to rent",
            notes = "Self explanatory")
    @ApiResponses({
            @ApiResponse(code = 200, message = "The Player's ownerships were retrieved succesfuly", response = UserResponse.class),
    })
    @PostMapping(path="rent_property/{playerId}/{propertyId}",produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Object rentProperty(
            @ApiParam(value = "The player's id", required = true)
            @PathVariable("playerId") Long playerId,
            @ApiParam(value = "The player's Property's Id", required = true)
            @PathVariable("propertyId") Long shareId) {
        return service.rentProperty(playerId, shareId);
    }

    @ApiOperation(
            value = "Sells a Player's property",
            notes = "Self explanatory")
    @ApiResponses({
            @ApiResponse(code = 200, message = "The Player's ownerships were retrieved succesfuly", response = UserResponse.class),
    })
    @PostMapping(path="sell_property/{playerId}/{propertyId}",produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Object sellProperty(
            @ApiParam(value = "The player's id", required = true)
            @PathVariable("playerId") Long playerId,
            @ApiParam(value = "The player's Property's Id", required = true)
            @PathVariable("propertyId") Long shareId) {
        return service.sellProperty(playerId, shareId);
    }

}