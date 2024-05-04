package com.bhaskar.urlshortner.exception.product;

import com.bhaskar.urlshortner.model.common.ErrorCodeEnum;

public class ProductSaveFailedException extends RuntimeException{
    public ProductSaveFailedException() { super(ErrorCodeEnum.PRODUCT_NOT_FOUND_ERROR_CODE.getMessage());}
}
