package com.bhaskar.urlshortner.model.shorturl;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Url {
    @Id
    @GeneratedValue
    private Long id;
    private String originalUrl;
    private String shortUrl;
    private LocalDateTime expirationDate;
    private LocalDateTime creationDate;
}
