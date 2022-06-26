package com.example.btechproject.controller;

import com.example.btechproject.common.ApiResponse;
import com.example.btechproject.dto.product.ProductDto;
import com.example.btechproject.model.Category;
import com.example.btechproject.repository.CategoryRepository;
import com.example.btechproject.repository.ProductRepository;
import com.example.btechproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProductRepository productRepository;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDto productDto){

        Optional<Category> optionalCategory = categoryRepository.findById((productDto.getCategoryId()));
        if(!optionalCategory.isPresent()){
            return new ResponseEntity<>(new ApiResponse(false,"category does not exit"), HttpStatus.BAD_REQUEST);
        }
        productService.createProduct(productDto,optionalCategory.get());
        return new ResponseEntity<>(new ApiResponse(true,"new product has been added"),HttpStatus.CREATED);
    }

    @GetMapping ("/")
    public ResponseEntity<List<ProductDto>> getProducts(){
        List<ProductDto> products = productService.getAllProducts();
        return new  ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/update/{productId}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("productId") Integer productId,@RequestBody ProductDto productDto) throws Exception {

        Optional<Category> optionalCategory = categoryRepository.findById((productDto.getCategoryId()));
        if(!optionalCategory.isPresent()){
            return new ResponseEntity<>(new ApiResponse(false,"category does not exit"), HttpStatus.BAD_REQUEST);
        }
        productService.updateProduct(productDto,productId);
        return new ResponseEntity<>(new ApiResponse(true,"product has been updated"),HttpStatus.OK);
    }
    @PostMapping("/delete/{productId}")

    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable("productId") Integer productId){

        productService.deleteProduct(productId);

        return  new ResponseEntity<>(new ApiResponse(true,"product has been deleted"),HttpStatus.OK);
    }



}
