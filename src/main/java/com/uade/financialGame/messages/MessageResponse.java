package com.uade.financialGame.messages;

import com.uade.financialGame.utils.Pair;
import com.uade.financialGame.utils.PairObject;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class MessageResponse implements Response {

    //ATTRIBUTES
    private Map<String, String> mapMessage;
    private Map<String, Object> mapObject;

    //BUILDERS
    public MessageResponse(Pair... args) {
        super();
        this.mapMessage = new HashMap<>();
        for (Pair arg : args) {
            mapMessage.put(arg.getKey(), arg.getValue());
        }
    }

    public MessageResponse(PairObject... args) {
        super();
        this.mapObject = new HashMap<>();
        for (PairObject arg : args) {
            mapObject.put(arg.getKey(), arg.getValue());
        }
    }

    public MessageResponse(String string) {
        super();
        this.mapMessage = new HashMap<>();
        mapMessage.put("message", string);
    }

    public MessageResponse() {
    }

}
