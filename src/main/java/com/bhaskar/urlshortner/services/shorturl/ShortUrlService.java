package com.bhaskar.urlshortner.services.shorturl;

import com.bhaskar.urlshortner.model.shorturl.UrlDTO;
import org.springframework.http.ResponseEntity;

public interface ShortUrlService {
    ResponseEntity<?> generateShortUrl(UrlDTO urlDTO);

    ResponseEntity<?> getOriginalUrlFromShortUrl(String shortUrl);
    void openLongUrl(String userId,String productId);
    ResponseEntity<?> openShortUrl(String shortUrl);

}
