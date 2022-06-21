package com.example.btechproject.dto.order;

import com.example.btechproject.model.Order;
import com.example.btechproject.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class PlaceOrderDto {

    private Integer id;
    private @NotNull User user;
    private @NotNull Double totalPrice;



    public PlaceOrderDto(Order order) {
        this.setId(order.getId());
        this.setUser(order.getUser());
        this.setTotalPrice(order.getTotalPrice());
    }
}
