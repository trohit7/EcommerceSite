package com.example.btechproject.dto.cart;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
public class CartDto {

    private List<CartItemDto> cartItems;
    private double totalCost;

    public CartDto(List<CartItemDto> cartItemDtoList, double totalCost) {
        this.cartItems = cartItemDtoList;
        this.totalCost = totalCost;
    }
}
