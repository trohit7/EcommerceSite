package com.example.btechproject.dto.product;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
@Data
@NoArgsConstructor
public class ProductDto {

    private Integer id;
    private @NotNull String name;
    private @NotNull String imageURL;
    private @NotNull double price;
    private @NotNull String description;
    private @NotNull Integer categoryId;
}
