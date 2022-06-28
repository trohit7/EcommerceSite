package com.example.btechproject.controller;

import com.example.btechproject.common.ApiResponse;
import com.example.btechproject.model.Category;

import com.example.btechproject.repository.CategoryRepository;
import com.example.btechproject.service.CategoryService;
import com.example.btechproject.utils.Helper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
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
        if (Helper.notNull(categoryService.readCategory(category.getCategoryName()))){
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category already exists"), HttpStatus.CONFLICT);
        }
        categoryService.createCategory(category);
        return new ResponseEntity<>(new ApiResponse(true, "created a new category "), HttpStatus.CREATED) ;
    }

    @GetMapping("/")
    public ResponseEntity<List<Category>> listCategory() {
        List<Category> body = categoryService.listCategory();
        return new ResponseEntity<>(body,HttpStatus.OK);
    }


    @PostMapping("/update/{categoryId}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable("categoryId") Integer categoryId, @Valid @RequestBody Category category ) {
        // Check to see if the category exists.
        if (Helper.notNull(categoryService.readCategory(categoryId))) {
            // If the category exists then update it.
            categoryService.editCategory(categoryId, category);
            return new ResponseEntity<ApiResponse>(new ApiResponse(true, "updated the category"), HttpStatus.OK);
        }
        // If the category doesn't exist then return a response of unsuccessful.
        return new ResponseEntity<>(new ApiResponse(true," category does not exist"),HttpStatus.NOT_FOUND  );

    }



}
