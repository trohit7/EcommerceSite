package com.example.btechproject.repository;

import com.example.btechproject.model.Product;
import com.example.btechproject.model.User;
import com.example.btechproject.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WishListRepository extends JpaRepository<WishList,Integer> {
    @Query("SELECT u FROM WishList u WHERE u.user= ?1 and u.product = ?2")
    WishList findWishListByUserAndProduct(User user, Product product);
}
