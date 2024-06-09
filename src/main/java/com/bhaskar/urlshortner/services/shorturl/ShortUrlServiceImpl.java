package com.bhaskar.urlshortner.services.shorturl;

import com.bhaskar.urlshortner.exception.shorturl.DuplicateOriginalUrlException;
import com.bhaskar.urlshortner.exception.shorturl.InvalidLongUrlException;
import com.bhaskar.urlshortner.exception.shorturl.InvalidShortUrlException;
import com.bhaskar.urlshortner.exception.shorturl.ShortUrlExpiredException;
import com.bhaskar.urlshortner.model.shorturl.UrlResponseDTO;
import com.bhaskar.urlshortner.model.shorturl.Url;
import com.bhaskar.urlshortner.model.shorturl.UrlDTO;
import com.bhaskar.urlshortner.model.user.Profile;
import com.bhaskar.urlshortner.repository.shorturl.ShortUrlRepository;
import com.bhaskar.urlshortner.services.product.ProductServicesImpl;
import com.bhaskar.urlshortner.services.user.UserServiceImpl;
import com.bhaskar.urlshortner.util.Utility;
import com.google.common.hash.Hashing;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ShortUrlServiceImpl implements ShortUrlService {
    @Autowired
    ShortUrlRepository shortUrlRepository;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    ProductServicesImpl productServices;

    @Override
    public ResponseEntity<?> generateShortUrl(UrlDTO urlDTO) {
        if (urlDTO != null && urlDTO.getUrl() != null) {
            Url url = Url.builder()
                    .originalUrl(urlDTO.getUrl())
                    .creationDate(Utility.getCurrentDateTime())
                    .shortUrl(createShortUrl(urlDTO.getUrl()))
                    .expirationDate(getExpirationDate(urlDTO))
                    .build();
            Url dbUrl = shortUrlRepository.findByOriginalUrl(url.getOriginalUrl());
            if (dbUrl != null && dbUrl.getExpirationDate().isAfter(LocalDateTime.now())) {
                throw new DuplicateOriginalUrlException();
            }
            Url savedUrl = shortUrlRepository.save(url);
            if (savedUrl != null) {
                UrlResponseDTO urlResponseDTO = UrlResponseDTO.builder()
                        .originalUrl(savedUrl.getOriginalUrl())
                        .shortUrl(savedUrl.getShortUrl())
                        .expirationDate(savedUrl.getExpirationDate().toString())
                        .build();
                return new ResponseEntity<>(urlResponseDTO, HttpStatus.OK);
            }
        }
        throw new InvalidLongUrlException();
    }

    @Override
    public ResponseEntity<?> getOriginalUrlFromShortUrl(String shortUrl) {
        Url url = getUrlDetailsFromShortUrl(shortUrl);
        if (url != null) {
            UrlResponseDTO urlResponseDTO = UrlResponseDTO.builder()
                    .originalUrl(url.getOriginalUrl())
                    .shortUrl(url.getShortUrl())
                    .expirationDate(url.getExpirationDate().toString())
                    .build();
            return new ResponseEntity<>(urlResponseDTO, HttpStatus.OK);
        }
        throw new InvalidShortUrlException();
    }


    @Override
    public void openLongUrl(String userId, String productId) {
        Optional<BigDecimal> amount = productServices.getProductPriceByProductId(Long.parseLong(productId));
        amount.map(price -> userService.increaseUserPoint(Long.parseLong(userId), price));
    }

    @Override
    public ResponseEntity<?> openShortUrl(String shortUrl) {
        Url longUrl = getUrlDetailsFromShortUrl(shortUrl);
        validateLongUrl(longUrl);
        Long userId = extractUserIdFromUrl(longUrl);
        Long productId = extractProductIdFromUrl(longUrl);
        Optional<BigDecimal> amount = productServices.getProductPriceByProductId(productId);
        Optional<Profile> profile = amount.map(price -> userService.increaseUserPoint(userId, price));
        return profile.map(user -> new ResponseEntity<>(user, HttpStatus.OK)).orElse(new ResponseEntity<>(Profile.builder().build(), HttpStatus.NO_CONTENT));
    }


    private Url getUrlDetailsFromShortUrl(String shortUrl) {
        return shortUrlRepository.findByShortUrl(shortUrl);
    }

    private LocalDateTime getExpirationDate(UrlDTO urlDTO) {
        if (urlDTO.getExpirationDate() != null)
            return LocalDateTime.parse(urlDTO.getExpirationDate());
        return LocalDateTime.now().plusSeconds(30);
    }

    public Long extractUserIdFromUrl(Url url) {
        //http://localhost:8080/shortUrl/share/{userId}/{productId}
        String userID = url.getOriginalUrl().split("/")[5];
        return Long.parseLong(userID);
    }

    public Long extractProductIdFromUrl(Url url) {
        //http://localhost:8080/shortUrl/share/{userId}/{productId}
        String productId = url.getOriginalUrl().split("/")[6];
        return Long.parseLong(productId);
    }

    public Boolean validateLongUrl(Url url) {
        //http://localhost:8080/shortUrl/share/{userId}/{productId}
        if (url != null) {
            if (url.getExpirationDate().isBefore(LocalDateTime.now()))
                throw new ShortUrlExpiredException(url.getExpirationDate().toString());
            if (StringUtils.isNotEmpty(url.getOriginalUrl()) && url.getOriginalUrl().split("/").length == 7) {
                return true;
            }
        }
        throw new InvalidLongUrlException();
    }

    private String createShortUrl(String url) {
        LocalDateTime localDateTime = Utility.getCurrentDateTime();
        String encodedUrl = Hashing.murmur3_32().hashString(url.concat(localDateTime.toString()), StandardCharsets.UTF_8).toString();
        return encodedUrl;
    }


}
