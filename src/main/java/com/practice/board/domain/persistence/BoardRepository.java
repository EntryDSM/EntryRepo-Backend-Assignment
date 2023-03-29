package com.practice.board.domain.persistence;


import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findByUserId(Long userId);

    List<Board> findByTitleContaining(String title);
}