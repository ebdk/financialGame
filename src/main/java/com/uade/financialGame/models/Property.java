package com.uade.financialGame.models;

public class Property {

    private Player player;
    private PropertyName propertyName;
    private Integer value;
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
