package com.bhaskar.urlshortner.services;

import com.bhaskar.urlshortner.model.Url;
import com.bhaskar.urlshortner.model.UrlDTO;

public interface ShortUrlService {
    Url generateShortUrl(UrlDTO urlDTO);

    Url getOriginalUrlFromShortUrl(String shortUrl);

}
