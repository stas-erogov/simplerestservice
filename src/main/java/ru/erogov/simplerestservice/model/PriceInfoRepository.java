package ru.erogov.simplerestservice.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface PriceInfoRepository extends JpaRepository<PriceInfo, Long> {
    Collection<PriceInfo> findByArticleArticleCode(String articleCode);
}
