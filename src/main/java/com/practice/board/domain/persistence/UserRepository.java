package com.practice.board.domain.persistence;

import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;


public interface UserRepository extends CrudRepository<User, UUID> {

    Boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);

}