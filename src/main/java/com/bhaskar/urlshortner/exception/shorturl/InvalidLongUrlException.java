package com.bhaskar.urlshortner.exception.shorturl;

import com.bhaskar.urlshortner.model.common.ErrorCodeEnum;

public class InvalidLongUrlException extends RuntimeException{
    public InvalidLongUrlException() { super(ErrorCodeEnum.INVALID_LONG_URL_ERROR_CODE.getMessage());}
}
