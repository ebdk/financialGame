package com.uade.financialGame.messages.responses;

import com.uade.financialGame.messages.Response;
import com.uade.financialGame.models.Player;
import com.uade.financialGame.models.Profession;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ProfessionResponse implements Response {

    private Long id;
    private String name;
    private String imgUrl;
    private String description;
    private List<Long> playerIds;
    private TransactionListResponse transactions;

    public ProfessionResponse(Profession profession) {
        if(profession != null){
            this.id = profession.getProfessionId() != null ? profession.getProfessionId() : null;
            this.name = profession.getName() != null ? profession.getName() : null;
            this.imgUrl = profession.getImgUrl() != null ? profession.getImgUrl() : null;
            this.description = profession.getDescription() != null ?  profession.getDescription() : null;
            this.playerIds = profession.getPlayer() != null ?
                    profession.getPlayer().stream().map(Player::getPlayerId).collect(Collectors.toList()) : null;
            this.transactions = profession.getTransactionList() != null ?
                    profession.getTransactionList().toDto() : null;
        }
    }

    public ProfessionResponse() {
    }
}
