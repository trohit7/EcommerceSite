package com.example.btechproject.dto.checkout;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CheckoutItemDto {

    private String productName;
    private int  quantity;
    private double price;
    private long productId;
    private int userId;

    public CheckoutItemDto(String productName, int quantity, double price, long productId, int userId) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.productId = productId;
        this.userId = userId;
    }

}
