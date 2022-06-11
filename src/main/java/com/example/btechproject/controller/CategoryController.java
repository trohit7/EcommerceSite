package com.example.btechproject.controller;

import com.example.btechproject.model.Category;

import com.example.btechproject.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping("/create")
    @ApiOperation(value = "create new category in ecommerce site", notes = "sample notes", response = String.class)
    public String createCategory(@RequestBody Category category){
        categoryService.createCategory(category);
        return "success";
    }

    @GetMapping("/list")
    public List<Category> listCategory() {
        return categoryService.listCategory();
    }


}
