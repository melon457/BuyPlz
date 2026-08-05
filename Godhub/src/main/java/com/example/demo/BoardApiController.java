package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boards")
public class BoardApiController {

    private final BoardRepository boardRepository;

    public BoardApiController(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    // 전체 조회
    @GetMapping
    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    // 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<Board> getBoardById(@PathVariable Long id) {
        return boardRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 작성
    @PostMapping
    public ResponseEntity<Board> createBoard(@RequestBody BoardDto boardDto) {
        Board board = new Board(boardDto.getTitle(), boardDto.getContent(), boardDto.getAuthor());
        Board savedBoard = boardRepository.save(board);
        return ResponseEntity.ok(savedBoard);
    }

    // 수정
    @PutMapping("/{id}")
    public ResponseEntity<Board> updateBoard(@PathVariable Long id, @RequestBody BoardDto boardDto) {
        return boardRepository.findById(id)
                .map(board -> {
                    board.update(boardDto.getTitle(), boardDto.getContent(), boardDto.getAuthor());
                    Board updatedBoard = boardRepository.save(board);
                    return ResponseEntity.ok(updatedBoard);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long id) {
        return boardRepository.findById(id)
                .map(board -> {
                    boardRepository.delete(board);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}