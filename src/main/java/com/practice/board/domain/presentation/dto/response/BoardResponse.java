package com.practice.board.domain.presentation.dto.response;

import lombok.*;

@Getter
@Builder
public class BoardResponse {

    private Long id;

    private String username;

    private String title;

    private String content;

    public BoardResponse(com.practice.board.domain.persistence.Board board) {
        id = board.getId();
        username = board.getUser().getUsername();
        title = board.getTitle();
        content = board.getContent();
    }
}