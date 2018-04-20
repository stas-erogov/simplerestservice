package ru.erogov.simplerestservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.erogov.simplerestservice.model.ArticleReporitory;
import ru.erogov.simplerestservice.model.PriceInfo;
import ru.erogov.simplerestservice.model.PriceInfoRepository;
import ru.erogov.simplerestservice.model.TradeMarkRepository;
import ru.erogov.simplerestservice.service.UserAuthenticationService;

import java.util.Collection;

@RestController
@RequestMapping("/article")
class ArticleRestController {

    private final ArticleReporitory articleReporitory;
    private final PriceInfoRepository priceInfoRepository;

    @Autowired
    public ArticleRestController(ArticleReporitory articleReporitory, PriceInfoRepository priceInfoRepository, TradeMarkRepository tradeMarkRepository, UserAuthenticationService userAuthenticationService) {
        this.articleReporitory = articleReporitory;
        this.priceInfoRepository = priceInfoRepository;
    }

    @RequestMapping(value = "/{articleCode}", method = RequestMethod.GET)
    Collection<PriceInfo> readPriceInfos(@PathVariable String articleCode) {
        this.validateArticleCode(articleCode);
        return priceInfoRepository.findByArticleArticleCode(articleCode);
    }

    private void validateArticleCode(String articleCode) {
        this.articleReporitory.findByArticleCode(articleCode)
                .orElseThrow(()->new ArticleNotFoudException(articleCode));
    }

    private class ArticleNotFoudException extends RuntimeException {
        public ArticleNotFoudException(String articleCode) {
            super("Could not found article with code: " + articleCode);
        }
    }
}
