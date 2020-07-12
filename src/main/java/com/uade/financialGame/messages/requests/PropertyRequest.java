package com.uade.financialGame.messages.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uade.financialGame.models.Card;
import com.uade.financialGame.models.Property;
import lombok.Getter;

@Getter
public class PropertyRequest {

    private String propertyName;
    private Integer buyValue;
    private Integer sellValue;
    private Integer rentValue;
    private Boolean isRentable;
    private Boolean canBeSold;

    @JsonIgnore
    public Property toEntity() {
        return new Property(this);
    }

	public Property toEntity(Card card) {
        return new Property(this, card);
	}
}
