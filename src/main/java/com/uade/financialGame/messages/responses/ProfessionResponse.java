package com.uade.financialGame.messages.responses;

import com.uade.financialGame.messages.Response;
import com.uade.financialGame.models.GameUser;
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
    private List<Long> gameUserIds;

    public ProfessionResponse(Profession profession) {
        if(profession != null){
            this.id = profession.getProfessionId() != null ? profession.getProfessionId() : null;
            this.name = profession.getProfessionName() != null ? profession.getProfessionName() : null;
            this.imgUrl = profession.getProfessionImgUrl() != null ? profession.getProfessionImgUrl() : null;
            this.description = profession.getProfessionDescription() != null ?  profession.getProfessionDescription() : null;
            this.gameUserIds = profession.getGameUser() != null ?
                    profession.getGameUser().stream().map(GameUser::getGameUserId).collect(Collectors.toList()) : null;
        }
    }

    public ProfessionResponse() {
    }
}
