package ru.erogov.simplerestservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Article {
    @Id
    @GeneratedValue
    @JsonProperty("article_id")
    private long articleId;

    @JsonProperty("article_code")
    private String articleCode;

    private String description;

    @JsonProperty("is_analog")
    private boolean isAnalog;

    @OneToOne
    @JsonProperty("trademark")
    private TradeMark tradeMark;

    private Article() {
    }

    public Article(final String articleCode,
                   final String description,
                   final boolean isAnalog,
                   final TradeMark tradeMark) {
        this.articleCode = articleCode;
        this.description = description;
        this.isAnalog = isAnalog;
        this.tradeMark = tradeMark;
    }

    public long getArticleId() {
        return articleId;
    }

    public String getArticleCode() {
        return articleCode;
    }

    public String getDescription() {
        return description;
    }

    @JsonIgnore
    public boolean isAnalog() {
        return isAnalog;
    }

    public TradeMark getTradeMark() {
        return tradeMark;
    }
}
