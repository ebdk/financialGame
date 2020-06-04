package com.uade.financialGame.services.impl;

import com.uade.financialGame.repositories.ProfessionDAO;
import com.uade.financialGame.services.ProfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ProfessionServiceImpl implements ProfessionService {

    @Autowired
    private ProfessionDAO professionRepository;

    @Override
    public Object getAllProfessions(String difficulty) {
        return professionRepository.findAll()
                .stream()
                .filter(x -> difficulty.equals(x.getDifficulty().toString()))
                .collect(Collectors.toList());
    }
}
