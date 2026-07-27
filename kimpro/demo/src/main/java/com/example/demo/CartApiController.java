package com.example.demo;


import com.example.demo.CartItemDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartApiController {

    private final List<CartItemDto> cartList = new ArrayList<>();

    @GetMapping
    public List<CartItemDto> getCart() {
        return cartList;
    }

    @PostMapping
    public List<CartItemDto> addToCart(@RequestBody CartItemDto newItem) {
        // 💡 [Spring Boot 콘솔] 요청 데이터 출력 로그
        System.out.println("========================================");
        System.out.println("📦 [Spring Boot API Log] 장바구니 추가 요청 수신!");
        System.out.println(" - 상품 ID : " + newItem.getProductId());
        System.out.println(" - 상품명   : " + newItem.getName());
        System.out.println(" - 선택사이즈: " + newItem.getSize());
        System.out.println(" - 가격     : " + newItem.getPrice() + "원");
        System.out.println("========================================");

        boolean exists = false;
        for (CartItemDto item : cartList) {
            if (item.getProductId().equals(newItem.getProductId()) && item.getSize().equals(newItem.getSize())) {
                item.setQty(item.getQty() + newItem.getQty());
                exists = true;
                break;
            }
        }

        if (!exists) {
            cartList.add(newItem);
        }

        return cartList;
    }

    @DeleteMapping
    public List<CartItemDto> clearCart() {
        System.out.println("🗑️ [Spring Boot API Log] 장바구니 비우기 처리 완료");
        cartList.clear();
        return cartList;
    }
}