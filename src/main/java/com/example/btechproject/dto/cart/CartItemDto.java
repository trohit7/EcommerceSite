package com.example.btechproject.dto.cart;

import com.example.btechproject.model.Cart;
import com.example.btechproject.model.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
@Data
@NoArgsConstructor
public class CartItemDto {
    private Integer id;
    private @NotNull Integer quantity;
    private @NotNull Product product;

    public CartItemDto(Cart cart){
        this.id= cart.getId();
        this.quantity= cart.getQuantity();
        this.setProduct(cart.getProduct());
    }


}
