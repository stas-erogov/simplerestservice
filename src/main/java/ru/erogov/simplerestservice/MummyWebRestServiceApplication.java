package ru.erogov.simplerestservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.erogov.simplerestservice.model.Article;
import ru.erogov.simplerestservice.model.ArticleReporitory;
import ru.erogov.simplerestservice.model.PriceInfo;
import ru.erogov.simplerestservice.model.PriceInfoRepository;
import ru.erogov.simplerestservice.model.TradeMark;
import ru.erogov.simplerestservice.model.TradeMarkRepository;

import java.math.BigDecimal;
import java.util.Arrays;

@SpringBootApplication
public class MummyWebRestServiceApplication {

	@Bean
    CommandLineRunner init(final TradeMarkRepository tradeMarkRepository, final ArticleReporitory articleReporitory, final PriceInfoRepository priceInfoRepository) {
        final int[] i = {1};
	    return (evt) -> Arrays.asList("schob,byk,gav,mg".split(","))
                .forEach(tm -> {
                    TradeMark tradeMark = tradeMarkRepository.save(new TradeMark(tm));
                    Article article = articleReporitory.save(new Article("123" + i[0], tm + "desc", false, tradeMark));
                    priceInfoRepository.save(new PriceInfo(BigDecimal.valueOf(i[0] *10.0), 1, 1+ i[0], 1, "Moscow", "key"+ i[0], article));
                    priceInfoRepository.save(new PriceInfo(BigDecimal.valueOf(i[0] *11.0), 2, 1+ i[0], 2, "London", "key"+ i[0], article));
                    priceInfoRepository.save(new PriceInfo(BigDecimal.valueOf(i[0] *11.0), 3, 1+ i[0], 3, "Paris", "key"+ i[0], article));
                    i[0] += 1;
                });
    }

	public static void main(final String[] args) {
		SpringApplication.run(MummyWebRestServiceApplication.class, args);
	}
}
