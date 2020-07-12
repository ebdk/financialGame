package com.uade.financialGame.models;

import lombok.Getter;

import javax.persistence.*;
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

}
