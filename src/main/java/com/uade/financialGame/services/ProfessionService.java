package com.uade.financialGame.services;

import com.uade.financialGame.messages.requests.ProfessionRequest;

public interface ProfessionService {
    Object getAllProfessions(String difficulty);

    Object createProfession(ProfessionRequest professionRequest);
}
