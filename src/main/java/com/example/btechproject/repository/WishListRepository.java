package com.example.btechproject.repository;

import com.example.btechproject.model.Product;
import com.example.btechproject.model.User;
import com.example.btechproject.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WishListRepository extends JpaRepository<WishList,Integer> {

    // check whether product is added to wishlist to the user
    @Query("SELECT u FROM WishList u WHERE u.user= ?1 and u.product = ?2")
    WishList findWishListByUserAndProduct(User user, Product product);

    // get wishlist -- find All the products Ordered  By the User  and list them in  "CreatedDate" in  Desc order
    List<WishList> findAllByUserOrderByCreatedDateDesc(User user);


}
