package com.uade.financialGame.models;

import com.uade.financialGame.messages.responses.ProfessionResponse;
import com.uade.financialGame.messages.responses.PropertyResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Property {

    private Player player;
    private PropertyName propertyName;
    private Integer buyValue;
    private Integer sellValue;
    private Integer rentValue;
    private Boolean beingRented;
    private Boolean isRentable;
    private Boolean canBeSold;

    public enum PropertyName {
        HOUSE,
        BLOCK,
        YACHT,
        TRUCK,
        CAR
    }


    //METHODS
    public PropertyResponse toDto() {
        return new PropertyResponse(this);
    }

}
