/*package com.uade.financialGame.models;

import com.uade.financialGame.messages.responses.MonthResponse;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Month")
@Table(name = "month")
@Getter
@Setter
public class Month {

    //ATTRIBUTES
    @Id
    @Column(name="MONTH_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long monthId;
    private Integer monthNumber;

    @ManyToOne
    private Player player;


    @OneToOne(mappedBy = "month")
    private TransactionList transactionList;


    public Month(Integer monthNumber, Player player) {
        this.monthNumber = monthNumber;
        this.player = player;
    }

    public void setTransactionList(TransactionList transactionList) {
        this.transactionList = transactionList;
        transactionList.setMonth(this);
    }

    public void addTransaction(Transaction transaction) {
        this.transactionList.addTransaction(transaction);
    }

    public void addTransactions(List<Transaction> transactionList) {
        this.transactionList.addTransactions(transactionList);
    }

    public Month() {
    }

    //METHODS
    public MonthResponse toDto() {
        return new MonthResponse(this);
    }


}
*/