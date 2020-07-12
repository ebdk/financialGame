package com.uade.financialGame.messages.responses;

import com.uade.financialGame.messages.Response;
import com.uade.financialGame.models.Bond;
import com.uade.financialGame.models.Player;
import com.uade.financialGame.models.Share;
import lombok.Getter;

@Getter
public class BondResponse implements Response {

    private String description;
    private String smallDescription;
    private Long playerId;
    private Integer endsAtMonthNumber;
    private Integer monthNumberLenght;
    private Integer buyValue;
    private Integer returnValue;
    private Boolean charged;

    public BondResponse(Bond bond) {
        if(bond != null) {
            this.description = bond.getDescription() != null ? bond.getDescription() : null;
            this.smallDescription = bond.getSmallDescription() != null ? bond.getSmallDescription() : null;
            this.playerId = bond.getPlayer() != null ? bond.getPlayer().getPlayerId() : null;
            this.endsAtMonthNumber = bond.getEndsAtMonthNumber() != null ? bond.getEndsAtMonthNumber() : null;
            this.monthNumberLenght = bond.getMonthNumberLenght() != null ? bond.getMonthNumberLenght() : null;
            this.buyValue = bond.getBuyValue() != null ? bond.getBuyValue() : null;
            this.returnValue = bond.getReturnValue() != null ? bond.getReturnValue() : null;
            this.charged = bond.getCharged() != null ? bond.getCharged() : null;
        }
    }

    public BondResponse() {
    }
}
