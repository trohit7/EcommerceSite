package com.example.btechproject.controller;

import com.example.btechproject.common.ApiResponse;
import com.example.btechproject.model.Category;

import com.example.btechproject.repository.CategoryRepository;
import com.example.btechproject.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;
    @Autowired
    CategoryRepository categoryRepository;

    @PostMapping("/create")
    @ApiOperation(value = "create new category in ecommerce site", notes = "sample notes", response = String.class)
    public ResponseEntity<ApiResponse> createCategory(@RequestBody Category category){
        categoryService.createCategory(category);
        return new ResponseEntity<>(new ApiResponse(true, "created a new category "), HttpStatus.CREATED) ;
    }

    @GetMapping("/list")
    public List<Category> listCategory() {
        return categoryService.listCategory();
    }


    @PostMapping("/update/{categoryId}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable("categoryId") int categoryId, @RequestBody Category category ) {

        if(!categoryService.findById(categoryId)){
            return new ResponseEntity<>(new ApiResponse(false," category does not exists "),HttpStatus.OK);

        }
        categoryService.editCategory(categoryId,category);
        return new ResponseEntity<>(new ApiResponse(true," category has been updated "),HttpStatus.CREATED  );

    }



}
