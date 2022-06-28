package com.example.btechproject.service;

import com.example.btechproject.dto.product.ProductDto;
import com.example.btechproject.exceptions.CartItemNotExistException;
import com.example.btechproject.model.Product;
import com.example.btechproject.model.User;
import com.example.btechproject.model.WishList;
import com.example.btechproject.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishListService {
    @Autowired
    WishListRepository wishListRepository;

    @Autowired
    ProductService productService;

    public void createWishList(WishList wishList) {

        wishListRepository.save(wishList);

    }

   public boolean checkIfItemExists(User user, Product prd){
        WishList wishList = wishListRepository.findWishListByUserAndProduct(user,prd);
        return wishList != null;
   }

   public List<ProductDto> getWishListForUser(User user){
        final List<WishList> wishLists = wishListRepository.findAllByUserOrderByCreatedDateDesc(user);
        List<ProductDto> productDtos = new ArrayList<>();
        for(WishList wishList:wishLists) {
            productDtos.add(productService.getProductDto(wishList.getProduct()));
        }
        return productDtos;
   }


    public List<WishList> readWishList(Integer userId) {
        return wishListRepository.findAllByUserIdOrderByCreatedDateDesc(userId);

    }

    public void deleteItem(Integer id, int userId) throws CartItemNotExistException {
        if (!wishListRepository.existsById(id))
            throw new CartItemNotExistException("Cart id is invalid : " + id);
        wishListRepository.deleteById(id);
    }
}
