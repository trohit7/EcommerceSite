package com.example.btechproject.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private @NotBlank String  categoryName;
    private @NotBlank String description;
    private  String imageUrl;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    Set<Product> products;


    public Category(@NotBlank String categoryName, @NotBlank String description) {
        this.categoryName = categoryName;
        this.description = description;
    }

    public Category(@NotBlank String categoryName, @NotBlank String description, @NotBlank String imageUrl) {
        this.categoryName = categoryName;
        this.description = description;
        this.imageUrl = imageUrl;
    }



}
