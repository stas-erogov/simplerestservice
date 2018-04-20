package ru.erogov.simplerestservice.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleReporitory extends JpaRepository<Article, Long> {
    Optional<Article> findByArticleCode(String articleCode);
}
