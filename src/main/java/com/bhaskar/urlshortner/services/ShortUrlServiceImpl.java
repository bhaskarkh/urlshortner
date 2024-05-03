package com.bhaskar.urlshortner.services;

import com.bhaskar.urlshortner.model.Url;
import com.bhaskar.urlshortner.model.UrlDTO;
import com.bhaskar.urlshortner.repository.ShortUrlRepository;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Service
public class ShortUrlServiceImpl implements ShortUrlService {
    @Autowired
    ShortUrlRepository shortUrlRepository;
    @Override
    public Url generateShortUrl(UrlDTO urlDTO) {
        if (urlDTO.getUrl() != null) {
            Url url = Url.builder().originalUrl(urlDTO.getUrl()).creationDate(LocalDateTime.now()).shortUrl(createShortUrl(urlDTO.getUrl())).expirationDate(getExpirationDate(urlDTO)).build();
            return shortUrlRepository.save(url);
        }

        return null;
    }

    private LocalDateTime getExpirationDate(UrlDTO urlDTO) {
        if (urlDTO.getExpirationDate() != null)
            return LocalDateTime.parse(urlDTO.getExpirationDate());
        return LocalDateTime.now().plusSeconds(30);
    }

    @Override
    public Url getOriginalUrlFromShortUrl(String shortUrl) {
        return shortUrlRepository.findByShortUrl(shortUrl);
    }

    private String createShortUrl(String url) {
        LocalDateTime localDateTime = LocalDateTime.now();
        String encodedUrl = Hashing.murmur3_32().hashString(url.concat(localDateTime.toString()), StandardCharsets.UTF_8).toString();
        return encodedUrl;
    }


}
