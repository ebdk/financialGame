package com.uade.financialGame.models;

import com.uade.financialGame.messages.requests.PropertyRequest;
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

    @ManyToOne
    private Player player;

    @ManyToOne
    private Card card;

	public Property(PropertyRequest propertyRequest) {
	}

	public Property(PropertyRequest propertyRequest, Card card) {
	    this.propertyName = PropertyName.valueOf(propertyRequest.getPropertyName());
	    this.buyValue = propertyRequest.getBuyValue();
	    this.sellValue = propertyRequest.getSellValue();
	    this.rentValue = propertyRequest.getRentValue();
	    this.isRentable = propertyRequest.getIsRentable();
	    this.canBeSold = propertyRequest.getCanBeSold();
	    this.beingRented = false;
	    this.card = card;
	}

	public Property() {
	}

	public Property(Property cardProperty, Player player) {
		this.propertyName = cardProperty.getPropertyName();
		this.buyValue = cardProperty.getBuyValue();
		this.sellValue = cardProperty.getSellValue();
		this.rentValue = cardProperty.getRentValue();
		this.isRentable = cardProperty.getIsRentable();
		this.canBeSold = cardProperty.getCanBeSold();
		this.beingRented = false;
		this.player = player;

	}

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
