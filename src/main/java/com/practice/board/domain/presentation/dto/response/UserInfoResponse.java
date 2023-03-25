package com.practice.board.domain.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserInfoResponse {

    private String username;

    public UserInfoResponse(com.practice.board.domain.persistence.User user){
        username = user.getUsername();
    }
}