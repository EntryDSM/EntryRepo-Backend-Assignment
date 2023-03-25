package com.practice.board.domain.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class BoardRequest {

    @NotNull(message = "제목을 입력하세요.")
    private String title;

    @NotNull(message = "내용을 입력하세요.")
    private String content;

}