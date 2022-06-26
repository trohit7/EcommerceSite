package com.example.btechproject.controller;

import com.example.btechproject.common.ApiResponse;
import com.example.btechproject.dto.cart.AddToCartDto;
import com.example.btechproject.dto.cart.CartDto;
import com.example.btechproject.exceptions.AuthenticationFailException;
import com.example.btechproject.exceptions.ProductNotExistException;
import com.example.btechproject.model.Product;
import com.example.btechproject.model.User;
import com.example.btechproject.service.AuthenticationService;
import com.example.btechproject.service.CartService;
import com.example.btechproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    ProductService productService;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse>  addToCart(@RequestBody AddToCartDto addToCartDto, @RequestParam("token") String token){
        // authenticate token
        authenticationService.authenticate(token);
        // find the user if token is present and check whether token is valid or not(find user)
        User user = authenticationService.getUser(token);

        cartService.addToCart(addToCartDto,user);

        return  new ResponseEntity<>(new ApiResponse(true,"Added to the cart"), HttpStatus.OK);
    }

    // get all cart items for a user
    @GetMapping("/")
    public ResponseEntity<CartDto> getCartItems(@RequestParam("token") String token){
        authenticationService.authenticate(token);
        // find the user if token is present and check whether token is valid or not(find user)
        User user = authenticationService.getUser(token);

        CartDto cartDto = cartService.listcartItems(user);

        return new ResponseEntity<>(cartDto,HttpStatus.OK);
    }


    @PutMapping("/update/{cartItemId}")
    public ResponseEntity<ApiResponse> updateCartItem(@RequestBody @Valid AddToCartDto cartDto,
                                                      @RequestParam("token") String token) throws AuthenticationFailException, ProductNotExistException {
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        Product product = productService.getProductById(cartDto.getProductId());
        cartService.updateCartItem(cartDto, user,product);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Product has been updated"), HttpStatus.OK);
    }


    @DeleteMapping("/delete/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("cartItemId")Integer itemId,
                                                      @RequestParam("token") String token){
        // authenticate token
        authenticationService.authenticate(token);
        // find the user if token is present and check whether token is valid or not(find user)
        int userId = authenticationService.getUser(token).getId();
            cartService.deleteCartItem(itemId , userId);

        return  new ResponseEntity<>(new ApiResponse(true,"Item is removed from the cart"), HttpStatus.OK);

    }




}
