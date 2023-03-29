package com.practice.board.domain.presentation.dto.response;

import com.practice.board.domain.persistence.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserInfoResponse {

    private String username;

    public UserInfoResponse(User user){
        username = user.getUsername();
    }
}