package com.bhaskar.urlshortner.exception.user;

import com.bhaskar.urlshortner.model.common.ErrorCodeEnum;

public class UserSaveFailedException extends RuntimeException{
    public UserSaveFailedException() { super(ErrorCodeEnum.FAILED_TO_SAVE_USER_ERROR_CODE.getMessage());}
}
