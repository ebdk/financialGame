package com.uade.financialGame.messages;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

public class MessageResponse implements Response {

    private Map<String, String> mapMessage;

    public MessageResponse(Pair... args) {
        super();
        this.mapMessage = new HashMap<>();
        for (Pair arg : args) {
            mapMessage.put(arg.getKey().toString(), arg.getValue().toString());
        }
    }

    public MessageResponse() {
    }
}
