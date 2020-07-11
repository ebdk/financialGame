package com.uade.financialGame.models;

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
    private Boolean isRentable;
    private Boolean canBeRented;

    public enum PropertyName {
        HOUSE,
        BLOCK,
        YACHT,
        TRUCK,
        CAR
    }

}
