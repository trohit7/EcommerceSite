package com.example.btechproject.dto.order;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class OrderItemsDto {


    private @NotNull double price;
    private @NotNull int quantity;
    private @NotNull int orderId;
    private @NotNull int productId;


    public OrderItemsDto(@NotNull double price, @NotNull int quantity, @NotNull int orderId, @NotNull int productId) {
        this.price = price;
        this.quantity = quantity;
        this.orderId = orderId;
        this.productId = productId;
    }
}
