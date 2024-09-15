package com.bhaskar.urlshortner.exception.shorturl;

import com.bhaskar.urlshortner.model.common.ErrorCodeEnum;

public class ShortUrlExpiredException extends RuntimeException{
    public ShortUrlExpiredException(String message) { super(ErrorCodeEnum.SHORT_URL_EXPIRED_ERROR_CODE.getMessage() +" on "+message);}
}
