/*package com.uade.financialGame.messages.responses;

import com.uade.financialGame.messages.Response;
import com.uade.financialGame.models.Month;
import lombok.Getter;

@Getter
public class MonthResponse implements Response {

    private Long monthId;
    private Integer monthNumber;
    private Long playerId;
    private PlayerResponse player;
    private TransactionListResponse transactions;

    public MonthResponse(Month month) {
        if(month != null){
            this.monthId = month.getMonthId();
            this.monthNumber = month.getMonthNumber();
            this.playerId = month.getPlayer().getPlayerId();
            this.player = month.getPlayer() != null ? new PlayerResponse(month.getPlayer()) : null;
            this.transactions = month.getTransactionList() != null ?
                    month.getTransactionList().toDto() : null;
        }
    }

    public MonthResponse() {
    }
}
 */