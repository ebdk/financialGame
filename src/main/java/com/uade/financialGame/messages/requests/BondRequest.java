package com.uade.financialGame.messages.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uade.financialGame.models.Bond;
import com.uade.financialGame.models.Card;
import com.uade.financialGame.models.Transaction;
import lombok.Getter;

@Getter
public class BondRequest {

    private String description;
    private String smallDescription;
    private Integer monthNumberLenght;
    private Integer buyValue;
    private Integer returnValue;
    private Boolean charged;

    @JsonIgnore
    public Bond toEntity() {
        return new Bond(this);
    }

	public Bond toEntity(Card card) {
        return new Bond(this, card);
	}
}
