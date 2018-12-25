package ru.erogov.simplerestservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TradeMark {
    @Id
    @GeneratedValue
    @JsonProperty("trademark_id")
    private long trademarkId;

    @JsonProperty("trademark_name")
    private String tradeMarkName;

    private TradeMark() {
    }

    public TradeMark(final String tradeMarkName) {
        this.tradeMarkName = tradeMarkName;
    }

    public long getTrademarkId() {
        return trademarkId;
    }

    public String getTradeMarkName() {
        return tradeMarkName;
    }
}
