package com.uade.financialGame.models;

import com.uade.financialGame.messages.responses.PlayerMiniResponse;
import com.uade.financialGame.messages.responses.PlayerResponse;
import com.uade.financialGame.models.Transaction.TransactionType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.uade.financialGame.models.Transaction.TransactionType.*;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
@Entity(name = "Player")
@Table(name = "player")
@Getter
@Setter
public class Player {

    //ATTRIBUTES
    @Id
    @Column(name="PLAYER_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long playerId;
    private PlayerType playerType;
    private boolean hasDonated;
    private boolean isEmployed;

    @ManyToOne
    private User user;

    @ManyToOne(cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
    private Game game;

    @ManyToOne
    private Profession profession;

    @OneToMany(mappedBy = "player")
    private List<Turn> turns;

    @OneToMany(mappedBy = "player")
    private List<Month> months;

    @OneToOne(mappedBy = "player", cascade=CascadeType.ALL)
    private TransactionList balance;

    private List<Bond> bonds;

    private List<com.uade.financialGame.models.Property> properties;

    private List<Share> shares;

    public enum PlayerType {
        HUMAN,
        CPU
    }

    //BUILDERS
    public Player(User user, PlayerType playerType) {
        this.user = user;
        this.playerType = playerType;
    }

    public Player() {
    }

    //METHODS
    public PlayerResponse toDto() {
        return new PlayerResponse(this);
    }

    public PlayerMiniResponse toMiniDto() {
        return new PlayerMiniResponse(this);
    }

    public Long getUserId(){
        return user.getUserId();
    }

    public Turn getLatestTurn() {
        return turns.stream()
                .sorted(comparing(Turn::getTurnNumber).reversed())
                .collect(toList())
                .get(0);
    }

    private Month getLatestMonth() {
        return months.isEmpty() ? null : months.stream()
                .sorted(comparing(Month::getMonthNumber).reversed())
                .collect(toList())
                .get(0);
    }

    public Integer getLatestMonthNumber() {
        return months.isEmpty() ? 0 : getLatestMonth().getMonthNumber();
    }

    public Map<TransactionType, List<Transaction>> getBalanceDetailedMap() {
        return balance.getTransactions().stream()
                .filter(Transaction::isCurrent)
                .collect(groupingBy(Transaction::getTransactionType));
    }

    public Map<TransactionType, List<Transaction>> getAllBalanceDetailedMap() {
        return balance.getTransactions().stream()
                .collect(groupingBy(Transaction::getTransactionType));
    }

    public Map<TransactionType, Integer> getBalanceValuesMap() {
        Map<TransactionType, List<Transaction>> balanceDetailedMap = getAllBalanceDetailedMap();

        Integer activeValue = 0;
        Integer passiveValue = 0;
        Integer incomeValue = 0;
        Integer expensesValue = 0;
        if(balanceDetailedMap.containsKey(ACTIVE)) {
            activeValue = balanceDetailedMap.get(ACTIVE).stream()
                    .mapToInt(Transaction::getValue).sum();
        }

        if(balanceDetailedMap.containsKey(PASSIVE)) {
            passiveValue = balanceDetailedMap.get(PASSIVE).stream()
                    .mapToInt(Transaction::getValue).sum();
        }

        if(balanceDetailedMap.containsKey(INCOMES)) {
            incomeValue = balanceDetailedMap.get(INCOMES).stream()
                    .mapToInt(Transaction::getValue).sum();
        }

        if(balanceDetailedMap.containsKey(EXPENSES)) {
            expensesValue = balanceDetailedMap.get(EXPENSES).stream()
                    .mapToInt(Transaction::getValue).sum();
        }

        Integer finalActiveValue = activeValue;
        Integer finalPassiveValue = passiveValue;
        Integer finalIncomeValue = incomeValue;
        Integer finalExpensesValue = expensesValue;
        return  new HashMap<TransactionType, Integer>() {{
            put(ACTIVE, finalActiveValue);
            put(PASSIVE, finalPassiveValue);
            put(INCOMES, finalIncomeValue);
            put(EXPENSES, finalExpensesValue);
        }};
    }
}
