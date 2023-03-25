package com.practice.board.domain.service.exception.user;

import com.practice.board.global.error.exception.BusinessException;
import com.practice.board.global.error.exception.ErrorCode;

public class UserAlreadyExistException extends BusinessException {
    public static final BusinessException EXCEPTION = new UserAlreadyExistException();
    private UserAlreadyExistException(){
        super(ErrorCode.USER_ALREADY_EXISTS);
    }
}