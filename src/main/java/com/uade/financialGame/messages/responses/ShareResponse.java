package com.uade.financialGame.messages.responses;

import com.uade.financialGame.messages.Response;
import com.uade.financialGame.models.Share;
import lombok.Getter;

@Getter
public class ShareResponse implements Response {

    private Long shareId;
    private Long companyId;
    private String companyName;
    private Integer companyShareSellValue;
    private Integer companyShareDividendValue;
    private Long playerId;
    private Integer quantity;

    public ShareResponse(Share share) {
        if(share != null){
            this.shareId = share.getShareId();
            this.companyId = share.getCompany() != null ? share.getCompany().getCompanyId() : null;
            this.companyName = share.getCompany() != null ? share.getCompany().getName() : null;
            this.companyShareSellValue = share.getCompany() != null ? share.getCompany().getShareValue() : null;
            this.companyShareDividendValue = share.getCompany() != null ? share.getCompany().getShareDividendValue() : null;
            this.playerId = share.getPlayer() != null ? share.getPlayer().getPlayerId() : null;
            this.quantity = share.getQuantity() != null ? share.getQuantity() : null;
        }
    }

    public ShareResponse() {
    }
}
