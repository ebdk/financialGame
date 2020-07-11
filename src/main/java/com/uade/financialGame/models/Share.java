package com.uade.financialGame.models;

import lombok.Getter;
import lombok.Setter;

import static com.uade.financialGame.utils.MathUtils.getPercentage;

@Getter
@Setter
public class Share {

    private Company company;
    private Player player;
    private Integer quantity;

    public Integer getValue() {
        return quantity * company.getShareValue();
    }

    public Integer getValueDividends() {
        return getPercentage(quantity * company.getShareDividendValue(), 10);
    }

}
