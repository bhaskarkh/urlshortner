package com.bhaskar.urlshortner.model.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public enum ErrorCodeEnum {
    //shortUrl related error code range between 1000-1999
    INVALID_LONG_URL_ERROR_CODE("1000", "Invalid Long Url format correct format is http://localhost:8080/share/{userId}/{productId}"),
    INVALID_SHORT_URL_ERROR_CODE("1001", "Invalid Short Url"),

    SHORT_URL_EXPIRED_ERROR_CODE("1002", "Short Url Expired"),
    DUPLICATE_ORIGINAL_ERROR_CODE("1003", "This Url is already present kindly use that"),
    //user related   error code range between 2000-2999
    USER_NOT_FOUND_ERROR_CODE("2000", "Invalid UserId, User not found kindly check long url"),
    FAILED_TO_SAVE_USER_ERROR_CODE("2001", "Failed to save User"),
    //product  related error code range between 3000-3999
    PRODUCT_NOT_FOUND_ERROR_CODE("3000", "Invalid ProductId, Product not found kindly check long url"),
    FAILED_TO_SAVE_PRODUCT_ERROR_CODE("3001", "Failed to save Product"),
    DEFAULT_ERROR_CODE("5000", "Error occur"); //Default  related error code range between 3000-3999

    private final String errorcode;
    private final String message;

    ErrorCodeEnum(String errorcode, String message) {
        this.errorcode = errorcode;
        this.message = message;
    }

    public String value() {
        return errorcode;
    }

    public String getMessage() {
        return message;
    }

    @JsonCreator
    public static ErrorCodeEnum find(String value) {
        return Arrays.stream(values())
                .filter(category -> StringUtils.equalsIgnoreCase(category.errorcode, value) || StringUtils.equalsIgnoreCase(category.value(), value))
                .findFirst().orElse(ErrorCodeEnum.DEFAULT_ERROR_CODE);
    }
}
