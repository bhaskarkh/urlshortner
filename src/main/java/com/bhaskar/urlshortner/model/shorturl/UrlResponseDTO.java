package com.bhaskar.urlshortner.model.shorturl;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UrlResponseDTO {
    private String originalUrl;
    private String shortUrl;
    private String expirationDate;
}
