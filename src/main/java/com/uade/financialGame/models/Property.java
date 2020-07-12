package com.uade.financialGame.models;

import com.uade.financialGame.messages.responses.PropertyResponse;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "Property")
@Table(name = "property")
@Getter
@Setter
public class Property {

    //ATTRIBUTES
    @Id
    @Column(name="PROPERTY_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long propertyId;

    private PropertyName propertyName;
    private Integer buyValue;
    private Integer sellValue;
    private Integer rentValue;
    private Boolean beingRented;
    private Boolean isRentable;
    private Boolean canBeSold;

    private Player player;

    public enum PropertyName {
        HOUSE,
        BLOCK,
        YACHT,
        TRUCK,
        CAR
    }


    //METHODS
    public PropertyResponse toDto() {
        return new PropertyResponse(this);
    }

}
