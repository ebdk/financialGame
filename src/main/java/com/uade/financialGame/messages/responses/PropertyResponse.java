package com.uade.financialGame.messages.responses;

import com.uade.financialGame.messages.Response;
import com.uade.financialGame.models.Property;
import lombok.Getter;

@Getter
public class PropertyResponse implements Response {

    private Long playerId;
    private String propertyName;
    private Integer buyValue;
    private Integer sellValue;
    private Integer rentValue;
    private Boolean beingRented;
    private Boolean isRentable;
    private Boolean canBeSold;

    public PropertyResponse(Property property) {
        if(property != null){
            this.playerId = property.getPlayer() != null ? property.getPlayer().getPlayerId() : null;
            this.propertyName = property.getPropertyName() != null ? property.getPropertyName().toString() : null;
            this.buyValue = property.getBuyValue() != null ? property.getBuyValue() : null;
            this.sellValue = property.getSellValue() != null ?  property.getSellValue() : null;
            this.rentValue = property.getRentValue() != null ?  property.getRentValue() : null;
            this.beingRented = property.getBeingRented() != null ?  property.getBeingRented() : null;
            this.isRentable = property.getIsRentable() != null ?  property.getIsRentable() : null;
            this.canBeSold = property.getCanBeSold() != null ?  property.getCanBeSold() : null;
        }
    }

    public PropertyResponse() {
    }
}
