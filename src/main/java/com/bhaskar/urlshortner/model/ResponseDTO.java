package com.bhaskar.urlshortner.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDTO {
    private String originalUrl;
    private String shortUrl;
    private String expirationDate;
}
