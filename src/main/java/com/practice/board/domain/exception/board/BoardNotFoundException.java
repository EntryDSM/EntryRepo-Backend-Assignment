package com.practice.board.domain.exception.board;

import com.practice.board.global.error.exception.BusinessException;
import com.practice.board.global.error.exception.ErrorCode;

public class BoardNotFoundException extends BusinessException {
    public static final BusinessException EXCEPTION = new BoardNotFoundException();
    public BoardNotFoundException(){
        super(ErrorCode.BOARD_NOT_FOUND);
    }
}