package com.bhaskar.urlshortner.controller;

import com.bhaskar.urlshortner.model.ErrorResponseDTO;
import com.bhaskar.urlshortner.model.ResponseDTO;
import com.bhaskar.urlshortner.model.Url;
import com.bhaskar.urlshortner.model.UrlDTO;
import com.bhaskar.urlshortner.services.ShortUrlServiceImpl;
import com.bhaskar.urlshortner.util.ErrorCodeStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShortUrlController {

    @Autowired
    ShortUrlServiceImpl shortUrlServiceImpl;

    @PostMapping("generateShortUrl")
    public ResponseEntity<?> generateShortUrl(@RequestBody UrlDTO urlDTO) {
        Url url = shortUrlServiceImpl.generateShortUrl(urlDTO);
        if (url != null) {
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .originalUrl(url.getOriginalUrl())
                    .shortUrl(url.getShortUrl())
                    .expirationDate(url.getExpirationDate().toString())
                    .build();
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }
        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
                .message(ErrorCodeStatic.INVALID_URL)
                .code(HttpStatus.BAD_REQUEST.toString())
                .build();
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
    }
    @PostMapping("getUrl")
    public ResponseEntity<?> getOriginalUrlFromShortUrl(@RequestBody String shortUrl) {
        Url url = shortUrlServiceImpl.getOriginalUrlFromShortUrl(shortUrl);
        if (url != null) {
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .originalUrl(url.getOriginalUrl())
                    .shortUrl(url.getShortUrl())
                    .expirationDate(url.getExpirationDate().toString())
                    .build();
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }
        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
                .message(ErrorCodeStatic.INVALID_URL)
                .code(HttpStatus.BAD_REQUEST.toString())
                .build();
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
    }
}
