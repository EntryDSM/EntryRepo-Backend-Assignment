package com.practice.board.domain.service;

import com.practice.board.domain.presentation.dto.request.*;
import com.practice.board.domain.presentation.dto.response.*;
import com.practice.board.domain.exception.user.PasswordMismatchException;
import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final com.practice.board.domain.persistence.UserRepository userRepository;
    private final com.practice.board.domain.facade.UserFacade userFacade;
    private final com.practice.board.global.security.jwt.JwtProperties jwtProperties;
    private final com.practice.board.global.security.jwt.JwtTokenProvider jwtTokenProvider;

    public void register(SignUpRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw com.practice.board.domain.exception.user.UserAlreadyExistException.EXCEPTION;
        }

        userRepository.save(
            com.practice.board.domain.persistence.User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword())
                .build()
        );
    }

    @Transactional
    public TokenResponse login(LoginRequest request) {

        com.practice.board.domain.persistence.User user = userRepository.findByUsername(request.getUsername())
            .orElseThrow(() -> com.practice.board.domain.exception.user.UserNotFoundException.EXCEPTION);

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

        com.practice.board.domain.persistence.User currentUser = userFacade.currentUser();

        return new UserInfoResponse(currentUser);
    }

    public void changePassword(PasswordChangeRequest request) {

        com.practice.board.domain.persistence.User user = userFacade.currentUser();

        if (user.getPassword() == null) {
            throw com.practice.board.domain.exception.board.BoardWriterMismatchException.EXCEPTION;
        }

        if (request.getOldPassword() != user.getPassword()) {
            throw PasswordMismatchException.EXCEPTION;
        }

        user.changePassword(request.getNewPassword());
        userRepository.save(user);
    }

}