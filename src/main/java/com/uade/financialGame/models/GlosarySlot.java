package com.uade.financialGame.models;

import com.uade.financialGame.messages.requests.GlosarySlotRequest;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "GlosarySlot")
@Table(name = "glosary_slot")
@Getter
public class GlosarySlot {

    //ATTRIBUTES
    @Id
    @Column(name="GLOSARY_SLOT_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long glosarySlotId;

    private String name;
    private String description;

    @OneToMany(mappedBy = "glosarySlot")
    private List<Card> cards;

    public GlosarySlot(GlosarySlotRequest glosarySlotRequest) {
        this.name = glosarySlotRequest.getName();
        this.description = glosarySlotRequest.getDescription();
    }

    public GlosarySlot(GlosarySlotRequest glosarySlotRequest, Card card) {
        this.name = glosarySlotRequest.getName();
        this.description = glosarySlotRequest.getDescription();
        this.cards = new ArrayList<>();
        cards.add(card);
    }
}
