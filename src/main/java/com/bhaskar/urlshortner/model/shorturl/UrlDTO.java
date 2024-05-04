package com.bhaskar.urlshortner.model.shorturl;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
public class UrlDTO {
    private String url;
    private String expirationDate;
}
