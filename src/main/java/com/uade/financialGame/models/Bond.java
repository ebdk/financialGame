package com.uade.financialGame.models;

import lombok.Getter;

import static com.uade.financialGame.utils.MathUtils.getPercentage;

@Getter
public class Bond {

    private Company company;
    private Player player;
    private Integer quantity;
    private Integer boughtAtMonthNumber;

    private Integer getValue() {
        return quantity * company.getBondValue();
    }

    public Integer getValueDividends() {
        return getPercentage(quantity * company.getBondDividendValue(), 10);
    }

}
