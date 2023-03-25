package com.practice.board.domain.presentation.dto.response;

import java.time.LocalDateTime;
import lombok.*;

@Getter
@Builder
public class TokenResponse {

    private String accessToken;

    private LocalDateTime expiredAt;

}