package com.example.btechproject.controller;

import com.example.btechproject.common.ApiResponse;
import com.example.btechproject.dto.product.ProductDto;
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

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
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
        int user_id = authenticationService.getUser(token).getId();
        List<WishList> body = wishListService.readWishList(user_id);
        List<ProductDto> products = new ArrayList<ProductDto>();
        for (WishList wishList : body) {
            products.add(ProductService.getDtoFromProduct(wishList.getProduct()));
        }

        return new ResponseEntity<List<ProductDto>>(products, HttpStatus.OK);

    }

@DeleteMapping("/delete/{wishListId}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("wishListId")Integer Id,
                                                      @RequestParam("token") String token){
        // authenticate token
        authenticationService.authenticate(token);
        // find the user if token is present and check whether token is valid or not(find user)
        int userId = authenticationService.getUser(token).getId();
            wishListService.deleteItem(Id , userId);

        return  new ResponseEntity<>(new ApiResponse(true,"Item is removed from the wishList"), HttpStatus.OK);

    }

}
