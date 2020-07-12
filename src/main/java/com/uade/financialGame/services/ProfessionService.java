package com.uade.financialGame.services;

import com.uade.financialGame.messages.requests.ProfessionRequest;

import java.util.List;

public interface ProfessionService {
    Object getAllProfessions(String difficulty);

    Object createProfession(List<ProfessionRequest> professionRequest);
}
