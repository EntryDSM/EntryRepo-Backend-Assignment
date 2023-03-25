package com.practice.board.domain.persistence;

import org.springframework.data.repository.CrudRepository;
import java.util.Optional;


public interface UserRepository extends CrudRepository<com.practice.board.domain.persistence.User, java.util.UUID> {

    Boolean existsByUsername(String username);

    Optional<com.practice.board.domain.persistence.User> findByUsername(String username);

    Optional<com.practice.board.domain.persistence.User> findByEmail(String email);
}