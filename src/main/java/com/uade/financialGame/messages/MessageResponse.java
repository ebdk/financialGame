package com.uade.financialGame.messages;

import com.uade.financialGame.utils.Pair;

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

    public MessageResponse(String string) {
        super();
        this.mapMessage = new HashMap<>();
        mapMessage.put("message", string);
    }

    public MessageResponse() {
    }

    public Map<String, String> getMapMessage() {
        return mapMessage;
    }
}
