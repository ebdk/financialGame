package com.uade.financialGame.services.impl;

import com.uade.financialGame.messages.requests.CardRequest;
import com.uade.financialGame.models.Card;
import com.uade.financialGame.models.Company;
import com.uade.financialGame.repositories.CardDAO;
import com.uade.financialGame.repositories.CompanyDAO;
import com.uade.financialGame.repositories.TransactionDAO;
import com.uade.financialGame.repositories.TransactionListDAO;
import com.uade.financialGame.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.uade.financialGame.models.Card.EffectType.COMPANY_VALUE_CHANGE;
import static com.uade.financialGame.models.Card.EffectType.SHARE_BUY;
import static java.util.stream.Collectors.toList;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardDAO cardRepository;

    @Autowired
    private TransactionListDAO transactionListRepository;

    @Autowired
    private TransactionDAO transactionRepository;

    @Autowired
    private CompanyDAO companyRepository;

    @Override
    public Object getRandomCard(String cardType, String cardDifficulty) {
        List<Card> allCards = cardRepository.findAll();
        List<Card> filteredCards  = allCards
                .stream()
                .filter(x -> (cardType.equals(x.getOptionType().toString())) && (cardDifficulty.equals(x.getDifficulty().toString())) )
                .collect(toList());
        Random rand = new Random();
        if(!(filteredCards.isEmpty())){
            return filteredCards.get(rand.nextInt(filteredCards.size())).toDto();
        } else {
            List<Card> sameTypeCards  = allCards
                    .stream()
                    .filter(x -> cardType.equals(x.getOptionType().toString()))
                    .collect(toList());
            return sameTypeCards.get(rand.nextInt(sameTypeCards.size())).toDto();
        }
    }


    @Override
    public Object createCard(List<CardRequest> cardRequestList) {

        List<Long> companiesIdsInvolved = companiesIdsInvolved(cardRequestList);
        List<Company> companiesInvolved = companiesInvolved(companiesIdsInvolved);

        List<Card> cards = cardRequestList
                .stream()
                .map(cardRequest -> cardRequest.toEntity(companiesInvolved))
                .collect(toList());

        cardRepository.saveAll(cards);
        return cards.stream().map(Card::toDto).collect(toList());
    }

    private List<Long> companiesIdsInvolved(List<CardRequest> cardRequestList)  {
        List<Long> ids = new ArrayList<>();
        cardRequestList.forEach(cardRequest -> {
            if(COMPANY_VALUE_CHANGE.equals(cardRequest.getEffectType())) {
                cardRequest.getCompanyChangesRequest()
                        .forEach(companyChangesRequest -> ids.add(companyChangesRequest.getCompanyId()));
            }
            if(SHARE_BUY.equals(cardRequest.getEffectType())){
                cardRequest.getSharesRequest()
                        .forEach(shareRequest -> ids.add(shareRequest.getCompanyId()));
            }
        });

        return ids;
    }

    private List<Company> companiesInvolved(List<Long> ids) {
        return companyRepository.findAll()
                .stream()
                .filter(company -> ids.contains(company.getCompanyId()))
                .collect(toList());
    }

}
