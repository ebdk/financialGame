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
        this.id = profession.getProfessionId();
        this.name = profession.getProfessionName();
        this.imgUrl = profession.getProfessionImgUrl();
        this.description = profession.getProfessionDescription();
        this.gameUserIds = profession.getGameUser().stream().map(GameUser::getGameUserId).collect(Collectors.toList());
    }

    public ProfessionResponse() {
    }
}
