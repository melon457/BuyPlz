package com.example.demo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper; // databind 가 꼭 들어가야 합니다!
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final BoardRepository boardRepository;
    private final ObjectMapper objectMapper;

    @Override
    public void run(String... args) throws Exception {

        if (boardRepository.count() == 0) {
            String jsonArray = """
                [
                    {
                        "title": "배송 관련 문의드립니다.",
                        "content": "배송은 보통 며칠 정도 걸리나요?",
                        "writer": "홍길동",
                        "views": 10,
                        "likes": 2
                    },
                    {
                        "title": "상품 후기 작성합니다!",
                        "content": "품질이 정말 좋고 마음에 듭니다.",
                        "writer": "이순신",
                        "views": 25,
                        "likes": 5
                    },
                    {
                        "title": "환불 절차가 궁금합니다.",
                        "content": "교환 및 환불 조건이 어떻게 되나요?",
                        "writer": "강감찬",
                        "views": 5,
                        "likes": 0
                    }
                ]
                """;

            List<Board> boards = objectMapper.readValue(jsonArray, new TypeReference<List<Board>>() {});
            boardRepository.saveAll(boards);
        }
    }
}