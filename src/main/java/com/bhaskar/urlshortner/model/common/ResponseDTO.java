package com.bhaskar.urlshortner.model.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDTO {
    private String message;
    private String errorCode;

}
