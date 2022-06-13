package com.example.btechproject.repository;

import com.example.btechproject.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishListRepository extends JpaRepository<WishList,Integer> {
}
