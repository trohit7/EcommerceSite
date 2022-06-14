package com.example.btechproject.service;

import com.example.btechproject.model.Product;
import com.example.btechproject.model.User;
import com.example.btechproject.model.WishList;
import com.example.btechproject.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishListService {
    @Autowired
    WishListRepository wishListRepository;

    public void createWishList(WishList wishList) {

        wishListRepository.save(wishList);

    }

   public boolean checkIfItemExists(User user, Product prd){
        WishList wishList = wishListRepository.findWishListByUserAndProduct(user,prd);
        return wishList != null;
   }

}
