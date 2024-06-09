package com.bhaskar.urlshortner.exception.shorturl;

import com.bhaskar.urlshortner.model.common.ErrorCodeEnum;

public class DuplicateOriginalUrlException extends RuntimeException{
    public DuplicateOriginalUrlException(String message) { super(ErrorCodeEnum.DUPLICATE_ORIGINAL_ERROR_CODE.getMessage() +"  "+message);}
    public DuplicateOriginalUrlException() { super(ErrorCodeEnum.DUPLICATE_ORIGINAL_ERROR_CODE.getMessage());}
}
