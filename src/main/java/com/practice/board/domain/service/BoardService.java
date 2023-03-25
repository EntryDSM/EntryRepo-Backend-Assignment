package com.practice.board.domain.service;

import com.practice.board.domain.presentation.dto.request.BoardRequest;
import com.practice.board.domain.presentation.dto.response.BoardIdResponse;
import com.practice.board.domain.presentation.dto.response.BoardResponse;
import com.practice.board.domain.persistence.Board;
import com.practice.board.domain.persistence.BoardRepository;
import com.practice.board.domain.persistence.User;
import com.practice.board.domain.facade.UserFacade;
import com.practice.board.domain.exception.board.BoardNotFoundException;
import com.practice.board.domain.exception.board.BoardWriterMismatchException;
import lombok.*;
import org.springframework.stereotype.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserFacade userFacade;

    public BoardIdResponse writeBoard(BoardRequest request) {

        User currentUser = userFacade.currentUser();

        Board board = boardRepository.save(
                Board.builder()
                        .user(currentUser)
                        .title(request.getTitle())
                        .content(request.getContent())
                        .build()
        );
        return new BoardIdResponse(board.getId());
    }

    public void modifyBoard(Long boardId, BoardRequest request) {

        User currentUser = userFacade.currentUser();
        Board board = boardRepository.findById(boardId)
                        .orElseThrow(() -> BoardNotFoundException.EXCEPTION);

        writerCheck(currentUser, board);

        board.modifyTitleAndContent(request.getTitle(), request.getContent());
        boardRepository.save(board);
    }

    public void deleteBoard(Long boardId) {

        User currentUser = userFacade.currentUser();
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> BoardNotFoundException.EXCEPTION);

        writerCheck(currentUser, board);

        boardRepository.deleteById(boardId);
    }

    private void writerCheck(User user, Board board) {
        if (board.getUser() != user) {
            throw BoardWriterMismatchException.EXCEPTION;
        }
    }

    public BoardResponse findBoard(Long boardId) {

        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> BoardNotFoundException.EXCEPTION);

        return new BoardResponse(board);
    }

    public List<BoardResponse> findAllBoards() {

        return boardRepository.findAll()
                .stream()
                .map(BoardResponse::new)
                .collect(Collectors.toList());
    }

    public List<BoardResponse> findMyBoards() {

        User currentUser = userFacade.currentUser();

        return boardRepository.findByUserId(currentUser.getId())
                .stream()
                .map(BoardResponse::new)
                .collect(Collectors.toList());
    }

    public List<BoardResponse> searchBoard(String title) {
        return boardRepository.findByTitleContaining(title)
                .stream()
                .map(BoardResponse::new)
                .collect(Collectors.toList());
    }
}