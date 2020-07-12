package com.uade.financialGame.services.impl;

import com.uade.financialGame.models.*;
import com.uade.financialGame.repositories.*;
import com.uade.financialGame.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.uade.financialGame.models.Transaction.NumericType.NUMBER;
import static com.uade.financialGame.models.Transaction.TransactionTime.CURRENT;
import static com.uade.financialGame.models.Transaction.TransactionType.*;
import static com.uade.financialGame.utils.MathUtils.getPercentage;
import static java.util.Arrays.asList;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerDAO playerRepository;

    @Autowired
    private TransactionListDAO transactionListRepository;

    @Autowired
    private TransactionDAO transactionRepository;

    @Autowired
    private TurnDAO turnRepository;

    @Autowired
    private PropertyDAO propertyRepository;

    @Autowired
    private ShareDAO shareRepository;

    @Autowired
    private BondDAO bondRepository;

    @Override
    public Object payDebt(Long playerId, Integer amount) {
        Player player = playerRepository.getOne(playerId);

        Transaction passiveTransaction = new Transaction("Pay Debt", PASSIVE, NUMBER, CURRENT, (-1) * amount);
        Transaction expensesTransaction = new Transaction("Pay Debt", EXPENSES, NUMBER, CURRENT, amount);
        List<Transaction> transactions = asList(passiveTransaction, expensesTransaction);

        player.addTransactionsToBalance(transactions);

        playerRepository.save(player);

        return player.getBalance().toDto();
    }

    @Override
    public Object donateToCharity(Long playerId) {
        Player player = playerRepository.getOne(playerId);

        TransactionList balance = player.getBalance();

        Integer playerSalary = player.getBalanceValuesMap().get(INCOMES);
        Integer amount = getPercentage(playerSalary, 10);

        Transaction expensesTransaction = new Transaction("Charity", EXPENSES, NUMBER, CURRENT, amount);

        player.setHasDonated(true);

        player.addTransactionsToBalance(Collections.singletonList(expensesTransaction));

        playerRepository.save(player);

        return balance.toDto();
    }

    @Override
    public Object newMonth(Long playerId) {
        Player player = playerRepository.getOne(playerId);
        Integer monthNumber = player.getLatestMonthNumber() + 1;

        TransactionList balance = player.getBalance();

        //OLD TRANSACTIONS
        List<Transaction> monthlyIncomes = balance.getMonthlyIncomes();
        List<Transaction> monthlyExpenses = balance.getMonthlyExpenses();

        //NEW TRANSACTIONS
        List<Transaction> thisMonthIncomes = new ArrayList<>();
        List<Transaction> thisMonthExpenses = new ArrayList<>();

        if(player.isEmployed()) {
            thisMonthIncomes.addAll(chargeBonds(player));
            thisMonthIncomes.addAll(shareDividends(player));
            for(Transaction transaction : monthlyIncomes) {
                Transaction newTransaction = new Transaction(transaction, monthNumber);
                thisMonthIncomes.add(newTransaction);
            }
        } else {
            player.setEmployed(true);
            playerRepository.save(player);
            Transaction newTransaction = new Transaction("No job for Month " + monthNumber, INCOMES, NUMBER, CURRENT, 0);
            thisMonthIncomes.add(newTransaction);
        }
        for(Transaction transaction : monthlyExpenses) {
            Transaction newTransaction = new Transaction(transaction, monthNumber);
            thisMonthExpenses.add(newTransaction);
        }
        thisMonthIncomes.addAll(thisMonthExpenses);
        balance.addTransactions(thisMonthIncomes);

        //transactionRepository.saveAll(thisMonthIncomes);
        transactionListRepository.save(balance);
        return balance.toDto();
    }

    private List<Transaction> shareDividends(Player player) {
        List<Transaction> thisMonthSharesDividends = new ArrayList<>();
        List<Share> shares = player.getShares();
        shares.forEach(x -> {
            Transaction shareTransaction = new Transaction("Dividends of Shares of " + x.getCompany().getName(),
                    INCOMES, NUMBER, CURRENT, x.getValueDividends());
            thisMonthSharesDividends.add(shareTransaction);
        });
        return thisMonthSharesDividends;
    }

    private List<Transaction> chargeBonds(Player player) {
        List<Transaction> thisMonthBondsDividends = new ArrayList<>();
        List<Bond> bonds = player.getBonds();
        bonds.forEach(x -> {
            if(x.canBeCharged(player.getCurrentMonth())) {
                Transaction shareTransaction = new Transaction("Charged Bond of " + x.getSmallDescription(),
                        INCOMES, NUMBER, CURRENT, x.charge());
                thisMonthBondsDividends.add(shareTransaction);
            };
        });
        return thisMonthBondsDividends;
    }


    @Override
    public Object showPlayerOwnerships(Long playerId) {
        Player player = playerRepository.getOne(playerId);

        List<Property> properties = propertyRepository.findByPlayer(player);
        List<Share> shares = shareRepository.findByPlayer(player);
        List<Bond> bonds = bondRepository.findByPlayer(player);

        Map responseMap = new HashMap();
        responseMap.put("Properties", properties);
        responseMap.put("Shares", shares);
        responseMap.put("Bonds", bonds);

        return responseMap;
    }

    @Override
    public Object sellProperty(Long playerId) {
        return null;
    }

    @Override
    public Object rentProperty(Long playerId) {
        return null;
    }

    @Override
    public Object sellShare(Long playerId) {
        return null;
    }


}
