package com.bhaskar.urlshortner.exception.common;

import com.bhaskar.urlshortner.exception.product.ProductNotFoundException;
import com.bhaskar.urlshortner.exception.shorturl.DuplicateOriginalUrlException;
import com.bhaskar.urlshortner.exception.shorturl.InvalidLongUrlException;
import com.bhaskar.urlshortner.exception.shorturl.InvalidShortUrlException;
import com.bhaskar.urlshortner.exception.shorturl.ShortUrlExpiredException;
import com.bhaskar.urlshortner.exception.user.UserNotFoundException;
import com.bhaskar.urlshortner.model.common.ErrorCodeEnum;
import com.bhaskar.urlshortner.model.common.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ResponseDTO> handleProductNotFoundException(ProductNotFoundException ex)
    {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(getErrorResponse(ErrorCodeEnum.PRODUCT_NOT_FOUND_ERROR_CODE));
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ResponseDTO> handleUserNotFoundException(UserNotFoundException ex)
    {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(getErrorResponse(ErrorCodeEnum.USER_NOT_FOUND_ERROR_CODE));
    }
    @ExceptionHandler(InvalidLongUrlException.class)
    public ResponseEntity<ResponseDTO> handleInvalidLongUrlException(InvalidLongUrlException ex)
    {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(getErrorResponse(ErrorCodeEnum.INVALID_LONG_URL_ERROR_CODE));
    }
    @ExceptionHandler(InvalidShortUrlException.class)
    public ResponseEntity<ResponseDTO> handleInvalidShortUrlException(InvalidShortUrlException ex)
    {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(getErrorResponse(ErrorCodeEnum.INVALID_SHORT_URL_ERROR_CODE));
    }
    @ExceptionHandler(ShortUrlExpiredException.class)
    public ResponseEntity<ResponseDTO> handleShortUrlExpiredException(ShortUrlExpiredException ex)
    {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(getErrorResponseWithMessage(ex, ErrorCodeEnum.SHORT_URL_EXPIRED_ERROR_CODE));
    }
    @ExceptionHandler(DuplicateOriginalUrlException.class)
    public ResponseEntity<ResponseDTO> handleDuplicateOriginalException(DuplicateOriginalUrlException ex)
    {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(getErrorResponseWithMessage(ex, ErrorCodeEnum.DUPLICATE_ORIGINAL_ERROR_CODE));
    }

    private ResponseDTO getErrorResponseWithMessage(Exception ex, ErrorCodeEnum errorCodeEnum) {
        return ResponseDTO.builder().message(ex.getMessage()).errorCode(errorCodeEnum.value()).build();
    }

    private ResponseDTO getErrorResponse(ErrorCodeEnum errorCode) {
        return ResponseDTO.builder().message(errorCode.getMessage()).errorCode(errorCode.value()).build();
    }

}
