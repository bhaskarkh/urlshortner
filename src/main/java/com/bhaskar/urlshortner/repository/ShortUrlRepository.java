package com.bhaskar.urlshortner.repository;

import com.bhaskar.urlshortner.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortUrlRepository extends JpaRepository<Url, Long> {
    Url findByShortUrl(String sortUrl);
}
