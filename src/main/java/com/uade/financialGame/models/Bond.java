package com.uade.financialGame.models;

import lombok.Getter;
import lombok.Setter;

import static com.uade.financialGame.utils.MathUtils.getPercentage;

@Getter
@Setter
public class Bond {

    private Company company;
    private Player player;
    private Integer quantity;
    private Integer boughtAtMonthNumber;
    private Integer endsAtMonthNumber;

    private Integer getValue() {
        return quantity * company.getBondValue();
    }

    public Integer getValueDividends() {
        return getPercentage(quantity * company.getBondDividendValue(), 10);
    }

}
