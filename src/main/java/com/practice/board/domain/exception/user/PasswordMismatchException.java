package com.practice.board.domain.exception.user;

import com.practice.board.global.error.exception.BusinessException;
import com.practice.board.global.error.exception.ErrorCode;

public class PasswordMismatchException extends BusinessException {
    public static final BusinessException EXCEPTION = new PasswordMismatchException();
    public PasswordMismatchException(){ super(ErrorCode.PASSWORD_MISMATCH); }

}