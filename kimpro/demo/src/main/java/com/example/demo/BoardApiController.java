package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/boards")
public class BoardApiController {

    // 💡 패션 쇼핑몰(Godhub) 주제에 맞춘 게시글 예시 데이터
    @GetMapping
    public List<BoardDto> getBoardList() {
        List<BoardDto> boardList = new ArrayList<>();
        boardList.add(new BoardDto(1L, "배송 기간 및 택배사 안내", "Godhub 제품은 CJ대한통운을 통해 1~2일 내 출고됩니다.", "관리자", "공지사항"));
        boardList.add(new BoardDto(2L, "26SS 블록코어 럭비 져지 사이즈 문의", "178cm / 70kg인데 L 사이즈가 맞을까요?", "user01", "상품문의"));
        boardList.add(new BoardDto(3L, "오버핏 자켓 교환/반품 규정", "제품 수령 후 7일 이내 고객센터 문의 시 교환 가능합니다.", "관리자", "FAQ"));
        boardList.add(new BoardDto(4L, "신규 가입 쿠폰 코드 적용 방법", "결제 창에서 WELCOME10 입력 시 10% 할인이 적용됩니다.", "팀장", "공지사항"));
        return boardList;
    }
}