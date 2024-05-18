package com.bhaskar.urlshortner.controller.shorturl;

import com.bhaskar.urlshortner.model.shorturl.UrlDTO;
import com.bhaskar.urlshortner.services.shorturl.ShortUrlServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("shortUrl")
public class ShortUrlController {

    @Autowired
    ShortUrlServiceImpl shortUrlServiceImpl;

    @PostMapping("generateShortUrl")
    public ResponseEntity<?> generateShortUrl(@RequestBody UrlDTO urlDTO) {
        return shortUrlServiceImpl.generateShortUrl(urlDTO);
    }
    @PostMapping("getUrl")
    public ResponseEntity<?> getOriginalUrlFromShortUrl(@RequestBody String shortUrl) {
        return shortUrlServiceImpl.getOriginalUrlFromShortUrl(shortUrl);
    }
    @PostMapping("openShortUrl")
    public void openShortUrl(@RequestBody String shortUrl) {
        shortUrlServiceImpl.openShortUrl(shortUrl);
    }
}
