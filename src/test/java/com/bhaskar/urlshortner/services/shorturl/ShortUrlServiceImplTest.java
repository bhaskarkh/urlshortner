package com.bhaskar.urlshortner.services.shorturl;

import com.bhaskar.urlshortner.model.shorturl.Url;
import com.bhaskar.urlshortner.util.Utility;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;


import java.time.LocalDateTime;
import java.util.stream.Stream;


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
                .originalUrl("http://localhost:8080/shortUrl/share/1/3")
                .expirationDate(LocalDateTime.now().plusMinutes(3))
                .build();
        assertEquals(true, shortUrlService.validateLongUrl(url));
    }

    @ParameterizedTest
    @MethodSource("parameterForExtractUserIdFromUrl")
    void extractUserIdFromUrl(String inputUrl,Long expectedValue) {
        Url url = Url.builder()
                .originalUrl(inputUrl)
                .build();
        assertEquals(expectedValue, shortUrlService.extractUserIdFromUrl(url));
    }
    private static Stream<Arguments> parameterForExtractUserIdFromUrl() {
        return Stream.of(
                Arguments.of("http://localhost:8080/share/1/3", 1L),
                Arguments.of("http://localhost:8080/share/22/3", 22L)
        );
    }

    @Test
    void extractProductIdFromUrl() {
        Url url = Url.builder()
                .originalUrl("http://localhost:8080/share/1/3")
                .build();
        assertEquals(3, shortUrlService.extractProductIdFromUrl(url));
    }
}