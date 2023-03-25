package com.practice.board.domain.presentation;

import com.practice.board.domain.presentation.dto.request.BoardRequest;
import com.practice.board.domain.presentation.dto.response.BoardIdResponse;
import com.practice.board.domain.presentation.dto.response.BoardResponse;
import com.practice.board.domain.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/board")
    public BoardIdResponse writeBoard(@Valid @RequestBody BoardRequest request) {
        return boardService.writeBoard(request);
    }

    @PatchMapping ("/board/{boardId}")
    public void modifyBoard(
        @PathVariable Long boardId,
        @Valid @RequestBody BoardRequest request
    ) {
        boardService.modifyBoard(boardId, request);
    }

    @DeleteMapping("/board/{boardId}")
    public void deleteBoard(@PathVariable Long boardId) {
        boardService.deleteBoard(boardId);
    }

    @GetMapping("/board/{boardId}")
    public BoardResponse findBoard(@PathVariable Long boardId) {
        return boardService.findBoard(boardId);
    }

    @GetMapping("/board/all")
    public List<BoardResponse> findBoardAll() {
        return boardService.findAllBoards();
    }

    @GetMapping("/board")
    public List<BoardResponse> findMyBoard() {
        return boardService.findMyBoards();
    }

    @GetMapping("/board/search")
    public List<BoardResponse> searchBoard(@RequestParam String title){
        return boardService.searchBoard(title);
    }
}