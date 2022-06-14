package com.example.btechproject.controller;

import com.example.btechproject.common.ApiResponse;
import com.example.btechproject.dto.ProductDto;
import com.example.btechproject.model.Product;
import com.example.btechproject.model.User;
import com.example.btechproject.model.WishList;
import com.example.btechproject.service.AuthenticationService;
import com.example.btechproject.service.ProductService;
import com.example.btechproject.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishList")
public class WishListController {
    @Autowired
    WishListService wishListService;

    ProductService productService;

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToWishList(@RequestBody Product product, @RequestParam("token") String token){
        // authenticate token
        authenticationService.authenticate(token);
        // find the user if token is present and check whether token is valid or not
        User user = authenticationService.getUser(token);

        // save the item in wishlist
        WishList wishList = new WishList(user, product);


        if(!wishListService.checkIfItemExists(user,product)){
            // if item is not present in wishlist then add to wishlist
            wishListService.createWishList(wishList);
            ApiResponse apiResponse = new ApiResponse(true,"product is added to wishlist");
            return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);


        }

        ApiResponse apiResponse = new ApiResponse(true,"product is already added to wishlist");
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }

    @GetMapping("/{token}")
    public ResponseEntity<List<ProductDto>> getwhishlist(@PathVariable("token") String token){
        // authenticate token
        authenticationService.authenticate(token);
        // find the user if token is present and check whether token is valid or not(find user)
        User user = authenticationService.getUser(token);

        // productdtos = wishListForUser

        List<ProductDto> productDtos = wishListService.getWishListForUser(user);
        return  new ResponseEntity<>(productDtos,HttpStatus.OK);

    }

//    @PostMapping("/delete/{token},{productId}")
//    public ResponseEntity<ApiResponse> deleteproduct(@PathVariable ("token") String token, @PathVariable Integer productId){
//        // authenticate token
//        authenticationService.authenticate(token);
//        // find the user if token is present and check whether token is valid or not(find user)
//        User user = authenticationService.getUser(token);
//
//        List<ProductDto> productDtos = wishListService.getWishListForUser(user);
//        if(productId.equals(productDtos.))
//
//        return null;
//    }




}
