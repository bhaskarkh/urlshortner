package com.bhaskar.urlshortner.exception.shorturl;

import com.bhaskar.urlshortner.model.common.ErrorCodeEnum;

public class InvalidShortUrlException extends RuntimeException{
    public InvalidShortUrlException() { super(ErrorCodeEnum.INVALID_SHORT_URL_ERROR_CODE.getMessage());}
}
