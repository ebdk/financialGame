package com.uade.financialGame.models;

import com.uade.financialGame.messages.requests.ShareRequest;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static com.uade.financialGame.utils.MathUtils.getPercentage;

@Entity(name = "Share")
@Table(name = "share")
@Getter
@Setter
public class Share {

    //ATTRIBUTES
    @Id
    @Column(name="SHARE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long shareId;

    private Integer quantity;

    @ManyToOne
    private Company company;

    @ManyToOne
    private Player player;

    @ManyToOne
    private Card card;

    public Share(ShareRequest shareRequest) {
        this.quantity = shareRequest.getQuantity();
    }

	public Share(ShareRequest shareRequest, Card card) {
        this.quantity = shareRequest.getQuantity();
        this.card = card;
	}

	public Integer getValue() {
        return quantity * company.getShareValue();
    }

    public Integer getValueDividends() {
        return getPercentage(quantity * company.getShareDividendValue(), 10);
    }

}
