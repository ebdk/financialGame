package com.uade.financialGame.messages.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uade.financialGame.models.Bond;
import com.uade.financialGame.models.Card;
import com.uade.financialGame.models.GlosarySlot;
import lombok.Getter;

@Getter
public class GlosarySlotRequest {

    private String name;
    private String description;

    @JsonIgnore
    public GlosarySlot toEntity() {
        return new GlosarySlot(this);
    }

	public GlosarySlot toEntity(Card card) {
    	return new GlosarySlot(this, card);
	}
}
