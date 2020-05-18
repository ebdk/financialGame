package com.uade.financialGame.controller;

import com.uade.financialGame.messages.MessageResponse;
import com.uade.financialGame.messages.Response;
import com.uade.financialGame.messages.UserDto;
import com.uade.financialGame.services.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("financial_game/api")
public class UserController {

    @Autowired
    private UserService service;

    @ApiOperation(
            value = "Looks up a user by username",
            notes = "Self explanatory")
    @ApiResponses({
            @ApiResponse(code = 200, message = "The user was found successfully", response = UserDto.class),
            @ApiResponse(code = 500, message = "Internal server error", response = MessageResponse.class),
    })
    @GetMapping(path="user/{username}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Response> getUser(
            @ApiParam(value = "The user's username", required = true)
            @PathVariable("username") String username) {
        return ResponseEntity.ok(service.getByUsername(username));
    }


    @ApiOperation(
            value = "Looks up ALL users from the database",
            notes = "Self explanatory")
    @ApiResponses({
            @ApiResponse(code = 200, message = "The users were found successfully", response = UserDto.class),
            @ApiResponse(code = 500, message = "Internal server error", response = MessageResponse.class),
    })
    @GetMapping(path="user", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getAllUsers() {
        return ResponseEntity.ok(service.getAllusers());
    }


    @ApiOperation(
            value = "Validates data from User",
            notes = "Looks up the user and tries to match it's password with the one given")
    @ApiResponses({
            @ApiResponse(code = 200, message = "The user was validated successfully", response = MessageResponse.class),
    })
    @GetMapping(path="userValidate/{username}/{password}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Response> getValidation(
            @ApiParam(value = "The user's username", required = true)
            @PathVariable("username") String username,
            @ApiParam(value = "The user's password", required = true)
            @PathVariable("password") String password) {
        return ResponseEntity.ok(service.validateByUserNameAndPassword(username, password));
    }


    @ApiOperation(
            value = "Creates a user",
            notes = "Self explanatory")
    @ApiResponses({
            @ApiResponse(code = 200, message = "The user was crated successfully", response = MessageResponse.class),
    })
    @PostMapping(path = "user", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public MessageResponse createPersona(@RequestBody UserDto userDto) {
        return service.createUser(userDto);
    }

}
