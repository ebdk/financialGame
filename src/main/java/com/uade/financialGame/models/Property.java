package com.uade.financialGame.models;

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
