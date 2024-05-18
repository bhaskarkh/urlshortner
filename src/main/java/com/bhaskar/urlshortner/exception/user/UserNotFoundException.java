package com.bhaskar.urlshortner.exception.user;

import com.bhaskar.urlshortner.model.common.ErrorCodeEnum;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException() { super(ErrorCodeEnum.USER_NOT_FOUND_ERROR_CODE.getMessage());}

}
