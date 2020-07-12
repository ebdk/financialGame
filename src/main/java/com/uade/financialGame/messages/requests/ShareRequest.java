package com.uade.financialGame.messages.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uade.financialGame.models.Card;
import com.uade.financialGame.models.Share;
import lombok.Getter;

@Getter
public class ShareRequest {

    private Integer quantity;
    private Long companyId;

    @JsonIgnore
    public Share toEntity() {
        return new Share(this);
    }

    public Share toEntity(Card card) {
        return new Share(this, card);
    }
}
