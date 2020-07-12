package com.uade.financialGame.services.impl;

import com.uade.financialGame.messages.requests.ProfessionRequest;
import com.uade.financialGame.models.Profession;
import com.uade.financialGame.repositories.ProfessionDAO;
import com.uade.financialGame.repositories.TransactionDAO;
import com.uade.financialGame.repositories.TransactionListDAO;
import com.uade.financialGame.services.ProfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class ProfessionServiceImpl implements ProfessionService {

    @Autowired
    private ProfessionDAO professionRepository;

    @Autowired
    private TransactionListDAO transactionListRepository;

    @Autowired
    private TransactionDAO transactionRepository;

    @Override
    public Object getAllProfessions(String difficulty) {
        return professionRepository.findAll()
                .stream()
                .filter(x -> difficulty.equals(x.getDifficulty().toString()))
                .map(Profession::toDto)
                .collect(toList());
    }

    @Override
    public Object createProfession(List<ProfessionRequest> professionRequestList) {
        List<Profession> professions = professionRequestList
                .stream()
                .map(ProfessionRequest::toEntity)
                .collect(toList());

        professionRepository.saveAll(professions);
        return professions.stream().map(Profession::toDto).collect(toList());
    }

}
