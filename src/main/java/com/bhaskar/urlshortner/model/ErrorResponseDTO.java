package com.bhaskar.urlshortner.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponseDTO {
    private String message;
    private String code;

}
