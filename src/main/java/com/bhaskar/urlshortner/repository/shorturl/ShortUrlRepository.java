package com.bhaskar.urlshortner.repository.shorturl;

import com.bhaskar.urlshortner.model.shorturl.Url;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortUrlRepository extends JpaRepository<Url, Long> {
    Url findByShortUrl(String sortUrl);
}
