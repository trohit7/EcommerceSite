package com.example.btechproject.service;

import com.example.btechproject.dto.cart.AddToCartDto;
import com.example.btechproject.dto.cart.CartDto;
import com.example.btechproject.dto.cart.CartItemDto;
import com.example.btechproject.exceptions.CustomException;
import com.example.btechproject.model.Cart;
import com.example.btechproject.model.Product;
import com.example.btechproject.model.User;
import com.example.btechproject.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    ProductService productService;
    @Autowired
    CartRepository cartRepository;


    public void addToCart(AddToCartDto addToCartDto, User user) {
        // validate the product id is  valid or not
        Product product = productService.getProductById(addToCartDto.getProductId());
        // save the cart
        Cart cart = new Cart();
        cart.setProduct(product);
        cart.setUser(user);
        cart.setQuantity(addToCartDto.getQuantity());
        cart.setCreatedDate(new Date());
        cartRepository.save(cart);
    }

    public CartDto listcartItems(User user) {
        List<Cart> cartList = cartRepository.findAllByUserOrderByCreatedDateDesc(user);

        List<CartItemDto> cartItems  = new ArrayList<>();
        double totalCost = 0;
        for(Cart cart : cartList){
            CartItemDto cartItemDto = new CartItemDto(cart);
            totalCost += cartItemDto.getQuantity() * cart.getProduct().getPrice();
            cartItems.add(cartItemDto);
        }

        CartDto cartDto = new CartDto();
        cartDto.setTotalCost(totalCost);
        cartDto.setCartItems(cartItems);

        return cartDto;
    }

    public void deleteCartItem(Integer cartItemId, User user) {

        Optional<Cart> optionalCart = cartRepository.findById(cartItemId);

        if(optionalCart.isEmpty()){
            throw new CustomException("cart item id is invalid:" +cartItemId);
        }
        Cart cart = optionalCart.get();
        if(cart.getUser() != user){
            throw new CustomException("cart item does not belongto user:"+ cartItemId);
        }
        cartRepository.delete(cart);
    }
}
