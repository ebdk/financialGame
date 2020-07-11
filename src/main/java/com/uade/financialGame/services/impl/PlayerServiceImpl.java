package com.uade.financialGame.services.impl;

import com.uade.financialGame.models.*;
import com.uade.financialGame.repositories.PlayerDAO;
import com.uade.financialGame.repositories.TransactionDAO;
import com.uade.financialGame.repositories.TransactionListDAO;
import com.uade.financialGame.repositories.TurnDAO;
import com.uade.financialGame.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    /*
    @Autowired
    private MonthDAO monthRepository;
     */

    @Autowired
    private TurnDAO turnRepository;

    @Override
    public Object payDebt(Long playerId, Integer amount) {
        Player player = playerRepository.getOne(playerId);

        Turn turn;
        if(player.getTurns().isEmpty()) {
            turn = new Turn(player, null, 0);
        } else {
            turn = player.getLatestTurn();
        }

        TransactionList balance = player.getBalance();
        /*
        TransactionList balance = transactionListRepository.findAll()
                .stream()
                .filter(x -> x.getPlayer().getPlayerId().equals(playerId))
                .collect(java.util.stream.Collectors.toList()).get(0);
                */

        Transaction passiveTransaction = new Transaction("Pay Debt", PASSIVE, NUMBER, CURRENT, (-1) * amount);
        Transaction expensesTransaction = new Transaction("Pay Debt", EXPENSES, NUMBER, CURRENT, amount);
        List<Transaction> transactions = asList(passiveTransaction, expensesTransaction);

        /*
        TransactionList turnTransactionList = turn.getTransactionList();
        turnTransactionList.addTransactions(transactions);
         */

        balance.addTransactions(transactions);

        transactionRepository.saveAll(transactions);
        transactionListRepository.save(balance);
        turnRepository.save(turn);

        return balance.toDto();
    }

    @Override
    public Object donateToCharity(Long playerId) {
        Player player = playerRepository.getOne(playerId);
        Turn turn = player.getLatestTurn();

        TransactionList balance = player.getBalance();

        Integer playerSalary = player.getBalanceValuesMap().get(INCOMES);
        //Integer amount = (int)(playerSalary*(10.0f/100.0f)); //10% of the salary
        Integer amount = getPercentage(playerSalary, 10);

        Transaction expensesTransaction = new Transaction("Charity", EXPENSES, NUMBER, CURRENT, amount);

        /*TransactionList turnTransactionList = turn.getTransactionList();
        turnTransactionList.addTransaction(expensesTransaction);
         */
        player.setHasDonated(true);

        balance.addTransaction(expensesTransaction);

        transactionRepository.save(expensesTransaction);
        transactionListRepository.save(balance);
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
            thisMonthIncomes.addAll(bondsDividends(player));
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

        /*
        Month month = new Month(monthNumber, player);
        TransactionList monthTransactionList = new TransactionList(thisMonthIncomes);
        month.setTransactionList(monthTransactionList);
         */

        transactionRepository.saveAll(thisMonthIncomes);
        transactionListRepository.save(balance);
        //monthRepository.save(month);

        return balance.toDto();
    }

    private List<Transaction> shareDividends(Player player) {
        List<Transaction> thisMonthSharesDividends = new ArrayList<>();
        List<Share> shares = player.getShares();
        shares.forEach(x -> {
            Transaction shareTransaction = new Transaction("Shares of " + x.getCompany().getName(),
                    INCOMES, NUMBER, CURRENT, x.getValueDividends());
            thisMonthSharesDividends.add(shareTransaction);
        });
        return thisMonthSharesDividends;
    }

    private List<Transaction> bondsDividends(Player player) {
        List<Transaction> thisMonthBondsDividends = new ArrayList<>();
        List<Bond> bonds = player.getBonds();
        bonds.forEach(x -> {
            Transaction shareTransaction = new Transaction("Shares of " + x.getCompany().getName(),
                    INCOMES, NUMBER, CURRENT, x.getValueDividends());
            thisMonthBondsDividends.add(shareTransaction);
        });
        return thisMonthBondsDividends;
    }

}
