package com.example.btechproject.dto.order;

import com.example.btechproject.model.Order;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
@Data
@NoArgsConstructor
public class OrderDto {

    private Integer id;
    private @NotNull Integer userId;

    public OrderDto(Order order) {
        this.setId(order.getId());
        //this.setUserId(order.getUserId());
    }

}
