package com.uade.financialGame.messages.responses;

import com.uade.financialGame.messages.Response;
import com.uade.financialGame.models.Player;
import com.uade.financialGame.models.Property;
import lombok.Getter;

import java.util.stream.Collectors;

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
            this.buyValue = property.getImgUrl() != null ? property.property() : null;
            this.sellValue = property.getDescription() != null ?  property.getDescription() : null;
            this.rentValue = property.getDescription() != null ?  property.getDescription() : null;
            this.beingRented = property.getDescription() != null ?  property.getDescription() : null;
            this.isRentable = property.getDescription() != null ?  property.getDescription() : null;
            this.canBeSold = property.getDescription() != null ?  property.getDescription() : null;
        }
    }

    public PropertyResponse() {
    }
}
