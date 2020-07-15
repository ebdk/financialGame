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

import static com.uade.financialGame.models.Transaction.NumericType.NUMBER;
import static com.uade.financialGame.models.Transaction.TransactionTime.CURRENT;
import static com.uade.financialGame.models.Transaction.TransactionType.*;
import static com.uade.financialGame.utils.MathUtils.getPercantageBewteenTwoRandom;
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

        Transaction passiveTransaction = new Transaction("Pago de Deuda", PASSIVE, NUMBER, CURRENT, (-1) * amount);
        Transaction expensesTransaction = new Transaction("Pago de Deuda", EXPENSES, NUMBER, CURRENT, amount);
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

        Transaction expensesTransaction = new Transaction("Caridad", EXPENSES, NUMBER, CURRENT, amount);

        player.setHasDonated(true);

        player.addTransactionsToBalance(singletonList(expensesTransaction));

        playerRepository.save(player);

        return balance.toDto();
    }

    @Override
    public Object nextMonth(Long playerId) {
        Player player = playerRepository.getOne(playerId);
        Integer monthNumber = player.getCurrentMonth() + 1;

        TransactionList balance = player.getBalance();

        //OLD TRANSACTIONS
        List<Transaction> monthlyIncomes = balance.getMonthlyIncomes();
        List<Transaction> monthlyExpenses = balance.getMonthlyExpenses();

        //NEW TRANSACTIONS
        List<Transaction> thisMonthIncomes = new ArrayList<>();
        List<Transaction> thisMonthExpenses = new ArrayList<>();

        if(player.isEmployed()) {//INGRESOS MENSUALES
            //thisMonthIncomes.addAll(chargeBonds(player));
            //thisMonthIncomes.addAll(shareDividends(player));
            for(Transaction transaction : monthlyIncomes) {
                Transaction newTransaction = new Transaction(transaction, monthNumber);
                thisMonthIncomes.add(newTransaction);
            }
        } else {//SI JUGADOR NO ESTA EMPLEADO, NO TIENE INGRESOS ESE MES
            player.setEmployed(true);
            Transaction newTransaction = new Transaction("Desempleado en el Mes " + monthNumber, INCOMES, NUMBER, CURRENT, 0);
            thisMonthIncomes.add(newTransaction);
        }//GASTOS MENSUALES
        for(Transaction transaction : monthlyExpenses) {
            Transaction newTransaction = new Transaction(transaction, monthNumber);
            thisMonthExpenses.add(newTransaction);
        }

        //PROPIEDADES
        /*
        List<Property> propertiesOnRent = player.getActiveProperties()
                .stream()
                .filter(Property::beingRented)
                .collect(Collectors.toList());
         */
        List<Property> activeProperties = player.getActiveProperties();
        if(!activeProperties.isEmpty()) {
            Integer rentValue = activeProperties.stream().mapToInt(Property::rent).sum();
            if(rentValue > 0) {
                Transaction newIncomeTransaction = new Transaction("Cobro de los Alquileres Mes " + monthNumber, INCOMES, NUMBER, CURRENT, rentValue);
                thisMonthIncomes.add(newIncomeTransaction);
                Transaction newExpenseTransaction = new Transaction("Gasto de mantenimiento Propiedades en Alquiler Mes " + monthNumber, EXPENSES, NUMBER, CURRENT, getPercantageBewteenTwoRandom(rentValue, 5, 15));
                thisMonthExpenses.add(newExpenseTransaction);
            }
        }

        //ACCIONES
        List<Share> shares = player.getActiveShares();
        if(!shares.isEmpty()) {
            Integer dividendsValue = shares.stream().mapToInt(Share::getValueDividends).sum();
            if(dividendsValue > 0) {
                Transaction newIncomeTransaction = new Transaction("Cobro de los Dividendos de las Acciones Mes " + monthNumber, INCOMES, NUMBER, CURRENT, dividendsValue);
                thisMonthIncomes.add(newIncomeTransaction);
            }
        }

        //BOND
        List<Bond> bonds = player.getActiveBonds();
        if(!bonds.isEmpty()) {
            Integer chargedBonds = bonds.stream().mapToInt(bond -> bond.charge(monthNumber)).sum();
            if(chargedBonds > 0) {
                Transaction shareTransaction = new Transaction("Cobrado Bonos Mes  " + monthNumber,
                        INCOMES, NUMBER, CURRENT, chargedBonds);
                thisMonthIncomes.add(shareTransaction);
            }
        }

        player.addTransactionsToBalance(thisMonthIncomes);
        player.addTransactionsToBalance(thisMonthExpenses);

        player.addMonth();
        playerRepository.save(player);
        return balance.toDto();
    }

    /*
    private List<Transaction> shareDividends(Player player) {
        List<Transaction> thisMonthSharesDividends = new ArrayList<>();
        List<Share> shares = player.getShares();
        shares.forEach(share -> {
            if(share.getValueDividends() >= 0) {
                Transaction shareTransaction = new Transaction("Dividends of Shares of " + share.getCompany().getName(),
                        INCOMES, NUMBER, CURRENT, share.getValueDividends());
                thisMonthSharesDividends.add(shareTransaction);
            }
        });
        return thisMonthSharesDividends;
    }

    private List<Transaction> chargeBonds(Player player) {
        List<Transaction> thisMonthBondsDividends = new ArrayList<>();
        List<Bond> bonds = player.getBonds();
        bonds.forEach(bond -> {
            if(bond.canBeCharged(player.getCurrentMonth())) {
                Transaction shareTransaction = new Transaction("Charged Bond of " + bond.getSmallDescription(),
                        INCOMES, NUMBER, CURRENT, bond.charge());
                thisMonthBondsDividends.add(shareTransaction);
            };
        });
        return thisMonthBondsDividends;
    }
     */


    @Override
    public Object showPlayerOwnerships(Long playerId) {
        Player player = playerRepository.getOne(playerId);

        List<Share> shares = player.getActiveShares();
        List<Bond> bonds = player.getActiveBonds();
        List<Property> properties = player.getActiveProperties();

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
        property.putOnRent();

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

    @Override
    public Object getBalance(Long playerId) {

        Player player = playerRepository.getOne(playerId);

        return player.getBalance().toDto();
    }

    @Override
    public Object getPlayer(Long playerId) {
        return playerRepository.getOne(playerId).toDto();
    }


}
