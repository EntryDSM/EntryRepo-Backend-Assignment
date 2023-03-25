package com.practice.board.domain.persistence;


import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface BoardRepository extends JpaRepository<com.practice.board.domain.persistence.Board, Long> {

    List<com.practice.board.domain.persistence.Board> findByUserId(Long userId);

    List<com.practice.board.domain.persistence.Board> findByTitleContaining(String title);
}