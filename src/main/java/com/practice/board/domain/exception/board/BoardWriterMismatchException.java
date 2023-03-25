package com.practice.board.domain.exception.board;

import com.practice.board.global.error.exception.BusinessException;
import com.practice.board.global.error.exception.ErrorCode;

public class BoardWriterMismatchException extends BusinessException {
    public static final BusinessException EXCEPTION = new BoardWriterMismatchException();
    public BoardWriterMismatchException(){
        super(ErrorCode.BOARD_WRITER_MISMATCH);
    }
}