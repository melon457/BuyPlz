package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cart")
public class CartApiController {
    private final List<Map<String, Object>> cartList = new ArrayList<>();
    @GetMapping
    public List<Map<String, Object>> getCart() {
        return cartList;
    }

    @PostMapping
    public List<Map<String, Object>> addToCart(@RequestBody Map<String, Object> newItem) {

        System.out.println("========================================");
        System.out.println("[API Log] 장바구니 추가 요청 수신!");
        System.out.println(" - 전달받은 JSON데이터: " + newItem);
        System.out.println("========================================");

        boolean exists = false;
        for (Map<String, Object> item : cartList) {
            if (item.get("productId").toString().equals(newItem.get("productId").toString()) &&
                item.get("size").toString().equals(newItem.get("size").toString())) {
                
                int currentQty = Integer.parseInt(item.get("qty").toString());
                int newQty = Integer.parseInt(newItem.get("qty").toString());
                item.put("qty", currentQty + newQty);
                
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
    public List<Map<String, Object>> clearCart() {
        System.out.println("🗑️ [API Log] 장바구니 비우기 처리 완료");
        cartList.clear();
        return cartList;
    }
}