package com.practice.board.domain.presentation.dto.response;

import com.practice.board.domain.persistence.Board;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
public class BoardResponse {

    private Long id;

    private String username;

    private String title;

    private String content;

    public BoardResponse(Board board) {
        id = board.getId();
        username = board.getUser().getUsername();
        title = board.getTitle();
        content = board.getContent();
    }
}