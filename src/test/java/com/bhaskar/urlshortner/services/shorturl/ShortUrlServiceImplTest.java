package com.bhaskar.urlshortner.services.shorturl;

import com.bhaskar.urlshortner.model.shorturl.Url;
import com.bhaskar.urlshortner.util.Utility;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;


import java.time.LocalDateTime;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ShortUrlServiceImplTest {

    @Autowired
    ShortUrlServiceImpl shortUrlService;

    @BeforeEach
    void setup() {
        shortUrlService = new ShortUrlServiceImpl();
    }


    @Test
    void validLongUrl() {
        Url url = Url.builder()
                .originalUrl("http://localhost:8080/share/1/3")
                .build();
        assertEquals(true, shortUrlService.validLongUrl(url));
    }

    @Test
    void extractUserIdFromUrl() {
        Url url = Url.builder()
                .originalUrl("http://localhost:8080/share/1/3")
                .build();
        assertEquals(1, shortUrlService.extractUserIdFromUrl(url));
    }

    @Test
    void extractProductIdFromUrl() {
        Url url = Url.builder()
                .originalUrl("http://localhost:8080/share/1/3")
                .build();
        assertEquals(3, shortUrlService.extractProductIdFromUrl(url));
    }
}