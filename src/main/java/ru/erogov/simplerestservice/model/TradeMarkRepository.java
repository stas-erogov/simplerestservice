package ru.erogov.simplerestservice.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TradeMarkRepository extends JpaRepository<TradeMark, Long> {
    Optional<TradeMark> findByTradeMarkName(String tradeMarkName);
}
