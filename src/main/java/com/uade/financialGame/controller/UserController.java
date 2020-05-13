package com.uade.financialGame.controller;

import com.uade.financialGame.messages.MessageResponse;
import com.uade.financialGame.services.UserService;
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
@RequestMapping("/financial_game/api")
public class UserController {

    @Autowired
    private UserService service;

    @ApiOperation(
            value = "Test",
            notes = "Returns 'Hello World'")
    @ApiResponses({
            @ApiResponse(code = 200, message = "The prediction was calculated successfully", response = MessageResponse.class),
    })
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MessageResponse> sayHelloWorld() {
        return ResponseEntity.ok(service.sayHello());
    }














    /*
    @ApiOperation(
            value = "Calculates the forecast of the weather condition of a given day",
            notes = "The forecast is limited to just 3650 days, or 10 years ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "The forecast was calculated successfully", response = DayDto.class),
    })
    @GetMapping(path="/{day}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<DayDto> forecastDay(
            @ApiParam(value = "The day that will be predicted", allowableValues = "range[1,3650]", required = true)
            @PathVariable("day") int day) {
        return ResponseEntity.ok(service.predictWeather(day));
    }

    @ApiOperation(
            value = "Calculates the forecast of the weather condition of the next 3600 days",
            notes = "This prediction includes: number of drought days, number of rainy days, "
                    + "day with maximum amount of rain and days with optimum weather conditions")
    @ApiResponses({
            @ApiResponse(code = 200, message = "The prediction was calculated successfully", response = PredictionDto.class),
    })
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PredictionDto> weatherForecast() {
        return ResponseEntity.ok(service.weatherForecast());
    }
    */

}
