package com.uade.financialGame.services.impl;

import com.uade.financialGame.models.*;
import com.uade.financialGame.repositories.*;
import com.uade.financialGame.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.uade.financialGame.models.Transaction.NumericType.NUMBER;
import static com.uade.financialGame.models.Transaction.TransactionTime.CURRENT;
import static com.uade.financialGame.models.Transaction.TransactionType.*;
import static com.uade.financialGame.utils.MathUtils.getPercentage;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerDAO playerRepository;

    @Autowired
    private TransactionListDAO transactionListRepository;

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

        player.addTransactionsToBalance(singletonList(expensesTransaction));

        playerRepository.save(player);

        return balance.toDto();
    }

    @Override
    public Object nextMonth(Long playerId) {
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
            //playerRepository.save(player);
            Transaction newTransaction = new Transaction("No job for Month " + monthNumber, INCOMES, NUMBER, CURRENT, 0);
            thisMonthIncomes.add(newTransaction);
        }
        for(Transaction transaction : monthlyExpenses) {
            Transaction newTransaction = new Transaction(transaction, monthNumber);
            thisMonthExpenses.add(newTransaction);
        }
        thisMonthIncomes.addAll(thisMonthExpenses);

        player.addTransactionsToBalance(thisMonthIncomes);


        //transactionRepository.saveAll(thisMonthIncomes);
        //transactionListRepository.save(balance);
        playerRepository.save(player);
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

        List<Share> shares = shareRepository.findByPlayer(player);
        List<Bond> bonds = bondRepository.findByPlayer(player);
        List<Property> properties = propertyRepository.findByPlayer(player)
                .stream()
                .filter(Property::notSold)
                .collect(Collectors.toList());

        Map responseMap = new HashMap();
        responseMap.put("Properties", properties.stream().map(Property::toDto));
        responseMap.put("Shares", shares.stream().map(Share::toDto));
        responseMap.put("Bonds", bonds.stream().map(Bond::toDto));

        return responseMap;
    }

    @Override
    public Object sellProperty(Long playerId, Long propertyId) {
        Player player = playerRepository.getOne(playerId);

        Property property = player.getPropertyByPropertyId(propertyId);

        if(!property.notSold()) {
            return "ERROR se intento vender una casa ya vendida";
        }

        property.sell();

        player.addTransactionsToBalance(singletonList(new Transaction("Venta de " + property.getPropertyName(),
                INCOMES, NUMBER, CURRENT, property.getSellValue())));

        playerRepository.save(player);

        return player.getBalance().toDto();
    }

    @Override
    public Object rentProperty(Long playerId, Long propertyId) {
        Player player = playerRepository.getOne(playerId);

        Property property = player.getPropertyByPropertyId(propertyId);
        property.rent();

        playerRepository.save(player);

        return player.getBalance().toDto();
    }

    @Override
    public Object sellShare(Long playerId, Long shareId, Integer quantity) {
        Player player = playerRepository.getOne(playerId);

        Share share = player.getShareByShareId(shareId);

        if(share.getQuantity() < quantity) {
            return "ERROR se intento vender mas acciones de las que hay";
        }

        Integer sellValue = share.getValue(quantity);
        share.setQuantity(share.getQuantity() - quantity);

        player.addTransactionsToBalance(singletonList(new Transaction(String.format("Venta de %s Acciones de %s", quantity, share.getCompany().getName()),
                INCOMES, NUMBER, CURRENT, sellValue)));


        playerRepository.save(player);

        return player.getBalance().toDto();
    }


}
