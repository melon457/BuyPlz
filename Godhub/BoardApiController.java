package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor 
public class BoardApiController {

    private final BoardRepository boardRepository;

    // 1. 전체 목록 조회
    @GetMapping
    public List<Board> list() {
        return boardRepository.findAll();
    }

    // 2. 게시글 상세 조회 (추가됨: ID로 1개 조회 및 조회수 증가)
    @GetMapping("/{id}")
    public Board getBoard(@PathVariable Long id) {
        Board board = boardRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다. id=" + id));
        
        // 상세 페이지 열릴 때 조회수 1 증가
        board.setViews((board.getViews() == null ? 0 : board.getViews()) + 1);
        return boardRepository.save(board);
    }

    // 3. 글 저장
    @PostMapping
    public Board save(@RequestBody Board board) {
        if (board.getViews() == null) board.setViews(0);
        if (board.getLikes() == null) board.setLikes(0);
        return boardRepository.save(board);
    }
}