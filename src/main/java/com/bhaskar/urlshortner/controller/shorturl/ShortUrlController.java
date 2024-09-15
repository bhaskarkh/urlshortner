package com.bhaskar.urlshortner.controller.shorturl;

import com.bhaskar.urlshortner.model.shorturl.UrlDTO;
import com.bhaskar.urlshortner.model.shorturl.UrlResponseDTO;
import com.bhaskar.urlshortner.services.shorturl.ShortUrlServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("shortUrl")
@CrossOrigin(origins = "http://localhost:4200")
public class ShortUrlController {

    @Autowired
    ShortUrlServiceImpl shortUrlServiceImpl;

    @PostMapping("generateShortUrl")
    public ResponseEntity<UrlResponseDTO> generateShortUrl(@RequestBody UrlDTO urlDTO) {
        return shortUrlServiceImpl.generateShortUrl(urlDTO);
    }

    @PostMapping("getUrl")
    public ResponseEntity<UrlResponseDTO> getOriginalUrlFromShortUrl(@RequestBody String shortUrl) {
        return shortUrlServiceImpl.getOriginalUrlFromShortUrl(shortUrl);
    }

    //http://localhost:8080/shortUrl/share/{userId}/{productId}
    @GetMapping("/share/{userId}/{productId}")
    public void openLongShortUrl(@PathVariable String userId, @PathVariable String productId) {
        shortUrlServiceImpl.openLongUrl(userId, productId);
    }

    @GetMapping("/share/{shortUrl}")
    public ResponseEntity<?> openShortUrl(@PathVariable String shortUrl) {
        return shortUrlServiceImpl.openShortUrl(shortUrl);
    }
}
