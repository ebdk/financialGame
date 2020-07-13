package com.uade.financialGame.models;

import com.uade.financialGame.messages.requests.ShareRequest;
import com.uade.financialGame.messages.responses.ShareResponse;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.List;

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

    //@ManyToOne(cascade = CascadeType.MERGE ,fetch = FetchType.LAZY)
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

    public Share(ShareRequest shareRequest, Card card, Company company) {
        this.quantity = shareRequest.getQuantity();
        this.card = card;
        this.company = company;
    }

    public Share(Company company) {
        this.quantity = 0;
        this.company = company;
    }

    public Share(Share share, Player player) {
        this.quantity = share.getQuantity();
        this.company = share.getCompany();
        this.player = player;
    }

    public Share() {
    }

    public Integer getValue() {
        return quantity * company.getShareValue();
    }

    public Integer getValue(Integer quantity) {
        return quantity * company.getShareValue();
    }

    public Integer getValueDividends() {
        return getPercentage(quantity * company.getShareDividendValue(), 10);
    }

    public boolean higherThanZeroQuantity() {
        return quantity > 0;
    }

	public ShareResponse toDto() {
        return new ShareResponse(this);
	}

}
