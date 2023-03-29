package com.practice.board.domain.service;

import com.practice.board.domain.persistence.User;
import com.practice.board.domain.persistence.UserRepository;
import com.practice.board.domain.presentation.dto.request.*;
import com.practice.board.domain.presentation.dto.response.*;
import com.practice.board.domain.service.exception.user.PasswordMismatchException;
import com.practice.board.domain.service.exception.board.*;
import com.practice.board.domain.service.exception.user.*;
import com.practice.board.domain.service.facade.*;
import com.practice.board.global.security.jwt.JwtProperties;
import com.practice.board.global.security.jwt.JwtTokenProvider;
import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserFacade userFacade;
    private final JwtProperties jwtProperties;
    private final JwtTokenProvider jwtTokenProvider;

    public void register(SignUpRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw UserAlreadyExistException.EXCEPTION;
        }

        userRepository.save(
            User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword())
                .build()
        );
    }

    @Transactional
    public TokenResponse login(LoginRequest request) {

        User user = userRepository.findByUsername(request.getUsername())
            .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        if (request.getPassword() != user.getPassword()) {
            throw PasswordMismatchException.EXCEPTION;
        }

        return TokenResponse
            .builder()
            .accessToken(jwtTokenProvider.createAccessToken(user.getUsername()))
            .expiredAt(java.time.LocalDateTime.now()
                .plusSeconds(jwtProperties.getAccessExpiration()))
            .build();
    }

    public UserInfoResponse getMyInfo() {

        User currentUser = userFacade.currentUser();

        return new UserInfoResponse(currentUser);
    }

    public void changePassword(PasswordChangeRequest request) {

        User user = userFacade.currentUser();

        if (user.getPassword() == null) {
            throw BoardWriterMismatchException.EXCEPTION;
        }

        if (request.getOldPassword() != user.getPassword()) {
            throw PasswordMismatchException.EXCEPTION;
        }

        user.changePassword(request.getNewPassword());
        userRepository.save(user);
    }

}