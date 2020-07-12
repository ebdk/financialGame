package com.uade.financialGame.models;

import com.uade.financialGame.messages.requests.PropertyRequest;
import com.uade.financialGame.messages.responses.PropertyResponse;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static com.uade.financialGame.models.Property.PropertyStatus.*;

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
    private PropertyStatus propertyStatus;
    private Boolean isRentable;

	public void rent() {
		if(notSold()) {
			this.propertyStatus = AVAILABLE.equals(propertyStatus) ? RENTING : AVAILABLE;
		}
	}

	public void sell() {
		if(notSold()) {
			this.propertyStatus = SOLD;
		}
	}

	public enum PropertyStatus {
    	AVAILABLE,
		RENTING,
		SOLD
	}


	public enum PropertyName {
		HOUSE,
		BLOCK,
		YACHT,
		TRUCK,
		CAR
	}

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
		this.propertyStatus = AVAILABLE;
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
		this.propertyStatus = AVAILABLE;
		this.player = player;

	}

	public boolean notSold() {
		return !SOLD.equals(propertyStatus);
	}

    //METHODS
    public PropertyResponse toDto() {
        return new PropertyResponse(this);
    }

}
