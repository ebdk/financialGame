package com.uade.financialGame.messages.responses;

import com.uade.financialGame.messages.Response;
import com.uade.financialGame.models.Company;
import com.uade.financialGame.models.Player;
import com.uade.financialGame.models.Property;
import com.uade.financialGame.models.Share;
import lombok.Getter;

@Getter
public class ShareResponse implements Response {

    private Long companyId;
    private Long playerId;
    private Integer quantity;

    public ShareResponse(Share share) {
        if(share != null){
            this.companyId = share.getCompany() != null ? share.getCompany().getCompanyId() : null;
            this.playerId = share.getPlayer() != null ? share.getPlayer().getPlayerId() : null;
            this.quantity = share.getQuantity() != null ? share.getQuantity() : null;
        }
    }

    public ShareResponse() {
    }
}
