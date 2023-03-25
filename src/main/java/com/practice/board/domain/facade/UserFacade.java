package com.practice.board.domain.facade;

import com.practice.board.domain.persistence.User;
import com.practice.board.domain.persistence.UserRepository;
import lombok.*;
import org.springframework.security.core.context.*;
import org.springframework.stereotype.*;
import com.practice.board.domain.exception.user.UserNotFoundException;

/**
 * 공통 로직을 묶기 위한 컨포넌트
 */
@RequiredArgsConstructor
@Component
public class UserFacade {

    private final UserRepository userRepository;

    public User currentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }
}