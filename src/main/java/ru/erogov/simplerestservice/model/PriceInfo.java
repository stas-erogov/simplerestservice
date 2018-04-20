package ru.erogov.simplerestservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
public class PriceInfo {

    @Id
    @GeneratedValue
    private long id;

    private BigDecimal price;

    private int qty;

    @JsonProperty("delivery_term")
    private int deliveryTerm;

    private int multiplicity;

    private String city;

    @JsonProperty("offer_key")
    private String offerKey;

    @ManyToOne
    private Article article;

    private PriceInfo() {
    }

    public PriceInfo(BigDecimal price, int qty, int deliveryTerm, int multiplicity, String city, String offerKey, Article article) {
        this.price = price;
        this.qty = qty;
        this.deliveryTerm = deliveryTerm;
        this.multiplicity = multiplicity;
        this.city = city;
        this.offerKey = offerKey;
        this.article = article;
    }

    public Article getArticle() {
        return article;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQty() {
        return qty;
    }

    public int getDeliveryTerm() {
        return deliveryTerm;
    }

    public int getMultiplicity() {
        return multiplicity;
    }

    public String getCity() {
        return city;
    }

    public String getOfferKey() {
        return offerKey;
    }

    public long getId() {
        return id;
    }
}
