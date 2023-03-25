package com.practice.board.domain.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class PasswordChangeRequest {

    @NotNull(message = "기존 비밀번호를 입력하세요.")
    private String oldPassword;

    @NotNull(message = "새로운 비밀번호를 입력하세요.")
    @Pattern(regexp="(?=.*\\d)(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}", message = "비밀번호는 특수기호 숫자 포함 8-20자여야 합니다")
    private String newPassword;

}